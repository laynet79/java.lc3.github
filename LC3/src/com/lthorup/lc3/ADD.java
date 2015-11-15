package com.lthorup.lc3;

public class ADD extends Word {

	private boolean immediate;
	private int src1, src2, dst;
	
	public ADD(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 4, line);		
		dst = parseReg(tokens[1], line);
		src1 = parseReg(tokens[2], line);
		if (isNumber(tokens[3])) {
			src2 = parseNumber(tokens[3], line, -16, 15);
			immediate =  true;
		}
		else {
			src2 = parseReg(tokens[3], line);
			immediate = false;
		}
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0x1000 | (dst << 9) | (src1 << 6) | (immediate?(0x20 | (src2 & 0x1f)) : src2));		
	}
	
	@Override
	public void execute(LC3 machine) {
		if (immediate)
			machine.writeReg(dst, machine.readReg(src1) + src2);
		else
			machine.writeReg(dst, machine.readReg(src1) + machine.readReg(src2));
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "ADD";
	}
	
	@Override
	public String argName(int n) {
		String s;
		if (n == 1)
			s = String.format("R%d,", dst);
		else if (n == 2)
			s = String.format("R%d,", src1);
		else
			s = String.format("%s%d", immediate?"#":"R", src2);
		return s;
	}
}
