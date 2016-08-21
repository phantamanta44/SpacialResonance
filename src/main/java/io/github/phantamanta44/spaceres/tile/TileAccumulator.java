package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.IStorageInterface;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.nbt.NBTTagCompound;

public class TileAccumulator extends TileNetworkable {

	private int maxTrans, devices;
	private boolean equalMode = false;
	
	public TileAccumulator() {
		// NO-OP
	}
	
	public TileAccumulator(LibTier tier) {
		maxTrans = tier.transfer;
		devices = tier.devices;
	}
	
	@Override
	protected void tick() {
		super.tick();
	}

	public int request(int amt) {
		int transLimit = maxTrans > 0 ? Math.min(amt, maxTrans) : amt;
		if (equalMode) {
			List<IStorageInterface> targets = network.stream()
					.filter(u -> u instanceof IStorageInterface)
					.map(u -> (IStorageInterface)u)
					.limit(devices)
					.collect(Collectors.toList());
			if (targets.isEmpty())
				return 0;
			int perTarget = (int)Math.floor((float)transLimit / (float)targets.size());
			int totalTrans = 0;
			for (IStorageInterface target : targets)
				totalTrans += target.requestEnergy(perTarget);
			return totalTrans;
		} else {
			IStorageInterface target = (IStorageInterface)network.findUnit(u ->
					u instanceof IStorageInterface && isNotEmpty((IStorageInterface)u));
			if (target == null)
				return 0;
			return target.requestEnergy(transLimit);
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
	
	private static boolean isNotEmpty(IStorageInterface stInt) {
		return stInt.getEnergyReservoir() > 0;
	}

}
