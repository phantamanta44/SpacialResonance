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
    public static final String TT_ENERGY_RATE = TT_KEY + "energyRate";
    public static final String TT_RATE_IN = TT_KEY + "inputRate";
    public static final String TT_RATE_OUT = TT_KEY + "outputRate";
    public static final String TT_EMPTY = TT_KEY + "empty";
    public static final String TT_INF = TT_KEY + "infinite";

    public static final String PLAYER_INV = "container.inventory";

    public static final String BLOCK_RES_CHAN_NAME = "blockResChannel";
    public static final String BLOCK_BUS_ACC_NAME = "blockBusAccept";
    public static final String BLOCK_BUS_EXP_NAME = "blockBusExport";
    public static final String BLOCK_DIST_NAME = "blockDistributor";
    public static final String BLOCK_ACCUM_NAME = "blockAccumulator";
    public static final String BLOCK_IFC_INT_NAME = "blockInterfaceInternal";
    public static final String BLOCK_IFC_INT_STG_NAME = "blockStorageInternal";
    public static final String BLOCK_ORE_NAME = "blockOre";
    public static final String BLOCK_COMP_NAME = "blockCompressed";

    public static final String ITEM_RESOURCE_NAME = "itemResource";
    public static final String ITEM_MATERIAL_NAME = "itemMaterial";

    public static final String GUI_KEY = LibCore.MODID + ".gui.";

    public static final String MSG_KEY = LibCore.MODID + ".chatMsg.";
    public static final String MSG_NET_STATS = MSG_KEY + "netStats";
    public static final String MSG_NET_DEVICES = MSG_KEY + "netDevices";
    public static final String MSG_NET_ENERGY = MSG_KEY + "netEnergy";
    public static final String MSG_NET_CAPACITY = MSG_KEY + "netCapacity";
    public static final String MSG_NET_USED = MSG_KEY + "netUsed";

    public static final String BLOCK_TIER_NAME_KEY = LibCore.MODID + ".tierName.tier";
    public static final String[] BLOCK_TIER_NAME = {
        BLOCK_TIER_NAME_KEY + "0", BLOCK_TIER_NAME_KEY + "1", BLOCK_TIER_NAME_KEY + "2",
        BLOCK_TIER_NAME_KEY + "3", BLOCK_TIER_NAME_KEY + "4", BLOCK_TIER_NAME_KEY + "5",
        BLOCK_TIER_NAME_KEY + "6", BLOCK_TIER_NAME_KEY + "7"
    };

    public static String get(String key) {
        return StatCollector.translateToLocal(key);
    }

}
