package com.gtt.gttcore.common.entity;

import com.gregtechceu.gtceu.common.entity.GTExplosiveEntity;
import com.gtt.gttcore.common.data.GTTBlocks;
import com.gtt.gttcore.common.data.GTTEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlackholeBombEntity extends GTExplosiveEntity {
    public BlackholeBombEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner) {
        super(GTTEntityTypes.BLACKHOLE_BOMB.get(), level, x, y, z, owner);
    }

    public BlackholeBombEntity(EntityType<? extends BlackholeBombEntity> type, Level world) {
        super(type, world);
    }

    @Override
    protected float getStrength() {
        return 1;
    }

    @Override
    public boolean dropsAllBlocks() {
        return false;
    }

    @Override
    protected void explode(Level level, @Nullable Entity source, double x, double y, double z, float radius, boolean dropBlocks) {
        super.explode(level, source, x, y, z, radius, dropBlocks);
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.addFreshEntity(new BlackholeEntity(serverLevel, x, y, z, getOwner()));
        }
    }

    @Override
    public @NotNull BlockState getExplosiveState() {
        return GTTBlocks.BLACKHOLE_BOMB.getDefaultState();
    }
}
