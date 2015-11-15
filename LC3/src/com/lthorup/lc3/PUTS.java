package com.lthorup.lc3;

public class PUTS extends Word {

	public PUTS(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 1, line);	
		isIO = true;
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0xF000 | 0x22);
	}

	@Override
	public void execute(LC3 machine) {
		int msg = machine.readReg(0);
		machine.writeString(msg);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "PUTS";
	}
	
	@Override
	public String argName(int n) {
		return "";
	}

}
