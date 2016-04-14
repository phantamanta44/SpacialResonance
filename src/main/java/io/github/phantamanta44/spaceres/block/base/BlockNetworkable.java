package io.github.phantamanta44.spaceres.block.base;

import io.github.phantamanta44.spaceres.energy.ResonanceNetwork;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class BlockNetworkable extends BlockModSubs implements ITileEntityProvider {

	public BlockNetworkable(Material material, int blocks) {
		super(material, blocks);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		ResonanceNetwork network = ((TileNetworkable)world.getTileEntity(x, y, z)).getNetwork();
		super.breakBlock(world, x, y, z, block, meta);
		if (network != null)
			network.rescan();
	}
	
}
