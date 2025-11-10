package com.gtt.gttcore.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import com.gtt.gttcore.common.machine.IHighEnergyLaserHatch;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class NotifiableHighEnergyLaserContainer extends NotifiableRecipeHandlerTrait<Integer> implements IHighEnergyLaserProvider, IHighEnergyLaserHatch, IHighEnergyLaserInfoProvider {
    protected IO handlerIO;
    protected boolean transmitter;
    protected int laserAmount;
    @Nullable
    protected TickableSubscription outputSubs;

    public NotifiableHighEnergyLaserContainer(MetaMachine machine, IO handlerIO, boolean transmitter) {
        super(machine);
        this.handlerIO = handlerIO;
        this.transmitter = transmitter;
    }

    @Override
    public IO getHandlerIO() {
        return handlerIO;
    }

    @Override
    public List<Integer> handleRecipeInner(IO io, GTRecipe recipe, List<Integer> left, boolean simulate) {
        int sum = left.stream().mapToInt(Integer::intValue).sum();
        if (io == IO.IN){
            sum -= getLaserAmount();
            if (!simulate) setLaserAmount(0);
        }
        else if (io == IO.OUT){
            if (!simulate) setLaserAmount(sum);
            sum = 0;
        }
        return sum <= 0 ? null : Collections.singletonList(sum);
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(getLaserAmount());
    }

    @Override
    public double getTotalContentAmount() {
        return getLaserAmount();
    }

    @Override
    public RecipeCapability<Integer> getCapability() {
        return HighEnergyLaserRecipeCapability.CAP;
    }

    @Override
    public int getLaserAmount() {
        return laserAmount;
    }

    @Override
    public void setLaserAmount(int amount) {
        laserAmount = amount;
    }

    @Override
    public boolean isTransmitter() {
        return transmitter;
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        outputSubs = getMachine().subscribeServerTick(outputSubs, this::outputTick);
    }

    @Override
    public void onMachineUnLoad() {
        super.onMachineUnLoad();
        if (outputSubs != null) {
            outputSubs.unsubscribe();
            outputSubs = null;
        }
    }

    public void outputTick() {
        if (!isTransmitter()) return;
        if (getMachine().getLevel().isClientSide) return;
        BlockPos pos = getMachine().getPos().relative(getMachine().getFrontFacing());
        IHighEnergyLaserProvider nhelc = GTTCapabilityHelper.getHighEnergyLaserContainer(getMachine().getLevel(), pos, getMachine().getFrontFacing().getOpposite());
        if (nhelc != null && !nhelc.isTransmitter()) {
            nhelc.setLaserAmount(getLaserAmount());
        }
    }
}
