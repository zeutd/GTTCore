package com.gtt.gttcore.api;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class GTTRecipeHelper {
    public static int getInputHighEnergyLaser(GTRecipe recipe){
        return recipe.inputs.get(HighEnergyLaserRecipeCapability.CAP).stream().mapToInt(c -> (Integer) c.content).sum();
    }

    public static int getOutputHighEnergyLaser(GTRecipe recipe){
        return recipe.outputs.get(HighEnergyLaserRecipeCapability.CAP).stream().mapToInt(c -> (Integer) c.content).sum();
    }

    public static List<FluidStack> getInputFluids(GTRecipeBuilder builder) {
        return builder.input.getOrDefault(FluidRecipeCapability.CAP, Collections.emptyList()).stream()
                .map(content -> FluidRecipeCapability.CAP.of(content.getContent()))
                .map(ingredient -> ingredient.getStacks()[0])
                .collect(Collectors.toList());
    }
}
