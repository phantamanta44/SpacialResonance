package io.github.phantamanta44.spaceres;

import io.github.phantamanta44.spaceres.item.ItemMaterial;
import io.github.phantamanta44.spaceres.item.SRItems;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibCore.MODID, version = LibCore.MODVER)
public class SpaceRes {
	
	@Instance(LibCore.MODID)
	public static SpaceRes instance;
	
	@SidedProxy(clientSide = "io.github.phantamanta44.spaceres.proxy.ClientProxy", serverSide = "io.github.phantamanta44.spaceres.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabSpaceRes = new CreativeTabSR();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.onPreInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.onInit();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.onPostInit();
	}
	
	public static class CreativeTabSR extends CreativeTabs {
		
		public CreativeTabSR() {
			super(LibLang.CREATIVE_TAB_NAME);
		}
		
		@Override
		public ItemStack getIconItemStack() {
			return new ItemStack(SRItems.itemMat, 1, ItemMaterial.TAU_CONV);
		}
		
		@Override
		public Item getTabIconItem() {
			return SRItems.itemMat;
		}
		
	}
	
}
