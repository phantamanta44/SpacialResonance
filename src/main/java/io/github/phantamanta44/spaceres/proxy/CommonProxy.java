package io.github.phantamanta44.spaceres.proxy;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.block.SRBlocks;
import io.github.phantamanta44.spaceres.crafting.MasterRecipeManager;
import io.github.phantamanta44.spaceres.handler.GuiHandler;
import io.github.phantamanta44.spaceres.item.SRItems;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void onPreInit() {
		SRItems.init();
		SRBlocks.init();
		registerTileEntities();
		registerContainers();
	}
	
	public void onInit() {
		MasterRecipeManager.addRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(SpaceRes.instance, new GuiHandler());
	}
	
	public void onPostInit() {
		
	}
	
	protected void registerTileEntities() {
		// TODO things
	}
	
	protected void registerContainers() {
		// TODO stuff
	}
	
	protected void registerContainer(Class<? extends TileEntity> tile, Class<? extends GuiContainer> gui, Class<? extends Container> cont) {
		GuiHandler.guiMap.put(tile, gui);
		GuiHandler.containerMap.put(tile, cont);
	}
	
	protected void addTEMapping(Class c) {
		TileEntity.addMapping(c, c.getName());
	}
	
}
