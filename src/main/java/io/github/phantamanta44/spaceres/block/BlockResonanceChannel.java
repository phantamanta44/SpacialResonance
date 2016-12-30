package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.block.base.BlockNetworkable;
import io.github.phantamanta44.spaceres.item.block.ItemBlockResonanceChannel;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.render.RenderMap;
import io.github.phantamanta44.spaceres.render.tile.ResonanceChannelRenderer;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockResonanceChannel extends BlockNetworkable {

    public static final int RES_CHAN = 0;

    public BlockResonanceChannel() {
        super(Material.iron, 1);
        setHardness(2.3F);
        setResistance(4.5F);
        setBlockName(LibLang.BLOCK_RES_CHAN_NAME);
    }

    @Override
    public Block setBlockName(String name) {
        GameRegistry.registerBlock(this, ItemBlockResonanceChannel.class, name);
        return super.setBlockName(name);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileResonanceChannel();
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        ((TileResonanceChannel)world.getTileEntity(x, y, z)).updateConnections();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderMap.forRenderer(ResonanceChannelRenderer.class);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        boolean x1, y1, z1, x2, y2, z2;
        x1 = y1 = z1 = x2 = y2 = z2 = false;
        TileResonanceChannel tile = (TileResonanceChannel)world.getTileEntity(x, y, z);
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (tile.isConnection(dir)) {
                if (dir.offsetX < 0)
                    x1 = true;
                else if (dir.offsetX > 0)
                    x2 = true;
                else if (dir.offsetY < 0)
                    y1 = true;
                else if (dir.offsetY > 0)
                    y2 = true;
                else if (dir.offsetZ < 0)
                    z1 = true;
                else if (dir.offsetZ > 0)
                    z2 = true;
            }
        }
        setBlockBounds(x1 ? 0F : 0.3F, y1 ? 0F : 0.3F, z1 ? 0F : 0.3F,
                x2 ? 1F : 0.7F, y2 ? 1F : 0.7F, z2 ? 1F : 0.7F);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB test, List bbs, Entity entity) {
        TileResonanceChannel tile = (TileResonanceChannel)world.getTileEntity(x, y, z);
        AxisAlignedBB center = AxisAlignedBB.getBoundingBox(
                (double)x + 0.35D, (double)y + 0.35D, (double)z + 0.35D,
                (double)x + 0.65D, (double)y + 0.65D, (double)z + 0.65D);
        if (test.intersectsWith(center))
            bbs.add(center);
        testBounds(tile, ForgeDirection.UP, test,
                AxisAlignedBB.getBoundingBox(x + 0.35D, y + 0.65D, z + 0.35D, x + 0.65D, y + 1D, z + 0.65D), bbs);
        testBounds(tile, ForgeDirection.DOWN, test,
                AxisAlignedBB.getBoundingBox(x + 0.35D, y, z + 0.35D, x + 0.65D, y, + 0.35D + 0.65D), bbs);
        testBounds(tile, ForgeDirection.WEST, test,
                AxisAlignedBB.getBoundingBox(x, y + 0.35D, z + 0.35D, x + 0.35D, y + 0.65D, z + 0.65D), bbs);
        testBounds(tile, ForgeDirection.EAST, test,
                AxisAlignedBB.getBoundingBox(x + 0.65D, y + 0.35D, z + 0.35D, x + 1D, y + 0.65D, z + 0.65D), bbs);
        testBounds(tile, ForgeDirection.NORTH, test,
                AxisAlignedBB.getBoundingBox(x + 0.35D, y + 0.35D, z, x + 0.65D, y + 0.65D, z + 0.35D), bbs);
        testBounds(tile, ForgeDirection.SOUTH, test,
                AxisAlignedBB.getBoundingBox(x + 0.35D, y + 0.35D, z + 0.65D, x + 0.65D, y + 0.65D, z + 1D), bbs);
    }

    private void testBounds(TileResonanceChannel tile, ForgeDirection dir, AxisAlignedBB test, AxisAlignedBB bb, List bbs) {
        if (tile.isConnection(dir) && test.intersectsWith(bb))
            bbs.add(bb);
    }

}
