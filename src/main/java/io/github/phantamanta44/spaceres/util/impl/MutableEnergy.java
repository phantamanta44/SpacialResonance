package io.github.phantamanta44.spaceres.util.impl;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.util.INBTSerializable;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyStorage;

public class MutableEnergy implements IEnergyStorage, INBTSerializable {

	protected int rf, max;
	
	public MutableEnergy(int max) {
		this.max = max;
		this.rf = 0;
	}
	
	public MutableEnergy(int max, int init) {
		this.max = max;
		this.rf = init;
	}
	
	@Override
	public int receiveEnergy(int amt, boolean sim) {
		int toTransfer = Math.min(amt, max - rf);
		if (!sim)
			rf += toTransfer;
		return toTransfer;
	}

	@Override
	public int extractEnergy(int amt, boolean sim) {
		int toTransfer = Math.min(amt, rf);
		if (!sim)
			rf -= toTransfer;
		return toTransfer;
	}

	@Override
	public int getEnergyStored() {
		return rf;
	}

	@Override
	public int getMaxEnergyStored() {
		return max;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger(LibNBT.ENERGY, this.rf);
		tag.setInteger(LibNBT.ENERGY_MAX, this.max);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.rf = tag.getInteger(LibNBT.ENERGY);
		this.max = tag.getInteger(LibNBT.ENERGY_MAX);
	}

}
