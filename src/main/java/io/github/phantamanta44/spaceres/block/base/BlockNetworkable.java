package io.github.phantamanta44.spaceres.block.base;

import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.block.IBlockInfo;
import cofh.api.tileentity.ITileInfo;

public abstract class BlockNetworkable extends BlockModSubs implements ITileEntityProvider, IBlockInfo {

	public BlockNetworkable(Material material, int blocks) {
		super(material, blocks);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileNetworkable tile = ((TileNetworkable)world.getTileEntity(x, y, z));
		super.breakBlock(world, x, y, z, block, meta);
		if (tile != null && tile.getNetwork() != null)
			tile.getNetwork().drop(tile);
	}

	@Override
	public void getBlockInfo(IBlockAccess world, int x, int y, int z, ForgeDirection side, EntityPlayer player, List<IChatComponent> info, boolean debug) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof ITileInfo)
			((ITileInfo)tile).getTileInfo(info, side, player, debug);
	}
	
}
