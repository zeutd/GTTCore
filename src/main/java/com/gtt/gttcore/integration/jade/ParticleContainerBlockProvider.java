package com.gtt.gttcore.integration.jade;

import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.capability.IParticleInfoProvider;
import com.gtt.gttcore.api.registry.GTTRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

@SuppressWarnings("removal")
public class ParticleContainerBlockProvider extends CapabilityBlockProvider<IParticleInfoProvider> {
    protected ParticleContainerBlockProvider() {
        super(GTTCore.id("particle_container_provider"));
    }

    @Nullable
    @Override
    protected IParticleInfoProvider getCapability(Level level, BlockPos pos, @Nullable Direction side) {
        return GTTCapabilityHelper.getParticleInfoProvider(level, pos, side);
    }

    @Override
    protected void write(CompoundTag data, IParticleInfoProvider capability) {
        if (capability.getStored() != null && !capability.getStored().isEmpty()) {
            data.putInt("ParticleAmount", capability.getStored().getAmount());
            data.putString("ParticleName", GTTRegistries.PARTICLE_TYPES.getKey(capability.getStored().getType()).toString());
        }
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block, BlockEntity blockEntity, IPluginConfig config) {
        if (!capData.contains("ParticleAmount") || !capData.contains("ParticleName")) return;
        int particleAmount = capData.getInt("ParticleAmount");
        String particleName = capData.getString("ParticleName");
        tooltip.add(Component.literal(particleAmount + "")
                .append(CommonComponents.space())
                .append(Component.translatable(new ResourceLocation(particleName).toLanguageKey("particle_type")))
        );

    }
}
