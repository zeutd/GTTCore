package com.gtt.gttcore.common.data;

import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.client.particles.MushroomCloudParticleOptions;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GTTParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
            .create(Registries.PARTICLE_TYPE, GTTCore.MOD_ID);

    public static final RegistryObject<ParticleType<MushroomCloudParticleOptions>> MUSHROOM_CLOUD_SMOKE_PARTICLE = PARTICLE_TYPES
            .register("mushroom_cloud_smoke", () -> new ParticleType<>(false, MushroomCloudParticleOptions.DESERIALIZER) {

                @Override
                public Codec<MushroomCloudParticleOptions> codec() {
                    return MushroomCloudParticleOptions.CODEC;
                }
            });

    public static void init(IEventBus modBus) {
        PARTICLE_TYPES.register(modBus);
    }
}
