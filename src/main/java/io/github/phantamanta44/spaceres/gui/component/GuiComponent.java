package io.github.phantamanta44.spaceres.gui.component;

import java.lang.reflect.Method;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiComponent {

    protected int x, y, width, height;

    public GuiComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHoveringText(GuiScreen gui, String string, int x, int y) {
        try {
            Method m = GuiScreen.class.getDeclaredMethod("func_146283_a", List.class, int.class, int.class);
            m.setAccessible(true);
            m.invoke(gui, Lists.newArrayList(string), x, y);
        } catch (Exception ex) { }
        RenderHelper.enableGUIStandardItemLighting();
    }

    public abstract void render(Minecraft mc, GuiScreen gui);

    public abstract void mouseOver(Minecraft mc, GuiScreen gui, int mX, int mY);

}
