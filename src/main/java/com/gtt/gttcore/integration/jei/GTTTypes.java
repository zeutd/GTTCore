package com.gtt.gttcore.integration.jei;

import com.gtt.gttcore.api.particle.ParticleStack;
import mezz.jei.api.ingredients.IIngredientType;

public class GTTTypes {
    public static final IIngredientType<ParticleStack> PARTICLE_TYPE = () -> ParticleStack.class;
}
