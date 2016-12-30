package io.github.phantamanta44.spaceres.item;

import net.minecraft.item.Item;

public final class SRItems {

    public static Item itemRes;
    public static Item itemMat;

    public static void init() {
        itemRes = new ItemResource();
        itemMat = new ItemMaterial();
    }

}
