package com.gtt.gttcore.api.registry;

import com.gregtechceu.gtceu.api.registry.GTRegistry;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.particle.ParticleType;

public class GTTRegistries {
    public static final GTRegistry.RL<ParticleType> PARTICLE_TYPES = new GTRegistry.RL<>(GTTCore.id("particle_type"));
}
