package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.item.block.ItemBlockResonanceDevice;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.tile.TileNetworkable;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockResonanceChannel extends BlockMod implements ITileEntityProvider {
	
	public BlockResonanceChannel() {
		super(Material.glass);
		setHardness(2.3F);
		setResistance(4.5F);
		setBlockName(LibLang.BLOCK_RES_CHAN_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockResonanceDevice.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileResonanceChannel();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		((TileNetworkable)world.getTileEntity(x, y, z)).updateNetwork();
	}
	
}
