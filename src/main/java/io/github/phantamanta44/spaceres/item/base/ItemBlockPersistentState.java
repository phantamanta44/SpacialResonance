package io.github.phantamanta44.spaceres.item.base;

import io.github.phantamanta44.spaceres.lib.LibNBT;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemBlockPersistentState extends ItemBlockWithMetadataAndName {

	public ItemBlockPersistentState(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		if (placed && stack.getTagCompound() != null && stack.getTagCompound().hasKey(LibNBT.ITEM_BLOCK_STATE)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			tile.readFromNBT(stack.getTagCompound().getCompoundTag(LibNBT.ITEM_BLOCK_STATE));
		}
		return placed;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someFlag) {
		List<String> tooltip = getTooltip(stack, player);
		if (tooltip != null && !tooltip.isEmpty())
			info.addAll(tooltip);
	}
	
	protected List<String> getTooltip(ItemStack stack, EntityPlayer player) {
		return null;
	}

}
