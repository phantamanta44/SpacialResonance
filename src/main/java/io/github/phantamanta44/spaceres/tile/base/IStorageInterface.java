package io.github.phantamanta44.spaceres.tile.base;

public interface IStorageInterface {

	long getEnergyReservoir();
		
	long getReservoirSize();
	
	int offerEnergy(int amt);
	
	int requestEnergy(int amt);
	
}
