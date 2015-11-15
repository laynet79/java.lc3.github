package com.lthorup.lc3;

public class JSR extends Word {

	private Address where;
	
	public JSR(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 2, line);	
		where = parseAddress(tokens[1], line, addr, 11);
	}
	
	@Override
	public void encode() throws Exception {
		int pcAddr = where.value() - address.value();
		if (pcAddr < -1024 || pcAddr > 1023)
			throw new Exception(String.format("pc relative offset too large at line: %d", line));		
		value = (short)(0x4800 | (pcAddr & 0x7ff));
	}
	
	@Override
	public boolean isJSR() { return true; }
	
	@Override
	public void execute(LC3 machine) {
		machine.writeReg(7, machine.pc()+1);
		machine.pcSet(where.value());
	}
	
	@Override
	public String opName() {
		return "JSR";
	}
	
	@Override
	public String argName(int n) {
		return (n==1) ? where.name() : "";
	}
}
