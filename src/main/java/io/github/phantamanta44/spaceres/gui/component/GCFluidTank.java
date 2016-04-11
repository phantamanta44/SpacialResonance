package io.github.phantamanta44.spaceres.gui.component;

import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibResource;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.IFluidTank;

public class GCFluidTank extends GuiComponent {

	private IFluidInfoHandler tankSource;
	
	public GCFluidTank(int x, int y, int length, IFluidInfoHandler fs) {
		super(x, y, 18, length);
		tankSource = fs;
	}

	@Override
	public void render(Minecraft mc, GuiScreen gui) {
		mc.renderEngine.bindTexture(LibResource.TEX_GC_BASE);
		gui.drawTexturedModalRect(x, y, 0, 0, 1, 1);
		gui.drawTexturedModalRect(x, y + height - 1, 0, 2, 1, 1);
		gui.drawTexturedModalRect(x + width - 1, y, 2, 0, 1, 1);
		gui.drawTexturedModalRect(x + width - 1, y + height - 1, 2, 2, 1, 1);
		for (int i = 1; i < height - 1; i++) {
			gui.drawTexturedModalRect(x, y + i, 0, 1, 1, 1);
			gui.drawTexturedModalRect(x + width - 1, y + i, 2, 1, 1, 1);
		}
		for (int i = 1; i < width - 1; i++) {
			gui.drawTexturedModalRect(x + i, y, 1, 0, 1, 1);
			gui.drawTexturedModalRect(x + i, y + height - 1, 1, 2, 1, 1);
			for (int j = 1; j < height - 1; j++)
				gui.drawTexturedModalRect(x + i, y + j, 1, 1, 1, 1);
		}
		
		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		float t = tankSource.getTank().getFluidAmount(), tMax = tankSource.getTank().getCapacity(), per = t / tMax;
		int sz = (int)(per * (height - 2));
		for (int i = 0; i < sz; i+= 16) {
			int yOpp = Math.min(16, sz - i);
			gui.drawTexturedModelRectFromIcon(x + 1, y + height - (yOpp + 1 + i), tankSource.getTank().getFluid().getFluid().getStillIcon(), 16, yOpp);
		}
		
		mc.renderEngine.bindTexture(LibResource.TEX_GC_BASE);
		int interval = (int)Math.ceil((float)(height - 2) / 4F);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < (i == 1 ? 16 : 7); j++)
				gui.drawTexturedModalRect(x + j + 1, y + interval + (i * interval), 9, 0, 1, 1);
		}
	}
	
	@Override
	public void mouseOver(Minecraft mc, GuiScreen gui, int mX, int mY) {
		if (PhantaUtil.isMouseOver(x, y, width, height, mX, mY))
			drawHoveringText(gui, getFluidText(), mX, mY);
	}

	private String getFluidText() {
		int fa = tankSource.getTank().getFluidAmount();
		return String.format("%s (%d / %d mB)", fa > 0 ? tankSource.getTank().getFluid().getLocalizedName() : LibLang.get(LibLang.TT_EMPTY), fa, tankSource.getTank().getCapacity());
	}
	
	public static interface IFluidInfoHandler {
		
		public IFluidTank getTank();
		
	}
	
}
