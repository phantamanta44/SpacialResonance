package io.github.phantamanta44.spaceres.util;

import java.util.function.BiConsumer;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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

    public static void iterAdjTiles(TileEntity tile, BiConsumer<TileEntity, ForgeDirection> func) {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity adj = getAdjTile(tile, dir);
            if (adj != null)
                func.accept(adj, dir);
        }
    }

    public static TileEntity getAdjTile(TileEntity tile, ForgeDirection face) {
        return tile.getWorldObj().getTileEntity(
                tile.xCoord + face.offsetX,
                tile.yCoord + face.offsetY,
                tile.zCoord + face.offsetZ);
    }

    public static void iterAdjTiles(World world, int x, int y, int z, BiConsumer<TileEntity, ForgeDirection> func) {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity adj = getAdjTile(world, x, y, z, dir);
            if (adj != null)
                func.accept(adj, dir);
        }
    }

    public static TileEntity getAdjTile(World world, int x, int y, int z, ForgeDirection face) {
        return world.getTileEntity(
                x + face.offsetX,
                y + face.offsetY,
                z + face.offsetZ);
    }

    public static String getKeyName(KeyBinding bind) {
        return Keyboard.getKeyName(bind.getKeyCode());
    }

    public static ItemStack copyStack(ItemStack stack, int cnt) {
        return new ItemStack(stack.getItem(), cnt, stack.getItemDamage());
    }

    public static String formatInt(long num) {
        try {
            String toStr = Long.toString(num);
            StringBuilder sb = new StringBuilder();
            for (int i = toStr.length() % 3; i < toStr.length(); i += 3) {
                if (i != 0)
                    sb.append(toStr.substring(Math.max(i - 3, 0), i)).append(",");
            }
            return sb.append(toStr.substring(Math.max(toStr.length() - 3, 0))).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "errored!";
        }
    }

}
