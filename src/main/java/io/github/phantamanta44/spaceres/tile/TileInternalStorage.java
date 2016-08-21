package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.TileMod;
import io.github.phantamanta44.spaceres.util.IDeepEnergyStorage;
import io.github.phantamanta44.spaceres.util.impl.EnergyProxy;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyStorage;

public class TileInternalStorage extends TileMod implements IDeepEnergyStorage {

	private long stored, max;
	private IEnergyStorage energyProxy;
	
	public TileInternalStorage() {
		this(LibTier.LEADSTONE);
	}
	
	public TileInternalStorage(LibTier tier) {
		this.stored = 0L;
		this.max = tier.storage;
		this.energyProxy = new EnergyProxy(
			() -> (int)Math.min(this.stored, Integer.MAX_VALUE),
			e -> this.stored += e,
			() -> (int)Math.min(this.max, Integer.MAX_VALUE)
		);
		this.init = true;
	}
	
	@Override
	protected void tick() {
		if (!worldObj.isRemote)
			markForUpdate();
	}
	
	public int requestEnergy(int amt) {
		return energyProxy.extractEnergy(amt, false);
	}
	
	public int offerEnergy(int amt) {
		return energyProxy.receiveEnergy(amt, false);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setLong(LibNBT.ENERGY, stored);
		tag.setLong(LibNBT.ENERGY_MAX, max);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.stored = tag.getLong(LibNBT.ENERGY);
		this.max = tag.getLong(LibNBT.ENERGY_MAX);
	}
	
	@Override
	public long getTauStored() {
		return stored;
	}
	
	@Override
	public long getTauMax() {
		return max;
	}

}
