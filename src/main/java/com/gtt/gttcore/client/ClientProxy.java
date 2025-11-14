package com.gtt.gttcore.client;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.CommonProxy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class ClientProxy extends CommonProxy {
    public ClientProxy(){
        super();
        init();
    }

    public static void init(){
        registerDynamicRenderers();
    }

    public static void registerDynamicRenderers(){
        DynamicRenderManager.register(GTTCore.id("large_rotor_holder"), LargeRotorHolderRenderer.TYPE);
    }

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("GTT Client Setup");
    }
}
