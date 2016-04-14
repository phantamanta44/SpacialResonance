package io.github.phantamanta44.spaceres.lib;

public enum LibTier {

	LEADSTONE(80000, 200, 1000, 3, 1),
	INVAR(125000, 800, 40000, 6, 2),
	ELECTRUM(500000, 8000, 90000, 8, 2),
	SIGNALUM(800000, 9500, 105000, 18, 3),
	LUMIUM(1500000, 16000, 120000, 12, 3),
	ENDERIUM(2300000, 32000, 160000, 24, 4),
	NUCLEAR(5200000, 40000, 200000, 64, 4),
	QUANTUM(9600000, -1, 314000, 80, 5);

	public final int storage, transfer, buffer, devices, cells;
	
	private LibTier(int storage, int transfer, int buffer, int devices, int interfaces) {
		this.storage = storage;
		this.transfer = transfer;
		this.buffer = buffer;
		this.devices = devices;
		this.cells = interfaces;
	}
	
	private static final LibTier[] tiers4 = new LibTier[] {LEADSTONE, INVAR, ELECTRUM, ENDERIUM};
	
	public static LibTier getTier4(int val) {
		return tiers4[val];
	}
	
	public static LibTier getTier8(int val) {
		return values()[val];
	}
	
}
