package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.block.base.BlockModSubs;
import io.github.phantamanta44.spaceres.item.block.ItemBlockResonanceDevice;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.lib.LibLang;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockCompressed extends BlockModSubs {

	public static final int COBALT = 0;
	
	public BlockCompressed() {
		super(Material.iron, 1);
		setHardness(4.4F);
		setResistance(5F);
		setHarvestLevel(LibCore.TOOL_PICK, 2);
		setBlockName(LibLang.BLOCK_COMP_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockResonanceDevice.class, name);
		return super.setBlockName(name);
	}
	
}
