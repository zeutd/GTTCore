package com.gtt.gttcore.api.capability.forge;

import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class GTTCapability {

    public static final Capability<IHighEnergyLaserInfoProvider> CAPABILITY_HIGH_ENERGY_LASER_INFO_PROVIDER = CapabilityManager
            .get(new CapabilityToken<>() {});

    public static final Capability<IHighEnergyLaserProvider> CAPABILITY_HIGH_ENERGY_LASER_CONTAINER = CapabilityManager
            .get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IHighEnergyLaserInfoProvider.class);
        event.register(IHighEnergyLaserProvider.class);
    }
}
