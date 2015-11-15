package com.lthorup.lc3;

public class JMP extends Word {
	
	private int reg;
	
	public JMP(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 2, line);	
		reg = parseReg(tokens[1], line);
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0xc000 | (reg << 5));
	}
	
	@Override
	public void execute(LC3 machine) {
		int address = machine.readReg(reg);
		machine.pcSet(address);
	}
	
	@Override
	public String opName() {
		return "JMP";
	}
	
	@Override
	public String argName(int n) {
		return (n==1) ? String.format("R%d", reg) : "";
	}
}
