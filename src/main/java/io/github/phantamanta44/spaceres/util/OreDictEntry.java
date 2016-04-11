package io.github.phantamanta44.spaceres.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictEntry {

	public final List<ItemStack> ores;
	public final List<ItemStack> ingots;
	public final List<ItemStack> nuggets;
	public final List<ItemStack> dusts;
	
	public OreDictEntry(List<ItemStack> oreList, List<ItemStack> ingotList, List<ItemStack> nuggetList, List<ItemStack> dustList) {
		ores = oreList;
		ingots = ingotList;
		nuggets = nuggetList;
		dusts = dustList;
	}
	
	public OreDictEntry(String oreName) {
		ores = OreDictionary.getOres("ore" + oreName, true);
		ingots = OreDictionary.getOres("ingot" + oreName, true);
		nuggets = OreDictionary.getOres("nugget" + oreName, true);
		dusts = OreDictionary.getOres("dust" + oreName, true);
	}
	
	public ItemStack getBaseOre() {
		if (!ores.isEmpty())
			return ores.get(0);
		return null;
	}
	
	public ItemStack getBaseIngot() {
		if (!ingots.isEmpty())
			return ingots.get(0);
		return null;
	}
	
	public ItemStack getBaseNugget() {
		if (!nuggets.isEmpty())
			return nuggets.get(0);
		return null;
	}
	
	public ItemStack getBaseDust() {
		if (!dusts.isEmpty())
			return dusts.get(0);
		return null;
	}
	
}
