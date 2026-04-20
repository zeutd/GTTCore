package com.gtt.gttcore.integration.jei.particle;

import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.integration.jei.GTTTypes;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ParticleIngredientHelper implements IIngredientHelper<ParticleStack> {
    @Override
    public IIngredientType<ParticleStack> getIngredientType() {
        return GTTTypes.PARTICLE_TYPE;
    }

    @Override
    public String getDisplayName(ParticleStack ingredient) {
        return Component.translatable(ingredient.getType().getId().toLanguageKey("particle_type")).getString();
    }

    @Override
    public String getUniqueId(ParticleStack ingredient, UidContext context) {
        return ingredient.getType().getId().toString();
    }

    @Override
    public ResourceLocation getResourceLocation(ParticleStack ingredient) {
        return ingredient.getType().getId();
    }

    @Override
    public ParticleStack copyIngredient(ParticleStack ingredient) {
        return ingredient.copy();
    }

    @Override
    public String getErrorInfo(@Nullable ParticleStack ingredient) {
        if (ingredient == null) return "null";
        return ingredient.getType().getId().toString();
    }

    @Override
    public ParticleStack normalizeIngredient(ParticleStack ingredient) {
        return ingredient.withAmount(1);
    }
}
