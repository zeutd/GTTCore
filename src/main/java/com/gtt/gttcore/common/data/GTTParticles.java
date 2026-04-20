package com.gtt.gttcore.common.data;

import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.particle.ParticleType;
import com.gtt.gttcore.api.particle.ParticleTypeBuilder;
import com.gtt.gttcore.api.registry.GTTRegistries;

public class GTTParticles {

    static {
        GTTRegistries.PARTICLE_TYPES.unfreeze();
    }
    public static final ParticleType ANTIPROTON = new ParticleTypeBuilder(GTTCore.id("ANTIPROTON"))
            .color(0x440077)
            .langValue("Antiproton")
            .chineseLangValue("反质子")
            .register();

    public static void init(){
        GTTRegistries.PARTICLE_TYPES.freeze();
    }
}
