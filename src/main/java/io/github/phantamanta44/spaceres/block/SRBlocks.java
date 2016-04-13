package io.github.phantamanta44.spaceres.block;

import net.minecraft.block.Block;


public final class SRBlocks {
	
	public static Block resChan;
	
	public static void init() {
		resChan = new BlockResonanceChannel();
	}
	
}
