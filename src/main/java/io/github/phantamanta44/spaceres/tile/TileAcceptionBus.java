package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.energy.INetworkable;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.IEnergyTile;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import io.github.phantamanta44.spaceres.util.impl.ThrottledEnergy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;

public class TileAcceptionBus extends TileNetworkable implements IEnergyReceiver, IEnergyTile {
	
	private ThrottledEnergy rfBuffer;
	
	public TileAcceptionBus() {
		rfBuffer = new ThrottledEnergy(0);
	}
	
	public TileAcceptionBus(LibTier tier) {
		this.rfBuffer = new ThrottledEnergy(tier.buffer, 0, tier.transfer, 0);
	}
	
	@Override
	protected void tick() {
		super.tick();
		if (rfBuffer.getEnergyStored() > 0) {
			INetworkable dist = network.findUnit(u -> u instanceof TileDistributor);
			if (dist != null)
				rfBuffer.extractEnergyTrue(((TileDistributor)dist).distribute(rfBuffer.getEnergyStored()), false);
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int amt, boolean sim) {
		return rfBuffer.receiveEnergy(amt, sim);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return rfBuffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return rfBuffer.getMaxEnergyStored();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		rfBuffer.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		rfBuffer.readFromNBT(tag);
	}
	
	@Override
	public IEnergyStorage getEnergyStorage() {
		return rfBuffer;
	}
	
}
