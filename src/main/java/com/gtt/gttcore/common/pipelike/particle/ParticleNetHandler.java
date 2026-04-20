package com.gtt.gttcore.common.pipelike.particle;

import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.common.blockentity.ParticlePipeBlockEntity;
import lombok.Getter;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParticleNetHandler implements IParticleContainer {

    @Getter
    private ParticlePipeNet net;
    private final ParticlePipeBlockEntity pipe;
    private final Direction facing;

    public ParticleNetHandler(ParticlePipeNet net, @NotNull ParticlePipeBlockEntity pipe, @Nullable Direction facing) {
        this.net = net;
        this.pipe = pipe;
        this.facing = facing;
    }

    public void updateNetwork(ParticlePipeNet net) {
        this.net = net;
    }

    @Nullable
    private IParticleContainer getInnerContainer() {
        if (net == null || pipe.isRemoved() || facing == null || pipe.isBlocked(facing)) {
            return null;
        }

        ParticleRoutePath data = net.getNetData(pipe.getBlockPos(), facing);
        if (data == null) {
            return null;
        }

        return data.getHandler(net.getLevel());
    }

    @Override
    public int acceptParticle(ParticleStack stack) {
        IParticleContainer handler = getInnerContainer();
        if (handler == null) return 0;
        return handler.acceptParticle(stack);
    }

    @Override
    public int extractParticle(int amount) {
        IParticleContainer handler = getInnerContainer();
        if (handler == null) return 0;
        return handler.extractParticle(amount);
    }

    @Override
    public boolean canInput(Direction face) {
        IParticleContainer handler = getInnerContainer();
        if (handler == null) return false;
        return handler.canInput(face);
    }

    @Override
    public boolean canOutput(Direction face) {
        IParticleContainer handler = getInnerContainer();
        if (handler == null) return false;
        return handler.canOutput(face);
    }

    @Override
    public ParticleStack getStored() {
        IParticleContainer handler = getInnerContainer();
        if (handler == null) return null;
        return handler.getStored();
    }

    @Override
    public int getCapacity() {
        IParticleContainer handler = getInnerContainer();
        if (handler == null) return 0;
        return handler.getCapacity();
    }
}
