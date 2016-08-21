package io.github.phantamanta44.spaceres.block.base;

import io.github.phantamanta44.spaceres.SpaceRes;
import io.github.phantamanta44.spaceres.lib.LibCore;
import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.tile.TileInternalStorage;
import io.github.phantamanta44.spaceres.util.IconHelper;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMod extends Block {
	
	protected ArrayList<ItemStack> compiledDrops;
	
	public BlockMod(Material material) {
		super(material);
		addToCreative();
	}

	@Override
	public Block setBlockName(String name) {
		if (GameRegistry.findBlock(LibCore.MODID, name) == null)
			GameRegistry.registerBlock(this, name);
		return super.setBlockName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		blockIcon = IconHelper.forBlock(registry, this);
	}
	
	public void addToCreative() {
		setCreativeTab(SpaceRes.tabSpaceRes);
	}
	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		if (this.getClass().isAnnotationPresent(ItemBlockState.class))
			compileDrops(world, x, y, z, world.getBlockMetadata(x, y, z));
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		return compiledDrops != null ? compiledDrops : super.getDrops(world, x, y, z, meta, fortune);
	}
	
	protected void compileDrops(IBlockAccess world, int x, int y, int z, int meta) {
		compiledDrops = new ArrayList<>();
		ItemStack stack = new ItemStack(getItemDropped(meta, null, 0), 1, meta);
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null) {
			NBTTagCompound tag = new NBTTagCompound();
			tile.writeToNBT(tag);
			if (stack.getTagCompound() == null)
				stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setTag(LibNBT.ITEM_BLOCK_STATE, tag);
		}
		compiledDrops.add(stack);
	}

}
