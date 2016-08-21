package io.github.phantamanta44.spaceres.energy;


public interface INetworkable {
	
	ResonanceNetwork getNetwork();
	
	void setNetwork(ResonanceNetwork network);
	
	interface INetworkUpdateHook {
		
		void onNetworkUpdate();
		
	}

}
