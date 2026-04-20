package com.gtt.gttcore.common.pipelike.particle;

import com.gregtechceu.gtceu.api.pipenet.LevelPipeNet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

public class LevelParticlePipeNet extends LevelPipeNet<ParticlePipeProperties, ParticlePipeNet> {

    private static final String DATA_ID = "gtceu_particle_pipe_net";

    public static LevelParticlePipeNet getOrCreate(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(tag -> new LevelParticlePipeNet(serverLevel, tag),
                () -> new LevelParticlePipeNet(serverLevel), DATA_ID);
    }

    public LevelParticlePipeNet(ServerLevel serverLevel) {
        super(serverLevel);
    }

    public LevelParticlePipeNet(ServerLevel serverLevel, CompoundTag tag) {
        super(serverLevel, tag);
    }

    @Override
    protected ParticlePipeNet createNetInstance() {
        return new ParticlePipeNet(this);
    }
}
