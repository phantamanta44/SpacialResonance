package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.block.base.BlockModSubs;
import io.github.phantamanta44.spaceres.item.block.ItemBlockResonanceDevice;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.TileInternalStorage;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockInternalStorage extends BlockModSubs implements ITileEntityProvider {

public static final int LEAD = 0, INVAR = 1, ELECTRUM = 2, SIGN = 3, LUM = 4, ENDER = 5, NUKE = 6, QNT = 7;
	
	public BlockInternalStorage() {
		super(Material.iron, 8);
		setHardness(5F);
		setResistance(7.5F);
		setBlockName(LibLang.BLOCK_IFC_INT_STG_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockResonanceDevice.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileInternalStorage(LibTier.getTier8(meta));
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
