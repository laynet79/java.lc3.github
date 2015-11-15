package com.lthorup.lc3;

public class NOT extends Word {

	private int src, dst;
	
	public NOT(String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 3, line);		
		dst = parseReg(tokens[1], line);
		src = parseReg(tokens[2], line);
	}
	
	@Override
	public void encode() throws Exception {
		value = (short)(0x903F | (dst << 9) | (src << 6));
	}
	
	@Override
	public void execute(LC3 machine) {
		int value = machine.readReg(src);
		value = ~value;
		machine.writeReg(dst, value);
		machine.pcInc();
	}
	
	@Override
	public String opName() {
		return "NOT";
	}
	
	@Override
	public String argName(int n) {
		String s;
		if (n == 1)
			s = String.format("R%d", dst);
		else if (n == 2)
			s = String.format("R%d", src);
		else
			s = "";
		return s;
	}
}
