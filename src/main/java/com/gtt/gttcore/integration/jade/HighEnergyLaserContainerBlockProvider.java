package com.gtt.gttcore.integration.jade;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.Accessor;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class HighEnergyLaserContainerBlockProvider extends CapabilityBlockProvider<IHighEnergyLaserInfoProvider> {
    protected HighEnergyLaserContainerBlockProvider() {
        super(GTTCore.id("high_energy_laser_container_provider"));
    }

    @Nullable
    @Override
    protected IHighEnergyLaserInfoProvider getCapability(Level level, BlockPos pos, @Nullable Direction side) {
        return GTTCapabilityHelper.getHighEnergyLaserInfoProvider(level, pos, side);
    }

    @Override
    protected void write(CompoundTag data, IHighEnergyLaserInfoProvider capability) {
        data.putInt("HighEnergyLaser", capability.getLaserAmount());
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block, BlockEntity blockEntity, IPluginConfig config) {
        if (!capData.contains("HighEnergyLaser")) return;
        int laserAmount = capData.getInt("HighEnergyLaser");
        tooltip.add(
                Component.translatable("gttcore.machine.high_energy_laser_amount", laserAmount)
        );
    }
}
