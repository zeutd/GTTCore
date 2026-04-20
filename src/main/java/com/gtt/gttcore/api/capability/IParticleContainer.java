package com.gtt.gttcore.api.capability;

import com.gtt.gttcore.api.particle.ParticleStack;
import net.minecraft.core.Direction;

public interface IParticleContainer extends IParticleInfoProvider{
    int acceptParticle(ParticleStack stack);
    int extractParticle(int amount);

    boolean canInput(Direction face);
    boolean canOutput(Direction face);
}
