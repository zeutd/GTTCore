package com.gtt.gttcore.common.pipelike.particle;

import com.gregtechceu.gtceu.api.pipenet.PipeNet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ParticlePipeNet extends PipeNet<ParticlePipeProperties> {

    private final Map<BlockPos, ParticleRoutePath> netData = new Object2ObjectOpenHashMap<>();

    public ParticlePipeNet(LevelParticlePipeNet world) {
        super(world);
    }

    @Nullable
    public ParticleRoutePath getNetData(BlockPos pipePos, Direction facing) {
        ParticleRoutePath data = netData.get(pipePos);
        if (data == null) {
            data = ParticleNetWalker.createNetData(this, pipePos, facing);
            if (data == ParticleNetWalker.FAILED_MARKER) {
                // walker failed, don't cache, so it tries again on next insertion
                return null;
            }

            netData.put(pipePos, data);
        }
        return data;
    }

    @Override
    public void onNeighbourUpdate(BlockPos fromPos) {
        netData.clear();
    }

    @Override
    public void onPipeConnectionsUpdate() {
        netData.clear();
    }

    @Override
    protected void writeNodeData(ParticlePipeProperties ParticlePipeProperties, CompoundTag compoundTag) {}

    @Override
    protected ParticlePipeProperties readNodeData(CompoundTag tagCompound) {
        return ParticlePipeProperties.INSTANCE;
    }

//    @AllArgsConstructor
//    public static class ParticleData implements IAttachData {
//
//        /**
//         * The current position of the pipe
//         */
//        @Getter
//        private final BlockPos pipePos;
//        /**
//         * The current face to handler
//         */
//        @Getter
//        private final Direction faceToHandler;
//        /**
//         * The manhattan distance traveled during walking
//         */
//        @Getter
//        private final int distance;
//        /**
//         * The particle pipe properties of the current pipe
//         */
//        @Getter
//        private final ParticlePipeProperties properties;
//        @Getter
//        byte connections;
//
//        @Override
//        public boolean canAttachTo(Direction side) {
//            return (connections & (1 << side.ordinal())) != 0 && side.getAxis() == this.faceToHandler.getAxis();
//        }
//
//        @Override
//        public boolean setAttached(Direction side, boolean attach) {
//            var result = canAttachTo(side);
//            if (result != attach) {
//                if (attach) {
//                    connections |= (1 << side.ordinal());
//                } else {
//                    connections &= ~(1 << side.ordinal());
//                }
//            }
//            return result != attach;
//        }
//
//        /**
//         * @return The position of where the handler would be
//         */
//        @NotNull
//        public BlockPos getHandlerPos() {
//            return pipePos.relative(faceToHandler);
//        }
//
//        /**
//         * Gets the handler if it exists
//         *
//         * @param world the world to get the handler from
//         * @return the handler
//         */
//        @Nullable
//        public IParticleContainer getHandler(@NotNull Level world) {
//            return GTTCapabilityHelper.getParticle(world, getHandlerPos(), faceToHandler.getOpposite());
//        }
//    }
}
