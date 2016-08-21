package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.block.base.BlockModSubs;
import io.github.phantamanta44.spaceres.item.block.ItemBlockResource;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.lib.LibLang;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockOre extends BlockModSubs {

	public static final int COBALT = 0;
	
	public BlockOre() {
		super(Material.rock, 1);
		setHardness(3.7F);
		setResistance(5F);
		setHarvestLevel(LibCore.TOOL_PICK, 3);
		setBlockName(LibLang.BLOCK_ORE_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockResource.class, name);
		return super.setBlockName(name);
	}
	
}
