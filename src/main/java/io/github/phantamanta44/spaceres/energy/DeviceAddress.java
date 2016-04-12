package io.github.phantamanta44.spaceres.energy;

public class DeviceAddress implements CharSequence {

	private String address;
	
	public DeviceAddress(String address) {
		if (!address.matches("^\\w{3,16}$"))
			throw new IllegalArgumentException("Invalid device address!");
		this.address = address;
	}
	
	public void setAddress(String address) {
		if (!address.matches("^\\w{3,16}$"))
			throw new IllegalArgumentException("Invalid device address!");
		this.address = address;
	}
	
	@Override
	public int length() {
		return address.length();
	}

	@Override
	public char charAt(int index) {
		return address.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return address.subSequence(start, end);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DeviceAddress)
			return ((DeviceAddress)o).address.equalsIgnoreCase(this.address);
		else if (o instanceof String)
			return ((String)o).equalsIgnoreCase(this.address);
		return false;
	}
	
	@Override
	public String toString() {
		return address;
	}

}
