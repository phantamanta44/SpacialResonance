package io.github.phantamanta44.spaceres.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerMod<T extends IInventory> extends Container {

    protected T tile;

    protected void addPlayerInventory(InventoryPlayer ipl) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(ipl, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }

        for (int i = 0; i < 9; ++i)
            this.addSlotToContainer(new Slot(ipl, i, 8 + i * 18, 142));
    }

    public T getTile() {
        return tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public abstract ItemStack transferStackInSlot(EntityPlayer player, int slot);

}
