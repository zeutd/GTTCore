package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gtt.gttcore.api.DysonWorldSavedData;
import net.minecraft.server.level.ServerLevel;

public class MicrowaveReceiverMachine extends WorkableElectricMultiblockMachine {
    public static final long EU_T_PER_CLOUD = 1L << 20;
    public static final long EU_T_PER_JOINT = 1L << 26;
    public MicrowaveReceiverMachine(BlockEntityCreationInfo info) {
        super(info);
    }

    @Override
    public long getOverclockVoltage() {
        if (!(getLevel() instanceof ServerLevel serverLevel)) return 0;
        var worldData = DysonWorldSavedData.getOrCreate(serverLevel);
        return EU_T_PER_CLOUD * worldData.dysonClouds + EU_T_PER_JOINT * worldData.dysonSphereJoints;
    }
}
