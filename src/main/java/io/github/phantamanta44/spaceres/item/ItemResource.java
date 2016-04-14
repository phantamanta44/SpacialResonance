package io.github.phantamanta44.spaceres.item;

import io.github.phantamanta44.spaceres.item.base.ItemModSubs;
import io.github.phantamanta44.spaceres.lib.LibLang;

public class ItemResource extends ItemModSubs {

	public static final int INGOT_COBALT = 0, DUST_COBALT = 1, NUGGET_COBALT = 2;
	
	public ItemResource() {
		super(3);
		setUnlocalizedName(LibLang.ITEM_RESOURCE_NAME);
	}

}
