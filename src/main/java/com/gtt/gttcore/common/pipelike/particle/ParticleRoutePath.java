package com.gtt.gttcore.common.pipelike.particle;

import com.gregtechceu.gtceu.api.pipenet.IAttachData;
import com.gregtechceu.gtceu.api.pipenet.IRoutePath;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.capability.IParticleContainer;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParticleRoutePath implements IRoutePath<IParticleContainer>, IAttachData {

    @Getter
    private final BlockPos targetPipePos;
    /**
     * the current face to handler
     */
    @NotNull
    @Getter
    private final Direction targetFacing;
    /**
     * the manhattan distance traveled during walking
     */
    @Getter
    private final int distance;
    @Getter
    byte connections;

    public ParticleRoutePath(BlockPos targetPipePos, Direction targetFacing, int distance) {
        this.targetPipePos = targetPipePos;
        this.targetFacing = targetFacing;
        this.distance = distance;
    }

    /**
     * Gets the handler if it exists
     *
     * @return the handler
     */
    @Nullable
    public IParticleContainer getHandler(Level level) {
        return GTTCapabilityHelper.getParticle(level, getTargetPipePos().relative(targetFacing),
                targetFacing.getOpposite());
    }

    @Override
    public boolean canAttachTo(Direction side) {
        return (connections & (1 << side.ordinal())) != 0 && side.getAxis() == this.targetFacing.getAxis();
    }

    @Override
    public boolean setAttached(Direction side, boolean attach) {
        var result = canAttachTo(side);
        if (result != attach) {
            if (attach) {
                connections |= (1 << side.ordinal());
            } else {
                connections &= ~(1 << side.ordinal());
            }
        }
        return result != attach;
    }
}
