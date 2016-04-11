package io.github.phantamanta44.spaceres.util;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class PhantaUtil {

	public static void dropItem(World world, int x, int y, int z, ItemStack stack) {
		float f = world.rand.nextFloat() * 0.8F + 0.1F;
		float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
		EntityItem ent = new EntityItem(world, x + f, y + f1, z + f2, stack);
		float f3 = 0.05F;
		ent.motionX = (double)((float)world.rand.nextGaussian() * f3);
		ent.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
		ent.motionZ = (double)((float)world.rand.nextGaussian() * f3);
		ent.delayBeforeCanPickup = 10;
		world.spawnEntityInWorld(ent);
	}
	
	public static boolean canHarvest(World world, int x, int y, int z, EntityPlayer player) {
		return !player.capabilities.isCreativeMode && !world.isRemote && world.getBlock(x, y, z).canHarvestBlock(player, world.getBlockMetadata(x, y, z));
	}
	
	public static boolean isMouseOver(int x, int y, int width, int height, int mX, int mY) {
		return mX >= x - 1 && mX < x + width + 1 && mY >= y - 1 && mY < y + height + 1;
	}
	
	public static String getKeyName(KeyBinding bind) {
		return Keyboard.getKeyName(bind.getKeyCode());
	}
	
}
