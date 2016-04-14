package io.github.phantamanta44.spaceres.tile.base;

import io.github.phantamanta44.spaceres.energy.INetworkable;
import io.github.phantamanta44.spaceres.energy.ResonanceNetwork;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

public class TileNetworkable extends TileMod implements INetworkable {

	protected ResonanceNetwork network;
	
	@Override
	protected void tick() {
		if (network == null)
			updateNetwork();
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!init) {
			updateNetwork();
			init = true;
		}
	}
	
	public void updateNetwork() {
		if (network == null) {
			PhantaUtil.iterAdjTiles(this, (t, d) -> {
				if (t instanceof INetworkable) {
					if (network == null)
						network = ((INetworkable)t).getNetwork();
					else
						((INetworkable)t).getNetwork().merge(network);
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
