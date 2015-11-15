package com.lthorup.lc3;

public class JSRR extends Word {
	
	private int reg;
	
	public JSRR(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 2, line);	
		reg = parseReg(tokens[1], line);
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0x4000 | (reg << 6));
	}
	
	@Override
	public boolean isJSR() { return true; }
	
	@Override
	public void execute(LC3 machine) {
		int address = machine.readReg(reg);
		machine.pcSet(address);
	}
	
	@Override
	public String opName() {
		return "JSRR";
	}
	
	@Override
	public String argName(int n) {
		return (n==1) ? String.format("R%d", reg) : "";
	}
}
