package io.github.phantamanta44.spaceres.tile.base;

import io.github.phantamanta44.spaceres.energy.INetworkable;
import io.github.phantamanta44.spaceres.energy.ResonanceNetwork;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileNetworkable extends TileMod implements INetworkable {

	protected ResonanceNetwork network;
	
	@Override
	protected void tick() {
		if (!worldObj.isRemote) {
			markForUpdate();
			if (network == null)
				updateNetwork();
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!init && !worldObj.isRemote) {
			updateNetwork();
			init = true;
		}
	}
	
	public void updateNetwork() {
		if (network == null) {
			PhantaUtil.iterAdjTiles(this, (t, d) -> {
				if (t instanceof TileResonanceChannel) {
					TileResonanceChannel target = (TileResonanceChannel)t;
					if (target.getNetwork() != null) {
						if (network == null)
							network = target.getNetwork();
						else
							target.getNetwork().merge(network);
					}
				}
			});
			if (network == null)
				network = new ResonanceNetwork(this);
		}
		network.rescan();
	}
	
	@Override
	public ResonanceNetwork getNetwork() {
		return network;
	}
	
	public void setNetwork(ResonanceNetwork network) {
		this.network = network;
	}
	
}
