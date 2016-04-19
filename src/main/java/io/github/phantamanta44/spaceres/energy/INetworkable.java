package io.github.phantamanta44.spaceres.energy;


public interface INetworkable {
	
	public ResonanceNetwork getNetwork();
	
	public void setNetwork(ResonanceNetwork network);
	
	public interface INetworkUpdateHook {
		
		public void onNetworkUpdate();
		
	}

}
