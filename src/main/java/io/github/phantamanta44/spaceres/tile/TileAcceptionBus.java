package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.util.impl.ThrottledEnergy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyReceiver;

public class TileAcceptionBus extends TileNetworkable implements IEnergyReceiver {
	
	private ThrottledEnergy rfBuffer;
	
	public TileAcceptionBus(LibTier tier) {
		switch (tier) {
		case LEADSTONE:
			this.rfBuffer = new ThrottledEnergy(400, 0, 80, 0);
			break;
		case INVAR:
			this.rfBuffer = new ThrottledEnergy(5000, 0, 400, 0);
			break;
		case ELECTRUM:
			this.rfBuffer = new ThrottledEnergy(12000, 0, 3000, 0);
			break;
		case ENDERIUM:
			this.rfBuffer = new ThrottledEnergy(40000, 0, 12000, 0);
			break;
		default:
			this.rfBuffer = new ThrottledEnergy(8888, 88, 88, 88);
			break;
		}
	}
	
	@Override
	protected void tick() {
		if (rfBuffer.getEnergyStored() > 0) {
			// distribute
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
	
}
