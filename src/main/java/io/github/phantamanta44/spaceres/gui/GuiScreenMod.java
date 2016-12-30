package io.github.phantamanta44.spaceres.gui;

import io.github.phantamanta44.spaceres.gui.component.GuiComponent;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.lib.LibLang;

import java.util.Collection;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GuiScreenMod extends GuiScreen {

    protected ResourceLocation resLoc;
    protected String invName;
    protected Collection<GuiComponent> comps = Sets.newHashSet();
    protected int xSize = 176, ySize = 166;
    protected int guiLeft, guiTop;

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void drawScreen(int mX, int mY, float z) {
        drawBackground();
        super.drawScreen(mX, mY, z);
        GL11.glPushMatrix();
        GL11.glTranslatef(guiLeft, guiTop, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        drawForeground(mX, mY);
        GL11.glPopMatrix();
    }

    protected void drawForeground(int mX, int mY) {
        fontRendererObj.drawString(LibLang.get(LibLang.PLAYER_INV), 8, this.ySize - 96 + 2, 4210752);
        String resolvedName = LibLang.get(invName);
        int nameXPos = xSize / 2 - fontRendererObj.getStringWidth(resolvedName) / 2;
        fontRendererObj.drawString(resolvedName, nameXPos, 6, LibCore.GUI_FONT_COLOR);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        for (GuiComponent comp : comps)
            comp.render(mc, this);

        for (GuiComponent comp : comps)
            comp.mouseOver(mc, this, mX - guiLeft, mY - guiTop);
    }

    protected void drawBackground() {
        this.drawDefaultBackground();
        mc.renderEngine.bindTexture(resLoc);
        int x = width / 2 - xSize / 2, y = height / 2 - ySize / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    protected void drawHoveringText(String string, int x, int y) {
        func_146283_a(Lists.newArrayList(string), x, y);
        RenderHelper.enableGUIStandardItemLighting();
    }

}
