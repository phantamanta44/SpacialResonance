package io.github.phantamanta44.spaceres.lib;

public enum LibTier {

    LEADSTONE(12000000L, 4000, 80000, 3, 1),
    INVAR(50000000L, 15000, 300000, 6, 2),
    ELECTRUM(200000000L, 60000, 1200000, 8, 2),
    SIGNALUM(780000000L, 250000, 5000000, 18, 3),
    LUMIUM(3100000000L, 950000, 19000000, 12, 3),
    ENDERIUM(12500000000L, 3750000, 75000000, 24, 4),
    NUCLEAR(50000000000L, 15000000, 300000000, 64, 4),
    QUANTUM(200000000000L, -1, 1200000000, 80, 5);

    public final long storage;
    public final int transfer, buffer, devices, cells;

    private LibTier(long storage, int transfer, int buffer, int devices, int cells) {
        this.storage = storage;
        this.transfer = transfer;
        this.buffer = buffer;
        this.devices = devices;
        this.cells = cells;
    }

    private static final LibTier[] tiers4 = new LibTier[] {LEADSTONE, INVAR, ELECTRUM, ENDERIUM};

    public static LibTier getTier4(int val) {
        return tiers4[val];
    }

    public static LibTier getTier8(int val) {
        return values()[val];
    }

}
