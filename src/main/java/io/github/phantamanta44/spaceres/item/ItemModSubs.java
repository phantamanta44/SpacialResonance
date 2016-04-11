package io.github.phantamanta44.spaceres.item;

import io.github.phantamanta44.spaceres.util.IconHelper;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModSubs extends ItemMod {

	protected final int subitemCount;
	protected IIcon[] icons;
	
	public ItemModSubs(int items) {
		super();
		subitemCount = items;
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item parent, CreativeTabs tab, List items) {
		for (int i = 0; i < subitemCount; i++)
			items.add(new ItemStack(parent, 1, i));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		icons = new IIcon[subitemCount];
		for (int i = 0; i < subitemCount; i++)
			icons[i] = IconHelper.forItem(registry, this, i);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		try {
			return icons[meta];
		} catch (IndexOutOfBoundsException ex) {
			return icons[0];
		}
	}
	
}
