package com.gtt.gttcore.api.capability;

import com.gtt.gttcore.api.particle.ParticleStack;

public interface IParticleInfoProvider {
    ParticleStack getStored();

    int getCapacity();
}
