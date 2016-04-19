package io.github.phantamanta44.spaceres.render.model;

import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class ResonanceChannelModel extends ModelBase {

	// DOWN, UP, NORTH, SOUTH, WEST, EAST
	private static final float[] ANGLE_Y = new float[] {
		0F,
		0F,
		(float)Math.PI / 2F, 
		(float)Math.PI / -2F,
		(float)Math.PI,
		0F
	};
	private static final float[] ANGLE_Z = new float[] {
		(float)Math.PI / -2F,
		(float)Math.PI / 2,
		0F,
		0F,
		0F,
		0F
	};
	
	private ModelRenderer base, cxn, baseIn, cxnIn;
	
	public ResonanceChannelModel() {
		base = new ModelRenderer(this);
		base.addBox(-3F, -3F, -3F, 6, 6, 6);
		base.addBox(-2F, -2F, -2F, 4, 4, 4);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		baseIn = new ModelRenderer(this, 24, 0);
		baseIn.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5);
		baseIn.setRotationPoint(0F, 0F, 0F);
		baseIn.setTextureSize(64, 32);
		baseIn.mirror = true;
		cxn = new ModelRenderer(this, 0, 12);
		cxn.addBox(3F, -3F, -3F, 5, 6, 6);
		cxn.setRotationPoint(0F, 0F, 0F);
		cxn.setTextureSize(64, 32);
		cxn.mirror = true;
		cxnIn = new ModelRenderer(this, 24, 0);
		cxnIn.addBox(3F, -2.5F, -2.5F, 5, 5, 5);
		cxnIn.setRotationPoint(0F, 0F, 0F);
		cxnIn.setTextureSize(64, 32);
		cxnIn.mirror = true;
	}
	
	public void render(TileResonanceChannel tile, float scale) {
		base.render(scale);
		if (tile.isDataFlowing()) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1F, 1F, 1F, 0.85F);
			baseIn.render(scale);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (tile.isConnection(dir))
				renderCxn(dir, scale, tile.isDataFlowing());
		}
	}
	
	public void render(float scale) {
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		base.render(scale);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1F, 1F, 1F, 0.85F);
		baseIn.render(scale);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		renderCxn(ForgeDirection.UP, scale, true);
		renderCxn(ForgeDirection.DOWN, scale, true);
	}
	
	private void renderCxn(ForgeDirection dir, float scale, boolean in) {
		cxn.rotateAngleY = ANGLE_Y[dir.ordinal()];
		cxn.rotateAngleZ = ANGLE_Z[dir.ordinal()];
		cxn.render(scale);
		if (in) {
			cxnIn.rotateAngleY = ANGLE_Y[dir.ordinal()];
			cxnIn.rotateAngleZ = ANGLE_Z[dir.ordinal()];
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1F, 1F, 1F, 0.85F);
			cxnIn.render(scale);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
	}

}
