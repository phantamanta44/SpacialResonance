package io.github.phantamanta44.spaceres.tile.base;

import io.github.phantamanta44.spaceres.energy.INetworkable;
import io.github.phantamanta44.spaceres.energy.ResonanceNetwork;
import io.github.phantamanta44.spaceres.lib.LibLang;
import io.github.phantamanta44.spaceres.tile.TileResonanceChannel;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.tileentity.ITileInfo;

public class TileNetworkable extends TileMod implements INetworkable, ITileInfo {

    private static final String INFO_HEADER = EnumChatFormatting.GRAY + "--------" + EnumChatFormatting.DARK_BLUE + " [%s] " + EnumChatFormatting.GRAY + "--------";
    private static final String INFO_FMT = EnumChatFormatting.DARK_GRAY + "*" + EnumChatFormatting.GRAY + " %s: " + EnumChatFormatting.DARK_GRAY + "%s";

    protected ResonanceNetwork network;

    @Override
    protected void tick() {
        if (!worldObj.isRemote) {
            markForUpdate();
            if (network == null)
                updateNetwork();
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!init && !worldObj.isRemote) {
            updateNetwork();
            init = true;
        }
    }

    public void updateNetwork() {
        if (network == null) {
            PhantaUtil.iterAdjTiles(this, (t, d) -> {
                if (t instanceof TileResonanceChannel) {
                    TileResonanceChannel target = (TileResonanceChannel)t;
                    if (target.getNetwork() != null) {
                        if (network == null)
                            network = target.getNetwork();
                        else
                            target.getNetwork().merge(network);
                    }
                }
            });
            if (network == null)
                network = new ResonanceNetwork(this);
        }
        network.rescan();
    }

    @Override
    public ResonanceNetwork getNetwork() {
        return network;
    }

    public void setNetwork(ResonanceNetwork network) {
        this.network = network;
    }

    @Override
    public void getTileInfo(List<IChatComponent> info, ForgeDirection side, EntityPlayer player, boolean debug) {
        long netUsed = network.stream()
                .filter(t -> t instanceof IStorageInterface)
                .map(t -> (IStorageInterface)t)
                .mapToLong(IStorageInterface::getEnergyReservoir)
                .sum();
        long netMax = network.stream()
                .filter(t -> t instanceof IStorageInterface)
                .map(t -> (IStorageInterface)t)
                .mapToLong(IStorageInterface::getReservoirSize)
                .sum();
        info.addAll(Arrays.asList(
            new ChatComponentText(String.format(INFO_HEADER, LibLang.get(LibLang.MSG_NET_STATS))),
            new ChatComponentText(String.format(INFO_FMT, LibLang.get(LibLang.MSG_NET_DEVICES),
                    Long.toString(network.stream()
                            .filter(t -> !(t instanceof TileResonanceChannel))
                            .count()))),
            new ChatComponentText(String.format(INFO_FMT, LibLang.get(LibLang.MSG_NET_ENERGY),
                    PhantaUtil.formatInt(netUsed).concat(" RF"))),
            new ChatComponentText(String.format(INFO_FMT, LibLang.get(LibLang.MSG_NET_CAPACITY),
                    PhantaUtil.formatInt(netMax).concat(" RF"))),
            new ChatComponentText(String.format(INFO_FMT, LibLang.get(LibLang.MSG_NET_USED),
                    String.format("~%.02f%%", 100F * (float)netUsed / (float)netMax)))
        ));
    }

}
