package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.IStorageInterface;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;

import java.util.List;
import java.util.stream.Collectors;

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
		if (equalMode) {
			List<IStorageInterface> targets = network.stream()
					.filter(u -> u instanceof IStorageInterface)
					.map(u -> (IStorageInterface)u)
					.limit(devices)
					.collect(Collectors.toList());
			if (targets.isEmpty())
				return 0;
			int perTarget = (int)Math.floor((float)Math.min(amt, maxTrans) / (float)targets.size());
			int totalTrans = 0;
			for (IStorageInterface target : targets)
				totalTrans += target.offerEnergy(perTarget);
			return totalTrans;
		} else {
			IStorageInterface target = (IStorageInterface)network.findUnit(u ->
					u instanceof IStorageInterface && isNotFull((IStorageInterface)u));
			if (target == null)
				return 0;
			return target.offerEnergy(Math.min(amt, maxTrans));
		}
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
	
	private static boolean isNotFull(IStorageInterface stInt) {
		return stInt.getEnergyReservoir() < stInt.getReservoirSize();
	}

}
