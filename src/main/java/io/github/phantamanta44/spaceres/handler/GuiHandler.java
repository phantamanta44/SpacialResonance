package io.github.phantamanta44.spaceres.handler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static Map<Class<? extends TileEntity>, Class<? extends Container>> containerMap = new HashMap<>();
	public static Map<Class<? extends TileEntity>, Class<? extends GuiContainer>> guiMap = new HashMap<>();

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		Class clazz = containerMap.get(tile.getClass());
		try {
			return clazz.getDeclaredConstructors()[0].newInstance(player.inventory, tile);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		Class clazz = guiMap.get(tile.getClass());
		try {
			return clazz.getDeclaredConstructors()[0].newInstance(player.inventory, tile);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
}