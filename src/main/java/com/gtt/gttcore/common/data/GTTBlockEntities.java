package com.gtt.gttcore.common.data;

import com.gtt.gttcore.api.registry.GTTRegistration;
import com.gtt.gttcore.common.blockentity.ParticlePipeBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;


public class GTTBlockEntities {
    public static void init(){

    }

    public static final BlockEntityEntry<ParticlePipeBlockEntity> PARTICLE_PIPE = GTTRegistration.REGISTRATE
            .blockEntity("particle_pipe", ParticlePipeBlockEntity::new)
            .onRegister(ParticlePipeBlockEntity::onBlockEntityRegister)
            .validBlocks(GTTBlocks.PARTICLE_PIPES)
            .register();
}
