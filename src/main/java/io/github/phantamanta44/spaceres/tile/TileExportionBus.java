package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.energy.INetworkable;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.IEnergyTile;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import io.github.phantamanta44.spaceres.util.impl.ThrottledEnergy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;

public class TileExportionBus extends TileNetworkable implements IEnergyProvider, IEnergyTile {
	
	private ThrottledEnergy rfBuffer;
	
	public TileExportionBus() {
		rfBuffer = new ThrottledEnergy(0);
	}
	
	public TileExportionBus(LibTier tier) {
		this.rfBuffer = new ThrottledEnergy(tier.buffer, 0, 0, tier.transfer);
	}
	
	@Override
	protected void tick() {
		super.tick();
		if (!worldObj.isRemote && rfBuffer.getEnergyStored() != rfBuffer.getMaxEnergyStored() && network != null) {
			INetworkable acc = network.findUnit(u -> u instanceof TileAccumulator);
			if (acc != null)
				rfBuffer.receiveEnergyTrue(((TileAccumulator)acc).request(rfBuffer.getMaxEnergyStored() - rfBuffer.getEnergyStored()), false);
		}
		if (!worldObj.isRemote && rfBuffer.getEnergyStored() > 0) {
			final Map<IEnergyReceiver, ForgeDirection> receivers = new HashMap<>();
			PhantaUtil.iterAdjTiles(this, (t, d) -> {
				if (t instanceof IEnergyReceiver)
					receivers.put((IEnergyReceiver)t, d.getOpposite());
			});
			int unitAmt = (int)Math.floor((float)rfBuffer.getOutputRate() / (float)receivers.size());
			receivers.forEach((r, d) -> rfBuffer.extractEnergyTrue(r.receiveEnergy(d, Math.min(rfBuffer.getEnergyStored(), unitAmt), false), false));
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

	@Override
	public IEnergyStorage getEnergyStorage() {
		return rfBuffer;
	}

}
