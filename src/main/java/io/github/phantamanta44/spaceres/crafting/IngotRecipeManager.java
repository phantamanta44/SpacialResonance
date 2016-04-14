package io.github.phantamanta44.spaceres.crafting;

import static io.github.phantamanta44.spaceres.crafting.MasterRecipeManager.addOreDictRecipe;
import static io.github.phantamanta44.spaceres.crafting.MasterRecipeManager.addShapelessOreDictRecipe;
import static io.github.phantamanta44.spaceres.crafting.MasterRecipeManager.addSmelting;
import io.github.phantamanta44.spaceres.block.BlockCompressed;
import io.github.phantamanta44.spaceres.block.BlockOre;
import io.github.phantamanta44.spaceres.block.SRBlocks;
import io.github.phantamanta44.spaceres.item.ItemResource;
import io.github.phantamanta44.spaceres.item.SRItems;
import io.github.phantamanta44.spaceres.lib.LibDict;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cofh.api.modhelpers.ThermalExpansionHelper;

public class IngotRecipeManager {

	public static void addRecipes() {
		addMaterial(SRBlocks.ore, BlockOre.COBALT, SRItems.itemRes, ItemResource.INGOT_COBALT,
				SRItems.itemRes, ItemResource.DUST_COBALT, SRItems.itemRes, ItemResource.NUGGET_COBALT,
				SRBlocks.comp, BlockCompressed.COBALT, LibDict.COBALT);
	}
	
	private static void addMaterial(Block ore, int om, Item ingot, int im, Item dust, int dm, Item nugget, int nm, Block block, int bm, String dict) {
		addMaterial(new ItemStack(ore, 1, om), new ItemStack(ingot, 1, im), new ItemStack(dust, 1, dm), new ItemStack(nugget, 1, nm), new ItemStack(block, 1, bm), dict);
	}
	
	private static void addMaterial(ItemStack ore, ItemStack ingot, ItemStack dust, ItemStack nugget, ItemStack block, String dict) {
		OreDictionary.registerOre(LibDict.ORE + dict, ore);
		OreDictionary.registerOre(LibDict.INGOT + dict, ingot);
		OreDictionary.registerOre(LibDict.DUST + dict, dust);
		OreDictionary.registerOre(LibDict.NUGGET + dict, nugget);
		OreDictionary.registerOre(LibDict.BLOCK + dict, block);
		addOreDictRecipe(ingot, "nnn", "nnn", "nnn", 'n', nugget);
		addShapelessOreDictRecipe(PhantaUtil.copyStack(nugget, 9), ingot);
		addOreDictRecipe(block, "iii", "iii", "iii", 'i', ingot);
		addShapelessOreDictRecipe(PhantaUtil.copyStack(ingot, 9), block);
		addSmelting(ingot, ore, 0.8F);
		addSmelting(ingot, dust, 0.55F);
		ThermalExpansionHelper.addPulverizerRecipe(4000, ore, PhantaUtil.copyStack(dust, 2));
		ThermalExpansionHelper.addPulverizerRecipe(4000, ingot, dust);
	}
	
}
