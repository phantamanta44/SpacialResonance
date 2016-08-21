package io.github.phantamanta44.spaceres.item.base;

import io.github.phantamanta44.spaceres.lib.LibNBT;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBlockPersistentState extends ItemBlockWithMetadataAndName {

	public ItemBlockPersistentState(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		if (placed)
			world.getTileEntity(x, y, z).readFromNBT(getStoredBlockState(stack));
		return placed;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someFlag) {
		List<String> tooltip = getTooltip(stack, player);
		if (tooltip != null && !tooltip.isEmpty())
			info.addAll(tooltip);
	}
	
	public NBTTagCompound getStoredBlockState(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null)
			stack.setTagCompound(tag = new NBTTagCompound());
		if (!tag.hasKey(LibNBT.ITEM_BLOCK_STATE)) {
			NBTTagCompound state;
			tag.setTag(LibNBT.ITEM_BLOCK_STATE, state = new NBTTagCompound());
			((ITileEntityProvider)field_150939_a).createNewTileEntity(null, stack.getItemDamage()).writeToNBT(state); // TODO Get a hold of the world somehow?
			return state;
		}
		else
			return tag.getCompoundTag(LibNBT.ITEM_BLOCK_STATE);
	}
	
	protected List<String> getTooltip(ItemStack stack, EntityPlayer player) {
		return null;
	}

}
