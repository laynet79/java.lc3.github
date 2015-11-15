package com.lthorup.lc3;

public class RET extends Word {
	
	public RET(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 1, line);	
	}

	@Override
	public void encode() throws Exception {
		value = (short)0xc1c0;
	}
	
	@Override
	public void execute(LC3 machine) {
		int address = machine.readReg(7);
		machine.pcSet(address);
	}
	
	@Override
	public String opName() {
		return "RET";
	}
	
	@Override
	public String argName(int n) {
		return "";
	}
}
