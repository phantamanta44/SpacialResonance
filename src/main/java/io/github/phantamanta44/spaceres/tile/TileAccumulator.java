package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import net.minecraft.nbt.NBTTagCompound;

public class TileAccumulator extends TileNetworkable {

	private int devices;
	private boolean equalMode = false;
	
	public TileAccumulator() {
		// NO-OP
	}
	
	public TileAccumulator(LibTier tier) {
		devices = tier.devices;
	}
	
	@Override
	protected void tick() {
		super.tick();
	}

	public int request(int amt) {
		// TODO Request energy from interfaces
		return amt;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger(LibNBT.DEV_COUNT, devices);
		tag.setBoolean(LibNBT.EQUAL_MODE, equalMode);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		devices = tag.getInteger(LibNBT.DEV_COUNT);
		equalMode = tag.getBoolean(LibNBT.EQUAL_MODE);
	}

}
