package io.github.phantamanta44.spaceres.item.block;

import io.github.phantamanta44.spaceres.block.base.BlockModSubs;
import io.github.phantamanta44.spaceres.lib.LibNBT;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;

public class ItemBlockEnergyStorageDevice extends ItemBlockPersistentDevice implements IEnergyContainerItem {
	
	public ItemBlockEnergyStorageDevice(Block block) {
		super(block);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		NBTTagCompound tag = getStoredBlockState(stack);
		return 1D - (double)tag.getLong(LibNBT.ENERGY) / (double)tag.getLong(LibNBT.ENERGY_MAX);
	}

	@Override
	public int receiveEnergy(ItemStack stack, int amt, boolean sim) {
		NBTTagCompound tag = getStoredBlockState(stack);
		long stored = tag.getLong(LibNBT.ENERGY), max = tag.getLong(LibNBT.ENERGY_MAX);
		int toTransfer = (int)Math.min(amt, max - stored);
		if (!sim)
			tag.setLong(LibNBT.ENERGY, stored + toTransfer);
		return toTransfer;
	}

	@Override
	public int extractEnergy(ItemStack stack, int amt, boolean sim) {
		NBTTagCompound tag = getStoredBlockState(stack);
		long stored = tag.getLong(LibNBT.ENERGY);
		int toTransfer = (int)Math.min(amt, stored);
		if (!sim)
			tag.setLong(LibNBT.ENERGY, stored - toTransfer);
		return toTransfer;
	}

	@Override
	public int getEnergyStored(ItemStack stack) {
		return (int)Math.min(getStoredBlockState(stack).getLong(LibNBT.ENERGY), Integer.MAX_VALUE);
	}

	@Override
	public int getMaxEnergyStored(ItemStack stack) {
		return (int)Math.min(getStoredBlockState(stack).getLong(LibNBT.ENERGY_MAX), Integer.MAX_VALUE);
	}

}
