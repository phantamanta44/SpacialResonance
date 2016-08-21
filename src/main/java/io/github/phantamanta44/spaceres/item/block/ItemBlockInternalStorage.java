package io.github.phantamanta44.spaceres.item.block;

import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockInternalStorage extends ItemBlockPersistentDevice {
	
	private static final String MSG_FMT = EnumChatFormatting.GRAY + "%s: " + EnumChatFormatting.DARK_GRAY + "%s / %s RF";

	public ItemBlockInternalStorage(Block block) {
		super(block);
	}
	
	@Override
	protected List<String> getTooltip(ItemStack stack, EntityPlayer player) {
		NBTTagCompound tag = stack.getTagCompound() == null ? null : stack.getTagCompound().getCompoundTag(LibNBT.ITEM_BLOCK_STATE);
		return tag == null ? null : Collections.singletonList(String.format(MSG_FMT,
				LibLang.get(LibLang.TT_ENERGY),
				PhantaUtil.formatInt(tag.getLong(LibNBT.ENERGY)),
				PhantaUtil.formatInt(tag.getLong(LibNBT.ENERGY_MAX)))
		);
	}

}
