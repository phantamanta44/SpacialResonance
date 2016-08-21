package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.block.base.BlockNetworkable;
import io.github.phantamanta44.spaceres.item.block.ItemBlockAcceptionBus;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.TileAcceptionBus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAcceptionBus extends BlockNetworkable {

	public static final int LEAD = 0, INVAR = 1, ELECTRUM = 2, ENDER = 3;
	
	public BlockAcceptionBus() {
		super(Material.iron, 8);
		setHardness(5F);
		setResistance(7.5F);
		setBlockName(LibLang.BLOCK_BUS_ACC_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockAcceptionBus.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAcceptionBus(LibTier.getTier8(meta));
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
		if (player.isSneaking())
			return false;
		if (!world.isRemote)
			player.openGui(SpaceRes.instance, 255, world, x, y, z);
		return true;
	}
	
}
