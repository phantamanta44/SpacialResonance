package io.github.phantamanta44.spaceres.item.block;

import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockAcceptionBus extends ItemBlockEnergyStorageDevice {

    private static final String STORED_FMT = EnumChatFormatting.GRAY + "%s: " + EnumChatFormatting.DARK_GRAY + "%s / %s RF";
    private static final String RATE_FMT = EnumChatFormatting.GRAY + "%s: " + EnumChatFormatting.DARK_GRAY + "%s RF/t";

    public ItemBlockAcceptionBus(Block block) {
        super(block);
    }

    @Override
    protected List<String> getTooltip(ItemStack stack, EntityPlayer player) {
        NBTTagCompound tag = getStoredBlockState(stack);
        long eRate = tag.getLong(LibNBT.ENERGY_RATE_IN);
        return Arrays.asList(
            String.format(STORED_FMT,
                    LibLang.get(LibLang.TT_ENERGY),
                    PhantaUtil.formatInt(tag.getLong(LibNBT.ENERGY)),
                    PhantaUtil.formatInt(tag.getLong(LibNBT.ENERGY_MAX))),
            String.format(RATE_FMT,
                    LibLang.get(LibLang.TT_ENERGY_RATE),
                    eRate == -1 ? LibLang.get(LibLang.TT_INF) : PhantaUtil.formatInt(eRate))
        );
    }

}
