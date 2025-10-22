package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;

public class GTTRecipeCapabilities {
    public final static RecipeCapability<Integer> HIGH_ENERGY_LASER = HighEnergyLaserRecipeCapability.CAP;

    public static void init() {
        GTRegistries.RECIPE_CAPABILITIES.register(HIGH_ENERGY_LASER.name, HIGH_ENERGY_LASER);
    }
}
