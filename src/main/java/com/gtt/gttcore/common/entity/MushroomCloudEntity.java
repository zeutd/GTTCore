package com.gtt.gttcore.common.entity;

import com.gtt.gttcore.client.particles.MushroomCloudParticleOptions;
import com.gtt.gttcore.common.data.GTTEntityTypes;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class MushroomCloudEntity extends Entity {
    @Getter
    @Setter
    private int time;
    private static final EntityDataAccessor<Integer> DATA_TIME_ID = SynchedEntityData.defineId(MushroomCloudEntity.class, EntityDataSerializers.INT);
    public MushroomCloudEntity(Level level, double x, double y, double z) {
        this(GTTEntityTypes.MUSHROOM_CLOUD.get(), level);
        this.setPos(x, y, z);
        this.noPhysics = true;
        this.noCulling = true;
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public MushroomCloudEntity(EntityType<MushroomCloudEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        time++;
        if(level() instanceof ClientLevel clientLevel) {
            for (int i = 0; i < 30; i++) {
                clientLevel.addParticle(new MushroomCloudParticleOptions((float) (random.nextFloat() * Math.PI * 2), (float) getX() + random.nextFloat() * 0.5f, (float) getY() + random.nextFloat() * 0.5f, (float) getZ() + random.nextFloat() * 0.5f), getX(), getY(), getZ(), 0, 0, 0);
            }
        }
        if(time >= 400) discard();
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_TIME_ID, 0);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putShort("Time", (short)this.getTime());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.setTime(pCompound.getShort("Time"));
    }
}
