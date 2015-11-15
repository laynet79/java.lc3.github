package com.lthorup.lc3;

public class LDR extends Word {

	private int dst;
	private int base;
	private int offset;
	
	public LDR(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 4, line);		
		dst = parseReg(tokens[1], line);
		base = parseReg(tokens[2], line);
		try {
			offset = parseNumber(tokens[3], line, -32, 31);
		}
		catch (Exception e) {
			throw new Exception(String.format("register relative offset too large at line: %d", line));
		}
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0x6000 | (dst << 9) | (base << 6) | (offset & 0x3F));
	}
	
	@Override
	public void execute(LC3 machine) {
		int address = machine.readReg(base) + offset;
		int value = machine.readMem(address);
		machine.writeReg(dst, value);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "LDR";
	}
	
	@Override
	public String argName(int n) {
		String s;
		if (n == 1)
			s = String.format("R%d,", dst);
		else if (n == 2)
			s = String.format("R%d,", base);
		else
			s = String.format("x%04X", offset & 0x3F);
		return s;
	}

}
