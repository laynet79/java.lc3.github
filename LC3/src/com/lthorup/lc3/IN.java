package com.lthorup.lc3;

public class IN extends Word {

	public IN(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 1, line);	
		isIO = true;
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0xF000 | 0x23);
	}

	@Override
	public void execute(LC3 machine) {
		int prompt = machine.readReg(0);
		machine.writeString(prompt);
		int c = machine.readChar();
		machine.writeChar(c);
		machine.writeReg(0, c);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "IN";
	}
	
	@Override
	public String argName(int n) {
		return "";
	}

}
