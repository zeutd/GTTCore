package com.gtt.gttcore.common;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gtt.gttcore.common.data.GTTMultiMachines;
import com.gtt.gttcore.common.data.GTTPoiTypes;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("deprecated")
public class CustomNetherTeleporter extends PortalForcer {
    public CustomNetherTeleporter(ServerLevel pLevel) {
        super(pLevel);
    }

    public @NotNull Optional<BlockUtil.FoundRectangle> findPortalAround(@NotNull BlockPos pPos, boolean pIsNether, @NotNull WorldBorder pWorldBorder) {
        PoiManager poimanager = this.level.getPoiManager();
        int i = pIsNether ? 16 : 128;
        poimanager.ensureLoadedAndValid(this.level, pPos, i);
        Optional<PoiRecord> optional = poimanager.getInSquare((p_230634_) -> {
            return p_230634_.is(GTTPoiTypes.NETHER_TRAVELLER);
        }, pPos, i, PoiManager.Occupancy.ANY).filter((p_192981_) -> {
            return pWorldBorder.isWithinBounds(p_192981_.getPos());
        }).min(Comparator.<PoiRecord>comparingDouble((p_192984_) -> {
            return p_192984_.getPos().distSqr(pPos);
        }).thenComparingInt((p_192992_) -> {
            return p_192992_.getPos().getY();
        }));
        return optional.map((poiRecord) -> {
            BlockPos blockpos = poiRecord.getPos();
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockState blockstate = this.level.getBlockState(blockpos);
            return new BlockUtil.FoundRectangle(blockpos.below().relative(blockstate.getOptionalValue(GTTMultiMachines.NETHER_CAPSULE.getRotationState().property).orElse(Direction.EAST)), 1, 1);
        });
    }

    public @NotNull Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pPos, Direction.@NotNull Axis pAxis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, pAxis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder worldborder = this.level.getWorldBorder();
        int i = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for(BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.spiralAround(pPos, 16, Direction.EAST, Direction.SOUTH)) {
            int j = Math.min(i, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getZ()));
            if (worldborder.isWithinBounds(blockpos$mutableblockpos1) && worldborder.isWithinBounds(blockpos$mutableblockpos1.move(direction, 1))) {
                blockpos$mutableblockpos1.move(direction.getOpposite(), 1);

                for(int l = j; l >= this.level.getMinBuildHeight(); --l) {
                    blockpos$mutableblockpos1.setY(l);
                    if (this.canPortalReplaceBlock(blockpos$mutableblockpos1)) {
                        int i1;
                        for(i1 = l; l > this.level.getMinBuildHeight() && this.canPortalReplaceBlock(blockpos$mutableblockpos1.move(Direction.DOWN)); --l) {
                        }

                        if (l + 4 <= i) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                blockpos$mutableblockpos1.setY(l);
                                if (this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 0)) {
                                    double d2 = pPos.distSqr(blockpos$mutableblockpos1);
                                    if (this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, -1) && this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = blockpos$mutableblockpos1.immutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = blockpos$mutableblockpos1.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0D && d1 != -1.0D) {
            blockpos = blockpos1;
            d0 = d1;
        }

        BlockState frameBlockState = GTBlocks.CASING_TITANIUM_STABLE.getDefaultState();

        if (d0 == -1.0D) {
            int k1 = Math.max(this.level.getMinBuildHeight() - -1, 70);
            int i2 = i - 9;
            if (i2 < k1) {
                return Optional.empty();
            }

            blockpos = (new BlockPos(pPos.getX(), Mth.clamp(pPos.getY(), k1, i2), pPos.getZ())).immutable();
            if (!worldborder.isWithinBounds(blockpos)) {
                return Optional.empty();
            }
        }

        for (int y1 = 0; y1 <= 1; y1++) {
            for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                Direction dir = Direction.from2DDataValue(dirIndex);
                blockpos$mutableblockpos.setWithOffset(blockpos, dir.getStepX(), y1, dir.getStepZ());
                level.setBlockAndUpdate(blockpos$mutableblockpos, frameBlockState);
            }
        }

        blockpos$mutableblockpos.setWithOffset(blockpos, 0, -1, 0);
        level.setBlockAndUpdate(blockpos$mutableblockpos, frameBlockState);

        blockpos$mutableblockpos.setWithOffset(blockpos, 0, 2, 0);
        level.setBlockAndUpdate(blockpos$mutableblockpos, frameBlockState);

        for (int y1 = 0; y1 <= 1; y1++) {
            blockpos$mutableblockpos.setWithOffset(blockpos, direction.getOpposite().getStepX(), y1, direction.getOpposite().getStepZ());
            level.setBlockAndUpdate(blockpos$mutableblockpos, Blocks.AIR.defaultBlockState());
            blockpos$mutableblockpos.setWithOffset(blockpos, 0, y1, 0);
            level.setBlockAndUpdate(blockpos$mutableblockpos, Blocks.AIR.defaultBlockState());
        }

        BlockState blockstate = GTTMultiMachines.NETHER_CAPSULE.defaultBlockState().setValue(GTTMultiMachines.NETHER_CAPSULE.getRotationState().property, direction.getOpposite());

        blockpos$mutableblockpos.setWithOffset(blockpos, direction.getStepX(), 1, direction.getStepZ());
        this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate);

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.relative(direction.getOpposite()).immutable(), 1, 1));
    }


    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pPos) {
        BlockState blockstate = this.level.getBlockState(pPos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }
    private boolean canHostFrame(BlockPos pOriginalPos, BlockPos.MutableBlockPos pOffsetPos, Direction pDirection, int pOffsetScale) {
        Direction direction = pDirection.getClockWise();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 2; ++j) {
                pOffsetPos.setWithOffset(pOriginalPos, pDirection.getStepX() * i + direction.getStepX() * pOffsetScale, j, pDirection.getStepZ() * i + direction.getStepZ() * pOffsetScale);
                if (j < 0 && !this.level.getBlockState(pOffsetPos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.canPortalReplaceBlock(pOffsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo)
    {
        WorldBorder worldborder = destWorld.getWorldBorder();
        double scale = DimensionType.getTeleportationScale(entity.level().dimensionType(), destWorld.dimensionType());
        BlockPos blockpos1 = worldborder.clampToBounds(entity.getX() * scale, entity.getY(), entity.getZ() * scale);
        return findPortalAround(blockpos1, destWorld.dimension() == ServerLevel.NETHER, worldborder)
                .map(rect -> PortalShape.createPortalInfo(destWorld, rect, Direction.Axis.X, Vec3.ZERO, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot()))
                .orElseGet(() -> createPortal(blockpos1, Direction.Axis.X).map(rect -> PortalShape.createPortalInfo(destWorld, rect, Direction.Axis.X, Vec3.ZERO, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot()))
                .orElse(null));
    }
}
