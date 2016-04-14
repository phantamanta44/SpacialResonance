package io.github.phantamanta44.spaceres.item;

import io.github.phantamanta44.spaceres.item.base.ItemModSubs;
import io.github.phantamanta44.spaceres.lib.LibLang;

public class ItemMaterial extends ItemModSubs {

	public static final int TAU_CONV = 0, RES_COND = 1;
	
	public ItemMaterial() {
		super(2);
		setUnlocalizedName(LibLang.ITEM_MATERIAL_NAME);
	}
	
}
