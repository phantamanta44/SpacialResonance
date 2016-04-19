package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.energy.INetworkable.INetworkUpdateHook;
import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import io.github.phantamanta44.spaceres.util.PhantaUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileResonanceChannel extends TileNetworkable implements INetworkUpdateHook {
	
	private boolean[] connections;
	
	private boolean dataFlowing = false;
	
	private boolean[] getConnections() {
		if (connections == null)
			updateConnections();
		return connections;
	}
	
	public boolean isConnection(ForgeDirection dir) {
		return getConnections()[dir.ordinal()];
	}
	
	public void updateConnections() {
		if (connections == null)
			connections = new boolean[6];
		for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++)
			connections[i] = PhantaUtil.getAdjTile(this, ForgeDirection.VALID_DIRECTIONS[i]) instanceof TileNetworkable;
	}
	
	@Override
	public void onNetworkUpdate() {
		dataFlowing = network.stream()
				.anyMatch(m -> !(m instanceof TileResonanceChannel));
	}
	
	public boolean isDataFlowing() {
		return dataFlowing;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		boolean[] coll = getConnections();
		int cxn = 0;
		for (int i = 0; i < coll.length ; i++)
			cxn |= (coll[i] ? 1 : 0) << i;
		tag.setInteger(LibNBT.CONNECTIONS, cxn);
		tag.setBoolean(LibNBT.DATA_FLOWING, dataFlowing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		int cxn = tag.getInteger(LibNBT.CONNECTIONS);
		if (connections == null)
			connections = new boolean[6];
		for (int i = 0; i < connections.length; i++)
			connections[i] = (cxn & (1 << i)) != 0;
		dataFlowing = tag.getBoolean(LibNBT.DATA_FLOWING);
	}
	
}
