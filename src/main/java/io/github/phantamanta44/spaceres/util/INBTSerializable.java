package io.github.phantamanta44.spaceres.util;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTSerializable {

	void writeToNBT(NBTTagCompound tag);
	
	void readFromNBT(NBTTagCompound tag);
	
}
