package io.github.phantamanta44.spaceres.block.base;

import io.github.phantamanta44.spaceres.item.base.ItemBlockWithMetadataAndName;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.tile.TileInternalStorage;
import io.github.phantamanta44.spaceres.util.IconHelper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockModSubs extends BlockMod {

    protected final int subblockCount;
    protected IIcon[] icons;

    public BlockModSubs(Material material, int blocks) {
        super(material);
        subblockCount = blocks;
    }

    @Override
    public void getSubBlocks(Item parent, CreativeTabs tab, List items) {
        for (int i = 0; i < subblockCount; i++)
            items.add(new ItemStack(parent, 1, i));
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public Block setBlockName(String name) {
        if (GameRegistry.findBlock(LibCore.MODID, name) == null)
            GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, name);
        return super.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry) {
        icons = new IIcon[subblockCount];
        for (int i = 0; i < subblockCount; i++)
            icons[i] = IconHelper.forBlock(registry, this, i);
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int face) {
        return icons[world.getBlockMetadata(x, y, z)];
    }

    @Override
    public IIcon getIcon(int id, int meta) {
        return icons[meta];
    }

}
