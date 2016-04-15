package io.github.phantamanta44.spaceres.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

public class DummyContainer extends Container {
	
	public DummyContainer(InventoryPlayer inv, TileEntity tile) {
		super();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
