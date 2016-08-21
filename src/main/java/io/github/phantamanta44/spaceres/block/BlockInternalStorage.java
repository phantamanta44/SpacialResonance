package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.block.base.BlockModSubs;
import io.github.phantamanta44.spaceres.block.base.ItemBlockState;
import io.github.phantamanta44.spaceres.item.base.ItemBlockPersistentState;
import io.github.phantamanta44.spaceres.item.block.ItemBlockInternalStorage;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.TileInternalStorage;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cofh.api.block.IDismantleable;
import cpw.mods.fml.common.registry.GameRegistry;

@ItemBlockState
public class BlockInternalStorage extends BlockModSubs implements ITileEntityProvider, IDismantleable {

	public static final int LEAD = 0, INVAR = 1, ELECTRUM = 2, SIGN = 3, LUM = 4, ENDER = 5, NUKE = 6, QNT = 7;
	
	public BlockInternalStorage() {
		super(Material.iron, 8);
		setHardness(5F);
		setResistance(7.5F);
		setBlockName(LibLang.BLOCK_IFC_INT_STG_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockInternalStorage.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List stacks) {
		for (int i = 0; i < subblockCount; i++) {
			stacks.add(new ItemStack(item, 1, i));
			ItemStack filled = new ItemStack(item, 1, i);
			NBTTagCompound tag = ((ItemBlockPersistentState)item).getStoredBlockState(filled);
			tag.setLong(LibNBT.ENERGY, tag.getLong(LibNBT.ENERGY_MAX));
			stacks.add(filled);
		}
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

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops) {
		this.removedByPlayer(world, player, x, y, z, true);
		if (!returnDrops)
			compiledDrops.forEach(is -> PhantaUtil.dropItem(world, x, y, z, is));
		return compiledDrops;
	}

	@Override
	public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z) {
		return true;
	}
	
}
