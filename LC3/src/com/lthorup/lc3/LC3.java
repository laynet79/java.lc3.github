package com.lthorup.lc3;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LC3 {

	private LC3View view;
	private final int MEM_SIZE = 65536;
	private Word[] mem = new Word[MEM_SIZE];
	private short[] reg = new short[8];
	private boolean neg = false;
	private boolean zero = true;
	private boolean pos = false;
	private int pc = 0;
	private int origin = 0;
	private boolean halted = true;
	private String status = "stopped";
	private int memEnd = 0;
	private Thread thread;
	private boolean running = false;
	private boolean exit = true;
	private String programName = "";
	private boolean loaded = false;
	private TextArea console;
	private volatile boolean consoleCharReady = false;
	private int consoleChar = 0;
	
	public LC3(LC3View view) {
		this.view = view;
		initialize();
	}
	
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	private void initialize() {
		Address.reset();
		for (int i = 0; i < MEM_SIZE; i++)
			mem[i] = new Word(new Address("",i), (short)0, i);
		for (int i = 0; i < 8; i++)
			reg[i] = 0;
		zero = true;
		pos = neg = false;
		pc = 0;
		halted = true;
		origin = 0;
		memEnd = 0;
	}
	
	public int pc() 		{ return pc; }
	public int pcLine()		{ return pc - origin; }
	public void pcInc()		{ pc++; }
	public void pcSet(int value) { pc = value; }
	public void halt()      { halted = true; exit = true; status = "halted"; }
	public boolean halted() { return halted; }
	public boolean pos()    { return pos;    }
	public boolean neg()    { return neg;    }
	public boolean zero()   { return zero;   }
	public String status()  { return status; }
	public String programName() { return programName; }
	
	public void consoleNewChar(char c) {
		consoleChar = c;
		consoleCharReady = true;
	}
	
	public int readChar() {
		while (! consoleCharReady) {
			try { Thread.sleep(1); } catch (Exception e) {}
			}
		int c = consoleChar;
		consoleCharReady = false;
		return c;
	}
	
	public void writeChar(int c) {
		String s = String.valueOf((char)c);
		view.writeConsole(s);
	}
	
	public void writeString(int addr) {
		String s = "";
		int c = readMem(addr++);
		while (c != 0) {
			s += String.valueOf((char)c);
			c = readMem(addr++);
		}
		view.writeConsole(s);
	}
		
	public void reset() {
		pc = origin;
		for (int i = 0; i < 8; i++)
			reg[i] = 0;
		halted = false;
		status = "stopped";
		consoleCharReady = false;
	}
	
	public void step() {
		if (mem[pc].isIO())
			stepOver();
		else {
			try {
				if (loaded && ! halted) {
					mem[pc].execute(this);
					status = "stopped";
				}
			}
			catch (Exception e) { status = String.format("Error: %s", e.getMessage()); }
		}
	}
	
	public void stepOver() {
		try {
			if (loaded && ! halted) {
				status = "stopped";
				if (mem[pc].isJSR() || mem[pc].isIO()) {
					mem[pc+1].setBreakPnt(Word.BreakType.ONCE);
					run();
				}
				else {
					mem[pc].execute(this);
					view.update();
				}
			}
			else
				view.update();
		}
		catch (Exception e) { status = String.format("Error: %s", e.getMessage()); }
	}	
	
	public void run() {
		if (loaded) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					runLoop();
				}
			});
			thread.start();
		}
		else
			view.update();
	}
	
	public void runLoop() {
		running =  true;
		exit = false;
		try {
			status = "running...";
			while (! halted && ! exit) {
				mem[pc].execute(this);
				if (mem[pc].getBreakPnt() != Word.BreakType.OFF) {
					if (mem[pc].getBreakPnt() == Word.BreakType.ONCE)
						mem[pc].setBreakPnt(Word.BreakType.OFF);
					break;
				}
			}
			status = halted ? "halted" : "stopped";
			view.update();
			running = false;
		}
		catch (Exception e) { status = String.format("Error: %s", e.getMessage()); }
	}
	
	public void stop() { exit = true; }
	
	public void toggleBreakPnt(int line) {
		line += origin;
		if (mem[line].getBreakPnt() == Word.BreakType.OFF)
			mem[line].setBreakPnt(Word.BreakType.FOREVER);
		else
			mem[line].setBreakPnt(Word.BreakType.OFF);
	}
	
	public int readReg(int index) {
		return reg[index];
	}
	
	public void setMemWord(Address addr, Word word) {
		mem[addr.value()] = word;
	}
	
	public void writeReg(int index, int value) {
		reg[index] = (short)value;
		zero = pos = neg = false;
		if (reg[index] == 0)
			zero = true;
		else if (reg[index] < 0)
			neg = true;
		else
			pos = true;
	}
	
	public short readMem(int addr) {
		return mem[addr].value();
	}
	
	public void writeMem(int addr, int value) {
		mem[addr].setValue(value);
	}
	
	public void loadProgram(String fileName) {
		try {
			programName = fileName;
			try {
				initialize();
				BufferedReader file = new BufferedReader(new FileReader(fileName));
				Address addr = new Address("", 0);
				int line = 0;
				boolean endFound = false;
				while (true) {
					String input = file.readLine();
					if (input == null)
						break;
					line++;
	
					// strip out comments
					int commentStart = input.indexOf(';');
					if (commentStart != -1)
						input = input.substring(0,  commentStart);
					
					// skip blank lines
					if (input.trim().equals(""))
						continue;
					
					// parse the line
					if (endFound)
						throw new Exception(".END not at end of file");
					endFound = Word.parse(this, input, line, addr);
				}
				file.close();
				if (! endFound)
					throw new Exception(".END not found at end of file");
				memEnd = addr.value();
				Address.link();
				for (int i = origin; i < memEnd; i++)
					mem[i].encode();
				reset();
				loaded = true;
			}
			catch (IOException e) {
				throw new Exception(String.format("Unable to open file: %s", fileName));
			}
			catch (Exception e) {
				throw e;
			}
		}
		catch (Exception e) { initialize(); status = String.format("Error: %s", e.getMessage()); programName = ""; loaded = false; }
	}
	
	public String memToString() {
		String s = mem[origin].toString();
		int end = Math.max(origin+1, memEnd);
		for (int i = origin+1; i < end; i++) {
			s = String.format("%s\n%s", s, mem[i].toString());
		}
		return s;
	}
}
