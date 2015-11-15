package com.lthorup.lc3;

public class HALT extends Word {

	public HALT(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 1, line);
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)0xF031;
	}
	
	@Override
	public void execute(LC3 machine) {
		machine.halt();
	}
	
	@Override
	public String opName() {
		return "HALT";
	}
	
	@Override
	public String argName(int n) {
		return "";
	}
}
