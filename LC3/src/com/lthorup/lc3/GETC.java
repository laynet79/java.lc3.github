package com.lthorup.lc3;

public class GETC extends Word {
	
	public GETC(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 1, line);	
		isIO = true;
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0xF000 | 0x20);
	}

	@Override
	public void execute(LC3 machine) {
		int c = machine.readChar();
		machine.writeReg(0, c);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "GETC";
	}
	
	@Override
	public String argName(int n) {
		return "";
	}
}
