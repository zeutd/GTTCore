package com.gtt.gttcore.common;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.data.recipe.event.CraftingComponentModificationEvent;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.*;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.gtt.gttcore.GTTCore.LOGGER;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class CommonProxy {
    public CommonProxy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(this);
//        GTTPoiTypes.bootstrap(BuiltInRegistries.POINT_OF_INTEREST_TYPE);
        MinecraftForge.EVENT_BUS.addListener(this::modifyCraftingComponent);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
        REGISTRATE.registerEventListeners(modEventBus);

        // Most other events are fired on Forge"s bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        modEventBus.register(this);
        MinecraftForge.EVENT_BUS.register(this);
        REGISTRATE.registerRegistrate();
    }

    public static void init(){
        GTTBlocks.init();
    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event) {
        event.enqueueWork(CommonProxy::init);
    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("GTT Common Setup");
            //LOGGER.info("Look, I found a {}!", Items.DIAMOND);
        });
    }

    @SubscribeEvent
    // You MUST have this for custom materials.
    // Remember to register them not to GT"s namespace, but your own.
    public void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(GTTCore.MOD_ID);
    }

    @SubscribeEvent
    // As well as this.
    public void addMaterials(MaterialEvent event) {
        LOGGER.info("GTT Material");
        GTTMaterials.init();
    }

    public void modifyCraftingComponent(CraftingComponentModificationEvent event){
        GTTCraftingComponents.init();
    }

    @SubscribeEvent
    // This is optional, though.
    public void modifyMaterials(PostMaterialEvent event) {
        GTTMaterials.modify();
    }

    public void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        GTTRecipeTypes.init();
    }

    public void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        GTTMultiMachines.init();
        GTTMachines.init();
    }
}
