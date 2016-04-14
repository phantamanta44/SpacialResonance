package io.github.phantamanta44.spaceres.block.base;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.util.IconHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMod extends Block {
	
	public BlockMod(Material material) {
		super(material);
		addToCreative();
	}

	@Override
	public Block setBlockName(String name) {
		if (GameRegistry.findBlock(LibCore.MODID, name) == null)
			GameRegistry.registerBlock(this, name);
		return super.setBlockName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		blockIcon = IconHelper.forBlock(registry, this);
	}
	
	public void addToCreative() {
		setCreativeTab(SpaceRes.tabSpaceRes);
	}

}
