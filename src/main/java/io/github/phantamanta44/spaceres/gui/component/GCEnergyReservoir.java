package io.github.phantamanta44.spaceres.gui.component;

import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibResource;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import cofh.api.energy.IEnergyStorage;

public class GCEnergyReservoir extends GuiComponent {
	
	private static final int[] COLOUR_A = new int[] {17, 111, 177}, COLOUR_B = new int[] {42, 157, 237};
	private IEnergyStorage esource;
	private float wave = 0.4F;
	
	public GCEnergyReservoir(int x, int y, int width, int length, IEnergyStorage ec) {
		super(x, y, width, length);
		esource = ec;
	}

	@Override
	public void render(Minecraft mc, GuiScreen gui) {
		float ePercent = (float)esource.getEnergyStored() / (float)esource.getMaxEnergyStored();
		int indOffset = (int)((float)(width - 2) * ePercent);
		mc.renderEngine.bindTexture(LibResource.TEX_GC_BASE);
		gui.drawTexturedModalRect(x, y, 0, 0, 1, 1);
		gui.drawTexturedModalRect(x + width - 1, y, 2, 0, 1, 1);
		gui.drawTexturedModalRect(x + width - 1, y + height - 1, 2, 2, 1, 1);
		gui.drawTexturedModalRect(x, y + height - 1, 0, 2, 1, 1);
		for (int i = 1; i < height - 1; i++) {
			gui.drawTexturedModalRect(x, y + i, 0, 1, 1, 1);
			gui.drawTexturedModalRect(x + width - 1, y + i, 2, 1, 1, 1);
			for (int j = 1; j < width - 1; j++)
				gui.drawTexturedModalRect(x + j, y + i, 1, 1, 1, 1);
		}
		for (int i = 1; i < width - 1; i++) {
			gui.drawTexturedModalRect(x + i, y, 1, 0, 1, 1);
			gui.drawTexturedModalRect(x + i, y + height - 1, 1, 2, 1, 1);
			if (i <= indOffset) {
				gui.drawRect(x + i, y + 1, x + i + 1, y + height - 1, getColour(i, width - 2));
				GL11.glColor4f(1F, 1F, 1F, 1F);
			}
		}
	}
	
	@Override
	public void mouseOver(Minecraft mc, GuiScreen gui, int mX, int mY) {
		if (PhantaUtil.isMouseOver(x, y, width, height, mX, mY))
			drawHoveringText(gui, String.format("%d / %d RF", esource.getEnergyStored(), esource.getMaxEnergyStored()), mX, mY);
	}
	
	private static int getColour(int n, int max) {
		double frac = (double)n / (double)max;
		int r = (int)(COLOUR_B[0] * sigmoid(frac) + COLOUR_A[0] * sigmoid(1D - frac));
		int g = (int)(COLOUR_B[1] * sigmoid(frac) + COLOUR_A[1] * sigmoid(1D - frac));
		int b = (int)(COLOUR_B[2] * sigmoid(frac) + COLOUR_A[2] * sigmoid(1D - frac));
		return 0xFF000000 | (r << 16) | (g << 8) | b;
	}
	
	private static double sigmoid(double n) {
		return 1D / (1D + Math.pow(Math.E, -12D * (n - 0.5D)));
	}

}
