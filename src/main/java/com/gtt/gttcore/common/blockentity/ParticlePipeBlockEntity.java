package com.gtt.gttcore.common.blockentity;

import com.gregtechceu.gtceu.api.blockentity.PipeBlockEntity;
import com.gregtechceu.gtceu.api.capability.GTCapability;
import com.gregtechceu.gtceu.api.pipenet.IPipeNode;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.api.capability.forge.GTTCapability;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.common.pipelike.particle.*;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.EnumMap;

public class ParticlePipeBlockEntity extends PipeBlockEntity<ParticlePipeType, ParticlePipeProperties> {
    @Getter
    protected final EnumMap<Direction, ParticleNetHandler> handlers = new EnumMap<>(Direction.class);
    // the ParticleNetHandler can only be created on the server, so we have an empty placeholder for the client
    public final IParticleContainer clientCapability = new DefaultParticleContainer();
    private WeakReference<ParticlePipeNet> currentPipeNet = new WeakReference<>(null);
    @Getter
    protected ParticleNetHandler defaultHandler;

    public ParticlePipeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void onBlockEntityRegister(BlockEntityType<ParticlePipeBlockEntity> cableBlockEntityBlockEntityType) {}

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == GTTCapability.CAPABILITY_PARTICLE) {
            if (getLevel().isClientSide())
                return GTTCapability.CAPABILITY_PARTICLE.orEmpty(cap, LazyOptional.of(() -> clientCapability));
            if (side != null && !isConnected(side)) return LazyOptional.empty();
            if (handlers.isEmpty()) {
                initHandlers();
            }
            checkNetwork();
            return GTTCapability.CAPABILITY_PARTICLE.orEmpty(cap,
                    LazyOptional.of(() -> handlers.getOrDefault(side, defaultHandler)));
        } else if (cap == GTCapability.CAPABILITY_COVERABLE) {
            return GTCapability.CAPABILITY_COVERABLE.orEmpty(cap, LazyOptional.of(this::getCoverContainer));
        }
        return super.getCapability(cap, side);
    }

    @Override
    public boolean canHaveBlockedFaces() {
        return false;
    }

    public void initHandlers() {
        ParticlePipeNet net = getParticlePipeNet();
        if (net == null) return;
        for (Direction facing : GTUtil.DIRECTIONS) {
            handlers.put(facing, new ParticleNetHandler(net, this, facing));
        }
        defaultHandler = new ParticleNetHandler(net, this, null);
    }

    public void checkNetwork() {
        if (defaultHandler != null) {
            ParticlePipeNet current = getParticlePipeNet();
            if (defaultHandler.getNet() != current) {
                defaultHandler.updateNetwork(current);
                for (ParticleNetHandler handler : handlers.values()) {
                    handler.updateNetwork(current);
                }
            }
        }
    }

    public ParticlePipeNet getParticlePipeNet() {
        if (level == null || level.isClientSide) {
            return null;
        }
        ParticlePipeNet currentPipeNet = this.currentPipeNet.get();
        if (currentPipeNet != null && currentPipeNet.isValid() && currentPipeNet.containsNode(this.getBlockPos())) {
            return currentPipeNet;
        }
        LevelParticlePipeNet worldNet = (LevelParticlePipeNet) getPipeBlock().getWorldPipeNet((ServerLevel) this.getLevel());
        currentPipeNet = worldNet.getNetFromPos(this.getBlockPos());
        if (currentPipeNet != null) {
            this.currentPipeNet = new WeakReference<>(currentPipeNet);
        }
        return currentPipeNet;
    }

    @Override
    public boolean canAttachTo(Direction side) {
        if (level != null) {
            if (level.getBlockEntity(getBlockPos().relative(side)) instanceof ParticlePipeBlockEntity) {
                return false;
            }
            return GTTCapabilityHelper.getParticle(level, getBlockPos().relative(side), side.getOpposite()) != null;
        }
        return false;
    }

    @Override
    public void setConnection(Direction side, boolean connected, boolean fromNeighbor) {
        if (!getLevel().isClientSide && connected) {
            int connections = getConnections();
            // block connection if any side other than the requested side and its opposite side are already connected.
            connections &= ~(1 << side.ordinal());
            connections &= ~(1 << side.getOpposite().ordinal());
            if (connections != 0) return;

            // check the same for the targeted pipe
            BlockEntity tile = getLevel().getBlockEntity(getBlockPos().relative(side));
            if (tile instanceof IPipeNode<?, ?> pipeTile &&
                    pipeTile.getPipeType().getClass() == this.getPipeType().getClass()) {
                connections = pipeTile.getConnections();
                connections &= ~(1 << side.ordinal());
                connections &= ~(1 << side.getOpposite().ordinal());
                if (connections != 0) return;
            }
        }
        super.setConnection(side, connected, fromNeighbor);
    }

    private static class DefaultParticleContainer implements IParticleContainer {

        @Override
        public int acceptParticle(ParticleStack stack) {
            return 0;
        }

        @Override
        public int extractParticle(int amount) {
            return 0;
        }

        @Override
        public ParticleStack getStored() {
            return ParticleStack.EMPTY;
        }

        @Override
        public int getCapacity() {
            return 0;
        }

        @Override
        public boolean canInput(Direction face) {
            return false;
        }

        @Override
        public boolean canOutput(Direction face) {
            return false;
        }
    }
}


