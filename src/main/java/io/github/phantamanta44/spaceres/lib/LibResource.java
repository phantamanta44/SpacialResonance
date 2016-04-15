package io.github.phantamanta44.spaceres.lib;

import net.minecraft.util.ResourceLocation;

public class LibResource {

	public static final String GUI_KEY = LibCore.MODPREF + "textures/gui/";
	public static final ResourceLocation GUI_ENERGY_METER = new ResourceLocation(GUI_KEY + "energy_meter.png");;
	
	public static final String GC_KEY = GUI_KEY + "component/";
	public static final ResourceLocation TEX_GC_BASE = new ResourceLocation(GC_KEY + "base.png");
	
}
