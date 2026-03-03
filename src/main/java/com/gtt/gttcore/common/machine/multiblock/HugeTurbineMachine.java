package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.RotorHolderPartMachine;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HugeTurbineMachine extends LargeTurbineMachine{



    public HugeTurbineMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier);
    }
    @Nullable
    private RotorHolderPartMachine getRotorHolder() {
        for (IMultiPart part : getParts()) {
            if (part instanceof RotorHolderPartMachine rotorHolder) {
                return rotorHolder;
            }
        }
        return null;
    }

    /**
     * @return EUt multiplier that should be applied to the turbine's output
     */

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
        int maxParallel = (int) (turbineMaxVoltage / EUt) * 32;
        int actualParallel = ParallelLogic.getParallelAmountFast(turbineMachine, recipe, maxParallel);
        double eutMultiplier = turbineMachine.productionBoost() * actualParallel;
        return ModifierFunction.builder().inputModifier(ContentModifier.multiplier(actualParallel)).outputModifier(ContentModifier.multiplier(actualParallel)).eutMultiplier(eutMultiplier).parallels(actualParallel).durationMultiplier(holderEfficiency).build();
    }
}
