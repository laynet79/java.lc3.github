package com.lthorup.lc3;

public class BR extends Word {

	private Address where;
	private boolean zero, pos, neg;
	
	public BR(boolean z, boolean n, boolean p, String[] tokens, Address addr, int line) throws Exception {
		super(addr, 0, line);
		verifyNumTokens(tokens, 2, line);	
		where = parseAddress(tokens[1], line, addr, 9);
		zero = z;
		neg = n;
		pos = p;
	}
	
	@Override
	public void encode() throws Exception{
		int pcAddr = where.value() - address.value();
		if (pcAddr < -256 || pcAddr > 255)
			throw new Exception(String.format("pc relative offset too large at line: %d", line));		
		value = (short)((neg?0x8000:0) | (zero?0x4000:0) | (pos?0x2000:0) | (pcAddr & 0x1FF));
		
	}
	
	@Override
	public void execute(LC3 machine) {
		if ((zero && machine.zero()) || (pos && machine.pos()) || (neg && machine.neg()))
			machine.pcSet(where.value());
		else
			machine.pcInc();
	}
		
	@Override
	public String opName() {
		if (neg && zero && pos)
			return "BR";
		return String.format("BR%s%s%s", neg?"n":"", zero?"z":"", pos?"p":"");
	}
	
	@Override
	public String argName(int n) {
		return (n==1) ? where.name() : "";
	}
}
