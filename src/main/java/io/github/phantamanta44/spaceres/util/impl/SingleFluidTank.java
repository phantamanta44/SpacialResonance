package io.github.phantamanta44.spaceres.util.impl;

import io.github.phantamanta44.spaceres.lib.LibNBT;
import io.github.phantamanta44.spaceres.util.INBTSerializable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class SingleFluidTank implements IFluidTank, INBTSerializable {

    private Fluid fluid;
    private FluidStack stack;
    private int capacity;

    public SingleFluidTank(Fluid filter, int max) {
        fluid = filter;
        stack = new FluidStack(filter, 0);
        capacity = max;
    }

    @Override
    public FluidStack getFluid() {
        return stack;
    }

    @Override
    public int getFluidAmount() {
        return stack.amount;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public FluidTankInfo getInfo() {
        return new FluidTankInfo(this);
    }

    @Override
    public int fill(FluidStack toFill, boolean doFill) {
        if (toFill != null && toFill.getFluid() == fluid) {
            if (stack != null) {
                int toTransfer = Math.min(toFill.amount, capacity - stack.amount);
                if (toTransfer > 0 && doFill)
                    stack.amount += toTransfer;
                return toTransfer;
            }
            else {
                int newAmount = Math.min(toFill.amount, capacity);
                if (newAmount > 0 && doFill) {
                    stack = toFill.copy();
                    stack.amount = newAmount;
                }
                return newAmount;
            }
        }
        return 0;
    }

    @Override
    public FluidStack drain(int amount, boolean doDrain) {
        if (stack != null) {
            int toDrain = Math.min(amount, stack.amount);
            if (toDrain > 0 && doDrain)
                stack.amount -= toDrain;
            FluidStack ret = stack.copy();
            ret.amount = toDrain;
            return ret;
        }
        return null;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        NBTTagCompound stackTag = new NBTTagCompound();
        stack.writeToNBT(stackTag);
        tag.setTag(LibNBT.TANK_FLUID_STACK, stackTag);
        tag.setString(LibNBT.TANK_FLUID_NAME, fluid.getName());
        tag.setInteger(LibNBT.TANK_CAP, capacity);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        NBTTagCompound stackTag = tag.getCompoundTag(LibNBT.TANK_FLUID_STACK);
        String fluidName = tag.getString(LibNBT.TANK_FLUID_NAME);
        capacity = tag.getInteger(LibNBT.TANK_CAP);
        stack = FluidStack.loadFluidStackFromNBT(stackTag);
        fluid = FluidRegistry.getFluid(fluidName);
    }

    private SingleFluidTank() {

    }

    public static SingleFluidTank loadFromNBT(NBTTagCompound tag) {
        SingleFluidTank tank = new SingleFluidTank();
        tank.readFromNBT(tag);
        return tank;
    }

}
