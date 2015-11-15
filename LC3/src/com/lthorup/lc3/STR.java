package com.lthorup.lc3;

public class STR extends Word {

	private int src;
	private int base;
	private int offset;
	
	public STR(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 4, line);		
		src = parseReg(tokens[1], line);
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
		value = (short)(0x7000 | (src << 9) | (base << 6) | (offset & 0x3f));
	}
	
	@Override
	public void execute(LC3 machine) {
		int address = machine.readReg(base) + offset;
		int value = machine.readReg(src);
		machine.writeMem(address, value);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "STR";
	}
	
	@Override
	public String argName(int n) {
		String s;
		if (n == 1)
			s = String.format("R%d,", src);
		else if (n == 2)
			s = String.format("R%d,", base);
		else
			s = String.format("x%04X", offset & 0x1FF);
		return s;
	}

}
