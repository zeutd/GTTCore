package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HugeTurbineMachine extends LargeTurbineMachine{

    public HugeTurbineMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
    }
    @Nullable
    private IRotorHolderMachine getRotorHolder() {
        for (IMultiPart part : getParts()) {
            if (part instanceof IRotorHolderMachine rotorHolder) {
                return rotorHolder;
            }
        }
        return null;
    }

    /**
     * @return EUt multiplier that should be applied to the turbine's output
     */
    protected double productionBoost() {
        var rotorHolder = getRotorHolder();
        if (rotorHolder != null && rotorHolder.hasRotor()) {
            int maxSpeed = rotorHolder.getMaxRotorHolderSpeed();
            int currentSpeed = rotorHolder.getRotorSpeed();
            if (currentSpeed >= maxSpeed) return 1;
            return Math.pow(1.0 * currentSpeed / maxSpeed, 2);
        }
        return 0;
    }

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof HugeTurbineMachine turbineMachine)) {
            return RecipeModifier.nullWrongType(HugeTurbineMachine.class, machine);
        }
        var rotorHolder = turbineMachine.getRotorHolder();
        if (rotorHolder == null) return ModifierFunction.NULL;
        long EUt = recipe.getOutputEUt().getTotalEU();
        long turbineMaxVoltage = turbineMachine.getOverclockVoltage();
        double holderEfficiency = rotorHolder.getTotalEfficiency() / 100.0;
        if (EUt <= 0 || turbineMaxVoltage <= EUt || holderEfficiency <= 0) return ModifierFunction.NULL;
        // get the amount of parallel required to match the desired output voltage
        int maxParallel = (int) (turbineMaxVoltage / EUt) * 16;
        int actualParallel = ParallelLogic.getParallelAmountFast(turbineMachine, recipe, maxParallel);
        double eutMultiplier = turbineMachine.productionBoost() * actualParallel;
        return ModifierFunction.builder().inputModifier(ContentModifier.multiplier(actualParallel)).outputModifier(ContentModifier.multiplier(actualParallel)).eutMultiplier(eutMultiplier).parallels(actualParallel).durationMultiplier(holderEfficiency).build();
    }
}
