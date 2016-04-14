package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.energy.INetworkable;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import io.github.phantamanta44.spaceres.util.impl.ThrottledEnergy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyProvider;

public class TileExportionBus extends TileNetworkable implements IEnergyProvider {
	
	private ThrottledEnergy rfBuffer;
	
	public TileExportionBus(LibTier tier) {
		this.rfBuffer = new ThrottledEnergy(tier.buffer, 0, 0, tier.transfer);
	}
	
	@Override
	protected void tick() {
		super.tick();
		if (rfBuffer.getEnergyStored() != rfBuffer.getMaxEnergyStored()) {
			INetworkable acc = network.findUnit(u -> u instanceof TileAccumulator);
			if (acc != null)
				rfBuffer.receiveEnergyTrue(((TileAccumulator)acc).request(rfBuffer.getMaxEnergyStored() - rfBuffer.getEnergyStored()), false);
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int amt, boolean sim) {
		return rfBuffer.extractEnergy(amt, sim);
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
