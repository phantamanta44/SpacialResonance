package io.github.phantamanta44.spaceres.block;

import net.minecraft.block.Block;

public final class SRBlocks {
	
	public static Block resChan;
	public static Block busAcc;
	public static Block busExp;
	public static Block dist;
	public static Block accum;
	public static Block ifcInt;
	public static Block intStg;
	public static Block ore;
	public static Block comp;
	
	public static void init() {
		resChan = new BlockResonanceChannel();
		busAcc = new BlockAcceptionBus();
		busExp = new BlockExportionBus();
		dist = new BlockDistributionProcessor();
		accum = new BlockAccumulationProcessor();
		ifcInt = new BlockInternalInterface();
		intStg = new BlockInternalStorage();
		ore = new BlockOre();
		comp = new BlockCompressed();
	}
	
}
