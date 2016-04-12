package io.github.phantamanta44.spaceres.util.impl;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import net.minecraft.nbt.NBTTagCompound;

public class ThrottledEnergy extends MutableEnergy {

	private int rf, max, inRate, outRate;
	
	public ThrottledEnergy(int max) {
		super(max);
		this.inRate = -1;
		this.outRate = -1;
	}
	
	public ThrottledEnergy(int max, int init) {
		super(max, init);
		this.inRate = -1;
		this.outRate = -1;
	}
	
	public ThrottledEnergy(int max, int init, int inRate, int outRate) {
		super(max, init);
		this.inRate = inRate;
		this.outRate = outRate;
	}
	
	@Override
	public int receiveEnergy(int amt, boolean sim) {
		int toTransfer = Math.min(amt, max - rf);
		if (inRate > -1)
			toTransfer = Math.min(toTransfer, inRate);
		if (!sim)
			rf += toTransfer;
		return toTransfer;
	}

	@Override
	public int extractEnergy(int amt, boolean sim) {
		int toTransfer = Math.min(amt, rf);
		if (outRate > -1)
			toTransfer = Math.min(toTransfer, outRate);
		if (!sim)
			rf -= toTransfer;
		return toTransfer;
	}
	
	public int receiveEnergyTrue(int amt, boolean sim) {
		return super.receiveEnergy(amt, sim);
	}

	public int extractEnergyTrue(int amt, boolean sim) {
		return super.extractEnergy(amt, sim);
	}
	
	public int getIntakeRate() {
		return inRate;
	}
	
	public int getOutputRate() {
		return outRate;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger(LibNBT.ENERGY_RATE_IN, this.inRate);
		tag.setInteger(LibNBT.ENERGY_RATE_OUT, this.outRate);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.inRate = tag.getInteger(LibNBT.ENERGY_RATE_IN);
		this.outRate = tag.getInteger(LibNBT.ENERGY_RATE_OUT);
	}

}
