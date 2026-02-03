package com.gtt.gttcore.common.entity;

import com.gtt.gttcore.common.data.GTTEntityTypes;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class BlackholeEntity extends Entity {
    private final List<Entity> attractEntities = new ArrayList<>();
    public static final float MASS = 10;
    private static final FloatProvider FLOAT_PROVIDER = ClampedNormalFloat.of(0, 20, -30, 30);
    private static final EntityDataAccessor<Integer> DATA_LIFE_ID = SynchedEntityData.defineId(BlackholeEntity.class, EntityDataSerializers.INT);
    @Nullable
    @Setter
    @Getter
    private LivingEntity owner;
    @Getter
    @Setter
    private int life = 160;
    public BlackholeEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner) {
        this(GTTEntityTypes.BLACKHOLE.get(), level);
        this.setPos(x, y, z);
        Vec3 velDir = Vec3.directionFromRotation(random.nextFloat() * (float) Math.PI * 2, random.nextFloat() * (float) Math.PI * 2).multiply(0.2f, 0.2f, 0.2f);
        this.setDeltaMovement(velDir.x, velDir.y, velDir.z);
        this.setLife(160);
        this.setNoGravity(true);
        this.noCulling = true;
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setOwner(owner);
    }

    public BlackholeEntity(EntityType<BlackholeEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        this.move(MoverType.SELF, this.getDeltaMovement());
        if (level() instanceof ClientLevel clientLevel){
            for (int i = 0; i < 9; i++) {
                double sx = FLOAT_PROVIDER.sample(level().random);
                double sy = FLOAT_PROVIDER.sample(level().random);
                double sz = FLOAT_PROVIDER.sample(level().random);
                double length = Vector3d.length(sx, sy, sz);
                clientLevel.addParticle(ParticleTypes.SQUID_INK,
                        sx + position().x, sy + position().y, sz + position().z, -sx / length / length * 20, -sy / length / length * 20, -sz / length / length * 20);
            }
        }
        else {
            if (life % 2 == 0){
                attractEntities.clear();
                attractEntities.addAll(level().getEntities(
                        this,
                        AABB.ofSize(position(), 50, 50, 50),
                        entity -> entity.distanceToSqr(this) <= 25 * 25 && entity.distanceToSqr(this) >= 0.5 * 0.5 && (!(entity instanceof ServerPlayer serverPlayer) || !serverPlayer.isCreative())
                ));
            }
            attractEntities.forEach(entity -> {
                double sx = position().x - entity.position().x;
                double sy = position().y - entity.position().y;
                double sz = position().z - entity.position().z;
                double distanceSqr = Vector3d.lengthSquared(sx, sy, sz);
                if (entity instanceof BlackholeEntity) entity.push(sx / distanceSqr, sy / distanceSqr, sz / distanceSqr);
                else entity.push(sx / distanceSqr * MASS, sy / distanceSqr * MASS, sz / distanceSqr * MASS);
            });
            BlockPos.betweenClosedStream(blockPosition().offset(-10, -10, -10), blockPosition().offset(10, 10, 10)).filter(blockPos -> blockPos.distSqr(this.blockPosition()) < 10 * 10 && level().getBlockState(blockPos).getBlock().defaultDestroyTime() > 0).forEach(blockPos -> level().setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState()));
            if (life <= 0) discard();
        }
        life -= 1;
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_LIFE_ID, 160);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putShort("Life", (short)this.getLife());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.setLife(pCompound.getShort("Life"));
    }
}
