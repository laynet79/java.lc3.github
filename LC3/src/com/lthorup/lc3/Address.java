package com.lthorup.lc3;

import java.util.ArrayList;

public class Address {

	private String name;
	private int value;
	
	private static ArrayList<Address> addresses = new ArrayList<Address>();
		
	public static Address add(String name, Address addr) throws Exception {
		for (Address s : addresses) {
			if (s.name.equals(name)) {
				if (s.value != -1)
					throw new Exception(String.format("symbol: %s already defined at line: %d", name, addr.value));
				s.value = addr.value;
				return s;
			}
		}
		Address s = new Address(name, addr.value);
		addresses.add(s);
		return s;
	}
	
	public static void reset() {
		addresses.clear();
	}
	
	public static Address lookup(String name) {
		for (Address s : addresses) {
			if (s.name.equals(name))
				return s;
		}
		Address s = new Address(name, -1);
		addresses.add(s);
		return s;
	}
	
	public static void link() throws Exception {
		for (Address s : addresses) {
			if (s.value == -1)
				throw new Exception(String.format("link error: %s is undefined", s.name));
		}
	}
	
	public Address(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String name() { return name;  }
	public int value()   { return value; }
	public void set(int value) { this.value = value; }
	public void inc() { value++; }
	public Address copy() { return new Address("", value); }
}
