package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;
import com.gtt.gttcore.api.capability.forge.GTTCapability;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = MetaMachine.class, remap = false)
public abstract class MetaMachineMixin {
    @Shadow
    private static <T> List<T> getCapabilitiesFromTraits(List<MachineTrait> traits, @Nullable Direction accessSide, Class<T> capability) {
        return null;
    }

    @Inject(method = "getCapability(Lcom/gregtechceu/gtceu/api/machine/MetaMachine;Lnet/minecraftforge/common/capabilities/Capability;Lnet/minecraft/core/Direction;)Lnet/minecraftforge/common/util/LazyOptional;", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static <T> void getCapabilityMixin(MetaMachine machine, @NotNull Capability<T> cap, @Nullable Direction side, CallbackInfoReturnable<LazyOptional<T>> cir) {
        if (cap == GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_INFO_PROVIDER) {
            if (machine instanceof IHighEnergyLaserInfoProvider highEnergyLaserInfoProvider) {
                cir.setReturnValue(GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_INFO_PROVIDER.orEmpty(cap, LazyOptional.of(() -> highEnergyLaserInfoProvider)));
            }

            var list = getCapabilitiesFromTraits(machine.getTraitHolder().getAllTraits(), side, IHighEnergyLaserInfoProvider.class);
            if (!list.isEmpty()) {
                cir.setReturnValue(GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_INFO_PROVIDER.orEmpty(cap, LazyOptional.of(() -> list.get(0))));
            }
        }
        if (cap == GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_CONTAINER) {
            if (machine instanceof IHighEnergyLaserProvider highEnergyLaserInfoProvider) {
                cir.setReturnValue(GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_CONTAINER.orEmpty(cap, LazyOptional.of(() -> highEnergyLaserInfoProvider)));
            }

            var list = getCapabilitiesFromTraits(machine.getTraitHolder().getAllTraits(), side, IHighEnergyLaserProvider.class);
            if (!list.isEmpty()) {
                cir.setReturnValue(GTTCapability.CAPABILITY_HIGH_ENERGY_LASER_CONTAINER.orEmpty(cap, LazyOptional.of(() -> list.get(0))));
            }
        }
    }
}
