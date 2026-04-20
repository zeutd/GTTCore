package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import com.gtt.gttcore.api.capability.recipe.ParticleRecipeCapability;
import com.gtt.gttcore.api.particle.ParticleStack;

public class GTTRecipeCapabilities {
    public final static RecipeCapability<Integer> HIGH_ENERGY_LASER = HighEnergyLaserRecipeCapability.CAP;

    public final static RecipeCapability<ParticleStack> PARTICLE = ParticleRecipeCapability.CAP;

    public static void init() {
        GTRegistries.RECIPE_CAPABILITIES.register(HIGH_ENERGY_LASER.name, HIGH_ENERGY_LASER);
        GTRegistries.RECIPE_CAPABILITIES.register(PARTICLE.name, PARTICLE);
    }
}
