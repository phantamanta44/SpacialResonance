package io.github.phantamanta44.spaceres.lib;

import net.minecraft.util.ResourceLocation;

public class LibResource {

    public static final String GUI_KEY = LibCore.MODPREF + "textures/gui/";
    public static final ResourceLocation GUI_ENERGY_METER = new ResourceLocation(GUI_KEY + "energy_meter.png");;

    public static final String GC_KEY = GUI_KEY + "component/";
    public static final ResourceLocation TEX_GC_BASE = new ResourceLocation(GC_KEY + "base.png");

    public static final String MODEL_KEY = LibCore.MODPREF + "textures/model/";

    public static final String MODEL_BLOCK_KEY = MODEL_KEY + "block/";
    public static final ResourceLocation MODEL_BLOCK_RES_CHANNEL = new ResourceLocation(MODEL_BLOCK_KEY + "channel.png");

}
