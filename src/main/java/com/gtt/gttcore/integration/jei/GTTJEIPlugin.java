package com.gtt.gttcore.integration.jei;

import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.api.registry.GTTRegistries;
import com.gtt.gttcore.integration.jei.particle.ParticleIngredientHelper;
import com.gtt.gttcore.integration.jei.particle.ParticleIngredientRenderer;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IModIngredientRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;

import java.util.stream.Collectors;

@JeiPlugin
@MethodsReturnNonnullByDefault
public class GTTJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return GTTCore.id("jei_plugin");
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
        registration.register(GTTTypes.PARTICLE_TYPE, GTTRegistries.PARTICLE_TYPES.values().stream().map(particleType -> new ParticleStack(particleType, 1)).collect(Collectors.toList()), new ParticleIngredientHelper(), new ParticleIngredientRenderer());
    }
}
