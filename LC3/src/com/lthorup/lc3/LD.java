package com.lthorup.lc3;

public class LD extends Word {
	
	private int dst;
	private Address where;
	
	public LD(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 3, line);		
		dst = parseReg(tokens[1], line);
		where = parseAddress(tokens[2], line, addr, 9);
	}
	
	@Override
	public void encode() throws Exception {
		int pcAddr = where.value() - address.value();
		if (pcAddr < -256 || pcAddr > 255)
			throw new Exception(String.format("pc relative offset too large at line: %d", line));
		value = (short)(0x2000 | (dst << 9) | (pcAddr & 0x1f));
	}
	
	@Override
	public void execute(LC3 machine) {
		int address = where.value();
		int value = machine.readMem(address);
		machine.writeReg(dst, value);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "LD";
	}
	
	@Override
	public String argName(int n) {
		String s;
		if (n == 1)
			s = String.format("R%d,", dst);
		else if (n == 2)
			s = where.name();
		else
			s = "";
		return s;
	}
}
