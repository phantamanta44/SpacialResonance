package io.github.phantamanta44.spaceres.proxy;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.block.BlockOre;
import io.github.phantamanta44.spaceres.block.SRBlocks;
import io.github.phantamanta44.spaceres.crafting.MasterRecipeManager;
import io.github.phantamanta44.spaceres.handler.GuiHandler;
import io.github.phantamanta44.spaceres.item.SRItems;
import io.github.phantamanta44.spaceres.tile.TileAcceptionBus;
import io.github.phantamanta44.spaceres.tile.TileAccumulator;
import io.github.phantamanta44.spaceres.tile.TileDistributor;
import io.github.phantamanta44.spaceres.tile.TileExportionBus;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;
import io.github.phantamanta44.spaceres.util.BlockWithMeta;
import io.github.phantamanta44.spaceres.worldgen.OreGenSimple;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

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
		GameRegistry.registerWorldGenerator(new OreGenSimple(new BlockWithMeta(SRBlocks.ore, BlockOre.COBALT), 0, new BlockWithMeta(Blocks.stone), 1, 28, 6, 4) , 5);
	}
	
	public void onPostInit() {
		
	}
	
	protected void registerTileEntities() {
		addTEMapping(TileAcceptionBus.class);
		addTEMapping(TileExportionBus.class);
		addTEMapping(TileDistributor.class);
		addTEMapping(TileAccumulator.class);
		addTEMapping(TileResonanceChannel.class);
	}
	
	protected void registerContainers() {
		// TODO Register GUIs and containers
	}
	
	protected void registerContainer(Class<? extends TileEntity> tile, Class<? extends GuiContainer> gui, Class<? extends Container> cont) {
		GuiHandler.guiMap.put(tile, gui);
		GuiHandler.containerMap.put(tile, cont);
	}
	
	protected void addTEMapping(Class c) {
		TileEntity.addMapping(c, c.getName());
	}
	
}
