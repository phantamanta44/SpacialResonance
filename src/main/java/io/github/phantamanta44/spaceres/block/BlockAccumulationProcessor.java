package io.github.phantamanta44.spaceres.block;

import io.github.phantamanta44.spaceres.block.base.BlockNetworkable;
import io.github.phantamanta44.spaceres.item.block.ItemBlockStatelessDevice;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.TileAccumulator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAccumulationProcessor extends BlockNetworkable {

    public static final int LEAD = 0, INVAR = 1, ELECTRUM = 2, ENDER = 3;

    public BlockAccumulationProcessor() {
        super(Material.iron, 8);
        setHardness(5F);
        setResistance(7.5F);
        setBlockName(LibLang.BLOCK_ACCUM_NAME);
    }

    @Override
    public Block setBlockName(String name) {
        GameRegistry.registerBlock(this, ItemBlockStatelessDevice.class, name);
        return super.setBlockName(name);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileAccumulator(LibTier.getTier8(meta));
    }

}
