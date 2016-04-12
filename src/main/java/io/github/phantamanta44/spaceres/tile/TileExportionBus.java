package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.energy.DeviceAddress;
import io.github.phantamanta44.spaceres.energy.IResonancePacketReceiver;
import io.github.phantamanta44.spaceres.energy.ResonancePacket;
import io.github.phantamanta44.spaceres.energy.ResonancePacket.PacketType;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import io.github.phantamanta44.spaceres.util.impl.ThrottledEnergy;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyProvider;

public class TileExportionBus extends TileMod implements IEnergyProvider, IResonancePacketReceiver {
	
	private ThrottledEnergy rfBuffer;
	private DeviceAddress address = new DeviceAddress("nil");
	private DeviceAddress accum = new DeviceAddress("nil");
	
	public TileExportionBus(LibTier tier) {
		switch (tier) {
		case LEADSTONE:
			this.rfBuffer = new ThrottledEnergy(400, 0, 00, 80);
			break;
		case INVAR:
			this.rfBuffer = new ThrottledEnergy(5000, 0, 0, 400);
			break;
		case ELECTRUM:
			this.rfBuffer = new ThrottledEnergy(12000, 0, 0, 3000);
			break;
		case ENDERIUM:
			this.rfBuffer = new ThrottledEnergy(40000, 0, 0, 12000);
			break;
		default:
			this.rfBuffer = new ThrottledEnergy(8888, 88, 88, 88);
			break;
		}
	}
	
	@Override
	protected void tick() {
		if (rfBuffer.getEnergyStored() != rfBuffer.getMaxEnergyStored()) {
			ResonancePacket reqPacket = new ResonancePacket(
					address, accum, PacketType.EXPORT_REQ,
					rfBuffer.getMaxEnergyStored() - rfBuffer.getEnergyStored()
					);
			PhantaUtil.iterAdjTiles(this, (t, d) -> {
				if (t == null || !(t instanceof IResonancePacketReceiver))
					return;
				((IResonancePacketReceiver)t).receivePacket(reqPacket);
			});
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
	public DeviceAddress getAddress() {
		return address;
	}

	@Override
	public void receivePacket(ResonancePacket packet) {
		if (packet.type == PacketType.EXPORT_ACK && packet.receive(this))
			this.rfBuffer.receiveEnergyTrue(packet.data, false);
	}

}
