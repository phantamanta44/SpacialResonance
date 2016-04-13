package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.energy.ResonanceNetwork;

public class TileAccumulator extends TileNetworkable {

	private ResonanceNetwork network;

	@Override
	protected void tick() {
		
	}
	
	@Override
	public ResonanceNetwork getNetwork() {
		return network;
	}

}
