package io.github.phantamanta44.spaceres.tile.base;

public interface IStorageInterface {

	public int getEnergyReservoir();
	
	public int getReservoirSize();
	
	public int offerEnergy(int amt);
	
	public int requestEnergy(int amt);
	
}
