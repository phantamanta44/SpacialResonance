package io.github.phantamanta44.spaceres.render.tile;

import io.github.phantamanta44.spaceres.lib.LibResource;
import io.github.phantamanta44.spaceres.render.RenderMap;
import io.github.phantamanta44.spaceres.render.model.ResonanceChannelModel;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ResonanceChannelRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    private ResonanceChannelModel model;

    public ResonanceChannelRenderer() {
        model = new ResonanceChannelModel();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
        bindTexture(LibResource.MODEL_BLOCK_RES_CHANNEL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        model.render((TileResonanceChannel)tile, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        bindTexture(LibResource.MODEL_BLOCK_RES_CHANNEL);
        GL11.glPushMatrix();
        GL11.glRotatef(90F, 0F, 1F, 0F);
        GL11.glScalef(1.28F, 1.28F, 1.28F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        model.render(0.0625F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return RenderMap.forRenderer(getClass());
    }

}
