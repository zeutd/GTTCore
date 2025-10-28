package com.gtt.gttcore.api;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;

public class GTTRecipeHelper {
    public static int getInputHighEnergyLaser(GTRecipe recipe){
        return recipe.inputs.get(HighEnergyLaserRecipeCapability.CAP).stream().mapToInt(c -> (Integer) c.content).sum();
    }

    public static int getOutputHighEnergyLaser(GTRecipe recipe){
        return recipe.outputs.get(HighEnergyLaserRecipeCapability.CAP).stream().mapToInt(c -> (Integer) c.content).sum();
    }
}
