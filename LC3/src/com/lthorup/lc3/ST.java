package com.lthorup.lc3;

public class ST extends Word {
	
	private int src;
	private Address where;
	
	public ST(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 3, line);		
		src = parseReg(tokens[1], line);
		where = parseAddress(tokens[2], line, addr, 9);
	}
	
	@Override
	public void encode() throws Exception {
		int pcAddr = where.value() - address.value();
		if (pcAddr < -256 || pcAddr > 255)
			throw new Exception(String.format("pc relative offset too large at line: %d", line));
		value = (short)(0x3000 | (src << 9) | (pcAddr & 0x1f));
	}
	
	@Override
	public void execute(LC3 machine) {
		int address = where.value();
		int value = machine.readReg(src);
		machine.writeMem(address, value);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "ST";
	}
	
	@Override
	public String argName(int n) {
		String s;
		if (n == 1)
			s = String.format("R%d,", src);
		else if (n == 2)
			s = where.name();
		else
			s = "";
		return s;
	}
}
