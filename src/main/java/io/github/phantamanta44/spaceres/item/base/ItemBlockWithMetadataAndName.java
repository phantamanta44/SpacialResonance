package io.github.phantamanta44.spaceres.item.base;

import io.github.phantamanta44.spaceres.lib.LibCore;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockWithMetadataAndName extends ItemBlockWithMetadata {

    public ItemBlockWithMetadataAndName(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
        return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("tile.", "tile." + LibCore.MODPREF);
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
    }

}

