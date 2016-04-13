package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.item.block.ItemBlockResonanceDevice;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.TileAcceptionBus;
import io.github.phantamanta44.spaceres.tile.TileNetworkable;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAcceptionBus extends BlockModSubs implements ITileEntityProvider {

	public static final int LEAD = 0, INVAR = 1, ELECTRUM = 2, ENDER = 3;
	
	public BlockAcceptionBus() {
		super(Material.iron, 5);
		setHardness(5F);
		setResistance(7.5F);
		setBlockName(LibLang.BLOCK_BUS_ACC_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockResonanceDevice.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAcceptionBus(LibTier.getTier4(meta));
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		((TileNetworkable)world.getTileEntity(x, y, z)).updateNetwork();
	}
	
}
