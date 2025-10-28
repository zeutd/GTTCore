package com.gtt.gttcore.api;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.IEnergyInfoProvider;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;
import com.gtt.gttcore.api.capability.forge.GTTCapability;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class GTTCapabilityHelper {
    @Nullable
    public static IHighEnergyLaserInfoProvider getHighEnergyLaserInfoProvider(Level level, BlockPos pos, @Nullable Direction side) {
        return getBlockEntityCapability(GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_INFO_PROVIDER, level, pos, side);
    }

    @Nullable
    public static IHighEnergyLaserProvider getHighEnergyLaserContainer(Level level, BlockPos pos, @Nullable Direction side) {
        return getBlockEntityCapability(GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_CONTAINER, level, pos, side);
    }

    @Nullable
    private static <T> T getBlockEntityCapability(Capability<T> capability, Level level, BlockPos pos,
                                                  @Nullable Direction side) {
        if (level.getBlockState(pos).hasBlockEntity()) {
            var blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null) {
                return blockEntity.getCapability(capability, side).resolve().orElse(null);
            }
        }
        return null;
    }
}
