package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import net.minecraft.nbt.NBTTagCompound;

public class TileDistributor extends TileNetworkable {

	private int maxTrans, devices;
	private boolean equalMode = false;
	
	public TileDistributor() {
		// NO-OP
	}
	
	public TileDistributor(LibTier tier) {
		maxTrans = tier.transfer;
		devices = tier.devices;
	}
	
	public int distribute(int amt) {
		// TODO Distribute stored energy to interfaces
		return amt;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger(LibNBT.ENERGY_RATE, maxTrans);
		tag.setInteger(LibNBT.DEV_COUNT, devices);
		tag.setBoolean(LibNBT.EQUAL_MODE, equalMode);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		maxTrans = tag.getInteger(LibNBT.ENERGY_RATE);
		devices = tag.getInteger(LibNBT.DEV_COUNT);
		equalMode = tag.getBoolean(LibNBT.EQUAL_MODE);
	}

}
