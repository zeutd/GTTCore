package com.gtt.gttcore.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class DysonWorldSavedData extends SavedData {
    public int dysonClouds = 0;
    public int dysonSphereJoints = 0;
    private DysonWorldSavedData() {

    }

    private DysonWorldSavedData(CompoundTag tag) {
        this();
        dysonClouds = tag.getInt("dysonClouds");
        dysonSphereJoints = tag.getInt("dysonSphereJoints");
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag compound) {
        compound.putInt("dysonClouds", dysonClouds);
        compound.putInt("dysonSphereJoints", dysonSphereJoints);
        return compound;
    }

    public static DysonWorldSavedData getOrCreate(ServerLevel serverLevel) {
        return serverLevel.getDataStorage()
                .computeIfAbsent(DysonWorldSavedData::new, DysonWorldSavedData::new, "gttcore_dyson");
    }
}
