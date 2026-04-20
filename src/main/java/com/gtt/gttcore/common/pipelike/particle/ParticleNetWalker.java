package com.gtt.gttcore.common.pipelike.particle;

import com.gregtechceu.gtceu.api.pipenet.PipeNetWalker;
import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.api.capability.forge.GTTCapability;
import com.gtt.gttcore.common.blockentity.ParticlePipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParticleNetWalker extends PipeNetWalker<ParticlePipeBlockEntity, ParticlePipeProperties, ParticlePipeNet> {

    public static final ParticleRoutePath FAILED_MARKER = new ParticleRoutePath(null, null, 0);

    @Nullable
    public static ParticleRoutePath createNetData(ParticlePipeNet world, BlockPos sourcePipe, Direction faceToSourceHandler) {
        try {
            ParticleNetWalker walker = new ParticleNetWalker(world, sourcePipe, 1);
            walker.sourcePipe = sourcePipe;
            walker.facingToHandler = faceToSourceHandler;
            walker.axis = faceToSourceHandler.getAxis();
            walker.traversePipeNet();
            return walker.routePath;
        } catch (Exception e) {
            return FAILED_MARKER;
        }
    }

    private static final Direction[] X_AXIS_FACINGS = { Direction.WEST, Direction.EAST };
    private static final Direction[] Y_AXIS_FACINGS = { Direction.UP, Direction.DOWN };
    private static final Direction[] Z_AXIS_FACINGS = { Direction.NORTH, Direction.SOUTH };

    private ParticleRoutePath routePath;
    private BlockPos sourcePipe;
    private Direction facingToHandler;
    private Direction.Axis axis;

    protected ParticleNetWalker(ParticlePipeNet world, BlockPos sourcePipe, int distance) {
        super(world, sourcePipe, distance);
    }

    @NotNull
    @Override
    protected PipeNetWalker<ParticlePipeBlockEntity, ParticlePipeProperties, ParticlePipeNet> createSubWalker(ParticlePipeNet net,
                                                                                                     Direction direction,
                                                                                                     BlockPos nextPos,
                                                                                                     int walkedBlocks) {
        ParticleNetWalker walker = new ParticleNetWalker(net, nextPos, walkedBlocks);
        walker.facingToHandler = facingToHandler;
        walker.sourcePipe = sourcePipe;
        walker.axis = axis;
        return walker;
    }

    @Override
    protected Class<ParticlePipeBlockEntity> getBasePipeClass() {
        return ParticlePipeBlockEntity.class;
    }

    @Override
    protected void checkPipe(ParticlePipeBlockEntity pipeTile, BlockPos pos) {}

    @Override
    protected Direction[] getSurroundingPipeSides() {
        return switch (axis) {
            case X -> X_AXIS_FACINGS;
            case Y -> Y_AXIS_FACINGS;
            case Z -> Z_AXIS_FACINGS;
        };
    }

    @Override
    protected void checkNeighbour(ParticlePipeBlockEntity pipeNode, BlockPos pipePos, Direction faceToNeighbour,
                                  @org.jetbrains.annotations.Nullable BlockEntity neighbourTile) {
        if (neighbourTile == null || (pipePos.equals(sourcePipe) && faceToNeighbour == facingToHandler)) {
            return;
        }

        if (((ParticleNetWalker) root).routePath == null) {
            IParticleContainer handler = neighbourTile
                    .getCapability(GTTCapability.CAPABILITY_PARTICLE, faceToNeighbour.getOpposite()).resolve().orElse(null);
            if (handler != null) {
                ((ParticleNetWalker) root).routePath = new ParticleRoutePath(pipePos.immutable(), faceToNeighbour,
                        getWalkedBlocks());
                stop();
            }
        }
    }
}