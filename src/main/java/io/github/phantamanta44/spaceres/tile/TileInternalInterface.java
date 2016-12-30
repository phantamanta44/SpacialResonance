package io.github.phantamanta44.spaceres.tile;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.lib.LibTier;
import io.github.phantamanta44.spaceres.tile.base.IStorageInterface;
import io.github.phantamanta44.spaceres.tile.base.TileNetworkable;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.nbt.NBTTagCompound;

public class TileInternalInterface extends TileNetworkable implements IStorageInterface {

    private List<TileInternalStorage> cells = new CopyOnWriteArrayList<>();
    private int cellCnt, transfer;

    public TileInternalInterface() {
        this.cellCnt = 0;
        this.transfer = 0;
    }

    public TileInternalInterface(LibTier tier) {
        this.cellCnt = tier.cells;
        this.transfer = tier.transfer;
    }

    public void updateCells() {
        cells.clear();
        PhantaUtil.iterAdjTiles(this, (te, dir) -> {
            if (te instanceof TileInternalStorage)
                cells.add((TileInternalStorage)te);
        });
    }

    @Override
    public void updateEntity() {
        if (!init)
            updateCells();
        super.updateEntity();
    }

    @Override
    public long getEnergyReservoir() {
        return cells.stream()
                .mapToLong(c -> c.getTauStored())
                .sum();
    }

    @Override
    public long getReservoirSize() {
        return cells.stream()
                .mapToLong(c -> c.getTauMax())
                .sum();
    }

    @Override
    public int offerEnergy(int amt) {
        int usable = amt = transfer > 0 ? Math.min(amt, transfer) : amt;
        for (TileInternalStorage cell : cells) {
            usable -= cell.offerEnergy(usable);
            if (usable <= 0)
                return amt;
        }
        return amt - usable;
    }

    @Override
    public int requestEnergy(int amt) {
        int requested = amt = transfer > 0 ? Math.min(amt, transfer) : amt;
        for (TileInternalStorage cell : cells) {
            requested -= cell.requestEnergy(requested);
            if (requested <= 0)
                return amt;
        }
        return amt - requested;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger(LibNBT.DEV_COUNT, cellCnt);
        tag.setInteger(LibNBT.ENERGY_RATE, transfer);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        cellCnt = tag.getInteger(LibNBT.DEV_COUNT);
        transfer = tag.getInteger(LibNBT.ENERGY_RATE);
    }

}
