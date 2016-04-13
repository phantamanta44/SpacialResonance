package io.github.phantamanta44.spaceres.lib;

public enum LibTier {

	LEADSTONE,
	INVAR,
	ELECTRUM,
	SIGNALUM,
	LUMIUM,
	ENDERIUM,
	NUCLEAR,
	QUANTUM;

	private static final LibTier[] tiers4 = new LibTier[] {LEADSTONE, INVAR, ELECTRUM, ENDERIUM};
	
	public static LibTier getTier4(int val) {
		return tiers4[val];
	}
	
	public static LibTier getTier8(int val) {
		return values()[val];
	}
	
}
