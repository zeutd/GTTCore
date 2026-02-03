package com.gtt.gttcore.client;

import com.gregtechceu.gtceu.client.renderer.entity.GTExplosiveRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.client.particles.MushroomCloudParticle;
import com.gtt.gttcore.client.renderers.BlackholeRenderer;
import com.gtt.gttcore.client.renderers.LargeRotorHolderRenderer;
import com.gtt.gttcore.common.CommonProxy;
import com.gtt.gttcore.common.data.GTTEntityTypes;
import com.gtt.gttcore.common.data.GTTParticleTypes;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class ClientProxy extends CommonProxy {
    public ClientProxy(){
        super();
        init();
    }

    @SubscribeEvent
    public void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(BlackholeRenderer.SPHERE_MODEL_LOCATION);
    }

    public static void init(){
        registerDynamicRenderers();
    }

    public static void registerDynamicRenderers(){
        DynamicRenderManager.register(GTTCore.id("large_rotor_holder"), LargeRotorHolderRenderer.TYPE);
    }

    @SubscribeEvent
    public void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GTTEntityTypes.BLACKHOLE_BOMB.get(), GTExplosiveRenderer::new);
        event.registerEntityRenderer(GTTEntityTypes.NUCLEAR_BOMB.get(), GTExplosiveRenderer::new);
        event.registerEntityRenderer(GTTEntityTypes.MUSHROOM_CLOUD.get(), NoopRenderer::new);
        event.registerEntityRenderer(GTTEntityTypes.BLACKHOLE.get(), BlackholeRenderer::new);
    }

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("GTT Client Setup");
    }

    @SubscribeEvent
    public void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(GTTParticleTypes.MUSHROOM_CLOUD_SMOKE_PARTICLE.get(), MushroomCloudParticle.Provider::new);
    }
//    @SubscribeEvent
//    public void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
//        event.registerLayerDefinition(MushroomCloudModel.LAYER_LOCATION, MushroomCloudModel::createBodyLayer);
//    }
}
