package com.gtt.gttcore;

import com.gtt.gttcore.api.IWirelessEnergyContainerMixin;
import com.gtt.gttcore.common.data.GTTCommands;
import com.gtt.gttcore.util.WirelessUtil;
import com.hepdd.gtmthings.api.misc.WirelessEnergyContainer;
import com.hepdd.gtmthings.data.WirelessEnergySavaedData;
import net.minecraft.core.GlobalPos;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.math.BigInteger;

@Mod.EventBusSubscriber(modid = GTTCore.MOD_ID)
public class GTTEventListener {
    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event) {
        if (ModList.get().isLoaded("gtmthings")) {
            if (event.phase == TickEvent.Phase.END) {
                if (event.getServer().getTickCount() % 200 == 0) {
                    for (WirelessEnergyContainer container : WirelessEnergySavaedData.INSTANCE.containerMap.values()) {
                        BigInteger capacity = BigInteger.ZERO;
                        GlobalPos pos = container.getBindPos();
                        if (pos != null) {
                            capacity = WirelessUtil.getCapacity(event.getServer().getLevel(pos.dimension()), pos.pos());
                        }
                        ((IWirelessEnergyContainerMixin) container).gTTCore$setCapacity(capacity);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPortalIgnition(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        GTTCommands.register(event.getDispatcher(), event.getBuildContext());
    }
}
