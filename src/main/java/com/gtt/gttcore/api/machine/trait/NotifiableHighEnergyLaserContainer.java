package com.gtt.gttcore.api.machine.trait;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationHatch;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.utils.GTMath;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.GTTRecipeHelper;
import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;
import com.gtt.gttcore.api.capability.forge.GTTCapability;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import com.gtt.gttcore.common.machine.IHighEnergyLaserHatch;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import com.gtt.gttcore.common.machine.multiblock.part.HighEnergyLaserHatchPartMachine;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
            int maxOutput = getLaserAmount();
            addLaserAmount(-Math.min(maxOutput, sum));
            sum -= maxOutput;
        }
        else if (io == IO.OUT){
            setLaserAmount(sum);
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
        checkOutputSubscription();
    }

    @Override
    public boolean isTransmitter() {
        return transmitter;
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        checkOutputSubscription();
    }

    public void checkOutputSubscription() {
            if (getLaserAmount() >= 0) {
                outputSubs = getMachine().subscribeServerTick(outputSubs, this::outputTick);
            } else if (outputSubs != null) {
                outputSubs.unsubscribe();
                outputSubs = null;
            }
    }

    public void outputTick() {
        if (getMachine().getLevel().isClientSide) return;
        if (!isTransmitter()) return;
        BlockPos pos = getMachine().getPos().relative(getMachine().getFrontFacing());
        IHighEnergyLaserProvider nhelc = GTTCapabilityHelper.getHighEnergyLaserContainer(getMachine().getLevel(), pos, getMachine().getFrontFacing().getOpposite());
        if (nhelc != null && !nhelc.isTransmitter()) {
            nhelc.setLaserAmount(getLaserAmount());
        }
    }
}
