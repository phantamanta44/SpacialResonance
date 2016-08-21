package io.github.phantamanta44.spaceres.item.block;

import io.github.phantamanta44.spaceres.item.base.ItemBlockWithMetadataAndName;
import io.github.phantamanta44.spaceres.lib.LibLang;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockStatelessDevice extends ItemBlockWithMetadataAndName {

	public ItemBlockStatelessDevice(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String nameKey = getUnlocalizedNameInefficiently(stack);
		return String.format("%s %s",
			LibLang.get(LibLang.BLOCK_TIER_NAME[stack.getItemDamage()]),
			LibLang.get(nameKey.substring(0, nameKey.length() - (stack.getItemDamage() <= 9 ? 1 : 2)) + ".name")
		);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		switch (stack.getItemDamage()) {
			case 5:
			case 6:
				return EnumRarity.rare;
			case 7:
				return EnumRarity.epic;
			default:
				return EnumRarity.common;
		}
	}

}
