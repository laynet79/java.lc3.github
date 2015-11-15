package com.lthorup.lc3;

public class Word {

	enum BreakType { OFF, ONCE, FOREVER }
	
	protected Address address;
	protected short value;
	protected int line;
	protected BreakType breakPnt;
	protected boolean isIO = false;
	
	public Word(Address a, int v, int line) { address = a; value = (short)v; this.line = line; breakPnt = BreakType.OFF; }
	
	public void encode() throws Exception {}
	
	public short value() { return value; }
	public void setValue(int v) { value = (short)v; }
	public void setBreakPnt(BreakType type) { breakPnt = type; }
	public BreakType getBreakPnt() { return breakPnt; }
	public boolean isJSR() { return false; }
	public boolean isIO() { return isIO; }
	
	public void execute(LC3 computer) throws Exception {
		computer.halt();
		throw new Exception(String.format("bad instruction at address: %d", address));
	}
	
	public static boolean parse(LC3 machine, String input, int line, Address addr) throws Exception {
		
		// Construct an address for this word
		Address address = null;
		if (! Character.isWhitespace(input.charAt(0))) {
			int i = 0;
			while (!Character.isWhitespace(input.charAt(i)))
				i++;
			String label = input.substring(0,i);
			address = Address.add(label, addr);
			input = input.substring(i).trim();
		}
		else {
			address = new Address("", addr.value());
			input = input.trim();
		}
		
		// break input line into tokens and parse word base on opcode
		String[] tokens = parseTokens(input, line);
		String opcode = tokens[0].toUpperCase();
		
		switch (opcode) {
		case ".END":
		{
			return true;
		}
		case ".ORIG":
		{
			verifyNumTokens(tokens, 2, line);
			int origin = parseNumber(tokens[1], line, 0, 65535);
			addr.set(origin);
			machine.setOrigin(origin);
			break;
		}
		case ".FILL":
		{
			verifyNumTokens(tokens, 2, line);
			int value = parseNumber(tokens[1], line, -32768, 32767);
			machine.setMemWord(addr, new Word(address, value, line));
			addr.inc();
			break;
		}
		case ".BLKW":
		{
			verifyNumTokens(tokens, 3, line);
			int cnt = parseNumber(tokens[1], line, 0, 65535);
			int value = parseNumber(tokens[2], line, -32768, 32767);
			for (int i = 0; i < cnt; i++) {
				machine.setMemWord(addr, new Word((i==0) ? address : addr.copy(), value, line));
				addr.inc();
			}
			break;
		}
		case ".STRINGZ":
		{
			verifyNumTokens(tokens, 2, line);
			for (int i = 0; i < tokens[1].length(); i++) {
				machine.setMemWord(addr, new Word((i==0) ? address : addr.copy(), tokens[1].charAt(i), line));
				addr.inc();
			}
			machine.setMemWord(addr.copy(), new Word(addr, 0, line));
			addr.inc();
			break;
		}
		case "ADD":
			machine.setMemWord(addr, new ADD(tokens, address, line));
			addr.inc();
			break;
		case "AND":
			machine.setMemWord(addr, new AND(tokens, address, line));
			addr.inc();
			break;
		case "BR":
		case "BRNZP":
			machine.setMemWord(addr, new BR(true, true, true, tokens, address, line));
			addr.inc();
			break;
		case "BRZ":
			machine.setMemWord(addr, new BR(true, false, false, tokens, address, line));
			addr.inc();
			break;
		case "BRN":
			machine.setMemWord(addr, new BR(false, true, false, tokens, address, line));
			addr.inc();
			break;
		case "BRP":
			machine.setMemWord(addr, new BR(false, false, true, tokens, address, line));
			addr.inc();
			break;
		case "BRZP":
			machine.setMemWord(addr, new BR(true, false, true, tokens, address, line));
			addr.inc();
			break;
		case "BRNZ":
			machine.setMemWord(addr, new BR(true, true, false, tokens, address, line));
			addr.inc();
			break;
		case "BRNP":
			machine.setMemWord(addr, new BR(false, true, true, tokens, address, line));
			addr.inc();
			break;
		case "JMP":
			machine.setMemWord(addr, new JMP(tokens, address, line));
			addr.inc();
			break;
		case "RET":
			machine.setMemWord(addr, new RET(tokens, address, line));
			addr.inc();
			break;
		case "JSR":
			machine.setMemWord(addr, new JSR(tokens, address, line));
			addr.inc();
			break;
		case "JSRR":
			machine.setMemWord(addr, new JSRR(tokens, address, line));
			addr.inc();
			break;
		case "NOT":
			machine.setMemWord(addr, new NOT(tokens, address, line));
			addr.inc();
			break;
		case "LD":
			machine.setMemWord(addr, new LD(tokens, address, line));
			addr.inc();
			break;
		case "LDI":
			machine.setMemWord(addr, new LDI(tokens, address, line));
			addr.inc();
			break;
		case "LDR":
			machine.setMemWord(addr, new LDR(tokens, address, line));
			addr.inc();
			break;
		case "LEA":
			machine.setMemWord(addr, new LEA(tokens, address, line));
			addr.inc();
			break;
		case "ST":
			machine.setMemWord(addr, new ST(tokens, address, line));
			addr.inc();
			break;
		case "STI":
			machine.setMemWord(addr, new ST(tokens, address, line));
			addr.inc();
			break;
		case "STR":
			machine.setMemWord(addr, new STR(tokens, address, line));
			addr.inc();
			break;
		case "GETC":
			machine.setMemWord(addr, new GETC(tokens, address, line));
			addr.inc();
			break;
		case "OUT":
			machine.setMemWord(addr, new OUT(tokens, address, line));
			addr.inc();
			break;
		case "PUTC":
			machine.setMemWord(addr, new OUT(tokens, address, line));
			addr.inc();
			break;
		case "PUTS":
			machine.setMemWord(addr, new PUTS(tokens, address, line));
			addr.inc();
			break;
		case "IN":
			machine.setMemWord(addr, new IN(tokens, address, line));
			addr.inc();
			break;
		case "HALT":
			machine.setMemWord(addr, new HALT(tokens, address, line));
			addr.inc();
			break;			
		default:
			throw new Exception(String.format("bad instruction: %s found on line: %d", opcode, addr.value()));
		}
		return false;
	}
	
	protected static String[] parseTokens(String input, int line) throws Exception {
		String[] tok = new String[100];
		int cnt = 0;
		input = input.trim();
		int end = input.length();
		int s = 0;
		while (s < end) {
			int e = s;
			if (input.charAt(s) == '"') {
				s++;
				e = s;
				while (e != end && input.charAt(e) != '"')
					e++;
				if (input.charAt(e) != '"')
					throw new Exception(String.format("invalid character string specified on line: %d", line));
				tok[cnt] = input.substring(s, e);
				tok[cnt] = tok[cnt].replace("\\n", "\n");
				cnt++;
				e++;
			}
			else {
				while (e != end && ! Character.isWhitespace(input.charAt(e)) && input.charAt(e) != ',')
					e++;
				tok[cnt++] = input.substring(s, e);
			}
			s = e;
			while (s != end && (Character.isWhitespace(input.charAt(s)) || input.charAt(s) == ','))
				s++;
		}
		String[] tokens = new String[cnt];
		for (int i = 0; i < cnt; i++)
			tokens[i] = tok[i];
		return tokens;
	}

	protected static void verifyNumTokens(String[] tokens, int n, int line) throws Exception {
		if (tokens.length != n)
			throw new Exception(String.format("wrong number of tokens on line: %d", line));
	}
	
	protected static boolean isNumber(String name) {
		return name.charAt(0) == '#' || name.charAt(0) == 'x';
	}
	
	protected static int parseNumber(String name, int line, int min, int max) throws Exception {
		try {
			int value;
			if (name.charAt(0) == '#')
				value = Integer.parseInt(name.substring(1));
			else if (name.charAt(0) == 'x')
				value = Integer.parseInt(name.substring(1), 16);
			else
				value = Integer.parseInt(name);
			value = (short)value;
			if (value < min || value > max)
				throw new Exception();
			return value;
		}
		catch(Exception e) {
			throw new Exception(String.format("illegal number specified on line: %d", line));
		}
	}
	
	protected static int parseReg(String name, int line) throws Exception {
		int reg;
		switch (name) {
		case "R0":
			reg = 0;
			break;
		case "R1":
			reg = 1;
			break;
		case "R2":
			reg = 2;
			break;
		case "R3":
			reg = 3;
			break;
		case "R4":
			reg = 4;
			break;
		case "R5":
			reg = 5;
			break;
		case "R6":
			reg = 6;
			break;
		case "R7":
			reg = 7;
			break;
		default:
			throw new Exception(String.format("bad register specified on line: %d", line));
		}
		return reg;
	}
	
	public static Address parseAddress(String name, int line, Address pc, int bits) throws Exception {
		Address addr;
		if (isNumber(name)) {
			int value;
			if (bits == 9)
				value = parseNumber(name, line, -256, 255);
			else // 11
				value = parseNumber(name, line, -1024, 1023);
			value += pc.value();
			addr = new Address("", value);
		}
		else
			addr = Address.lookup(name);
		return addr;
	}
	
	public String opName() {
		return "";
	}
	
	public String argName(int n) {
		return "";
	}
	
	@Override
	public String toString() {
		return String.format("%s%04X: %04X %-10s %-4s %-3s %-3s %-3s",
				(breakPnt == BreakType.FOREVER) ? "-" : " ", address.value(), value, address.name(), opName(), argName(1), argName(2), argName(3));
	}
	
}
