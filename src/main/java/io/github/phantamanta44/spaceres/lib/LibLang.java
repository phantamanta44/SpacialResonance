package io.github.phantamanta44.spaceres.lib;

import net.minecraft.util.StatCollector;

public class LibLang {

	public static final String CREATIVE_TAB_NAME = "tab_" + LibCore.MODID;
	
	public static final String INF_KEY = LibCore.MODID + ".itemInfo.";
	public static final String INF_NO_TAG = INF_KEY + "noTag";
	public static final String INF_CHARGE = INF_KEY + "charge";
	public static final String INF_EXPAND = INF_KEY + "expand";
	
	public static final String TT_KEY = LibCore.MODID + ".tooltip.";
	public static final String TT_ENERGY = TT_KEY + "energyStored";
	public static final String TT_EMPTY = TT_KEY + "empty";
	
	public static final String PLAYER_INV = "container.inventory";

	public static final String BLOCK_RES_CHAN_NAME = "blockResChannel";
	public static final String BLOCK_BUS_ACC_NAME = "blockBusAccept";
	public static final String BLOCK_BUS_EXP_NAME = "blockBusExport";
	public static final String BLOCK_DIST_NAME = "blockDistributor";
	public static final String BLOCK_ACCUM_NAME = "blockAccumulator";
	public static final String BLOCK_ORE_NAME = "blockOre";
	public static final String BLOCK_COMP_NAME = "blockCompressed";

	public static final String ITEM_RESOURCE_NAME = "itemResource";
	public static final String ITEM_MATERIAL_NAME = "itemMaterial";

	public static final String GUI_KEY = LibCore.MODID + ".gui.";
	
	public static String get(String key) {
		return StatCollector.translateToLocal(key);
	}
	
}
