package io.github.phantamanta44.spaceres.render;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderMap {

	private static Map<Class<?>, Integer> idMap = new HashMap<>();
	
	public static void registerTile(Class<? extends TileEntity> tile, Object renderer) {
		int id = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(tile, (TileEntitySpecialRenderer)renderer);
		RenderingRegistry.registerBlockHandler(id, (ISimpleBlockRenderingHandler)renderer);
		idMap.put(renderer.getClass(), id);
	}
	
	public static int forRenderer(Class<?> clazz) {
		return idMap.get(clazz);
	}
	
}
