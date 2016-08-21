package io.github.phantamanta44.spaceres.util.impl;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.util.INBTSerializable;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyStorage;

public class EnergyProxy implements IEnergyStorage {

	protected IntSupplier getEnergy, getMax;
	protected IntConsumer addEnergy;
	
	public EnergyProxy(IntSupplier getEnergy, IntConsumer addEnergy, IntSupplier getMax) {
		this.getEnergy = getEnergy;
		this.addEnergy = addEnergy;
		this.getMax = getMax;
	}
	
	@Override
	public int receiveEnergy(int amt, boolean sim) {
		int toTransfer = Math.min(amt, getMax.getAsInt() - getEnergy.getAsInt());
		if (!sim)
			addEnergy.accept(toTransfer);
		return toTransfer;
	}

	@Override
	public int extractEnergy(int amt, boolean sim) {
		int toTransfer = Math.min(amt, getEnergy.getAsInt());
		if (!sim)
			addEnergy.accept(-toTransfer);
		return toTransfer;
	}

	@Override
	public int getEnergyStored() {
		return getEnergy.getAsInt();
	}

	@Override
	public int getMaxEnergyStored() {
		return getMax.getAsInt();
	}

}
