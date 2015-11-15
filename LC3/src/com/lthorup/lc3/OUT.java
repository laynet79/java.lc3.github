package com.lthorup.lc3;

public class OUT extends Word {

	public OUT(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 1, line);	
		isIO = true;
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0xF000 | 0x21);
	}

	@Override
	public void execute(LC3 machine) {
		int c = machine.readReg(0);
		machine.writeChar(c);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "OUT";
	}
	
	@Override
	public String argName(int n) {
		return "";
	}

}
