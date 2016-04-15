package io.github.phantamanta44.spaceres.gui;

public abstract class GuiTile extends GuiScreenMod {
	
	@Override
	protected void keyTyped(char typed, int keyCode) {
		if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode())
			this.mc.thePlayer.closeScreen();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		if (!mc.thePlayer.isEntityAlive() || mc.thePlayer.isDead)
			mc.thePlayer.closeScreen();
	}

}
