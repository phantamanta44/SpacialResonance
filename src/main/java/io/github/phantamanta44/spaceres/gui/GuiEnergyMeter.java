package io.github.phantamanta44.spaceres.gui;

import io.github.phantamanta44.spaceres.gui.component.GCEnergyReservoir;
import io.github.phantamanta44.spaceres.gui.component.GuiComponent;
import io.github.phantamanta44.spaceres.lib.LibResource;
import io.github.phantamanta44.spaceres.util.IDeepEnergyStorage;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class GuiEnergyMeter extends GuiTile {

    public GuiEnergyMeter(InventoryPlayer player, TileEntity tile) {
        if (!(tile instanceof IDeepEnergyStorage))
            throw new IllegalArgumentException("Supplied tile isn't an energy storage!");
        IDeepEnergyStorage storage = (IDeepEnergyStorage)tile;
        comps.add(new GCEnergyReservoir(xSize / 2 - 84, ySize / 2 - 13, 168, 29, storage));
        resLoc = LibResource.GUI_ENERGY_METER;
    }

    @Override
    protected void drawForeground(int mX, int mY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        for (GuiComponent comp : comps)
            comp.render(mc, this);
        for (GuiComponent comp : comps)
            comp.mouseOver(mc, this, mX - guiLeft, mY - guiTop);
    }

}
