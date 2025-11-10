package com.gtt.gttcore;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.steam.SteamMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifierList;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTCovers;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.data.recipe.event.CraftingComponentModificationEvent;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gtt.gttcore.common.data.*;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.gtt.gttcore.data.GTTDatagen;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.bindings.event.StartupEvents;
import dev.latvian.mods.kubejs.bindings.event.WorldgenEvents;
import dev.latvian.mods.kubejs.level.gen.RemoveWorldgenEventJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.jfr.event.WorldLoadFinishedEvent;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

@Mod(GTTCore.MOD_ID)
public class GTTCore {
    public static final String MOD_ID = "gttcore";
    public static final String NAME = "GTT Core";
    public static final Logger LOGGER = LogManager.getLogger();

    public GTTCore() {
        GTTCore.LOGGER.info("GTT Core");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);
        GTTBlocks.init();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);
        MinecraftForge.EVENT_BUS.addListener(this::modifyCraftingComponent);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
        modEventBus.addListener(EventPriority.LOWEST, GTTDatagen::gatherData);
        // Most other events are fired on Forge"s bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        modEventBus.register(this);
        MinecraftForge.EVENT_BUS.register(this);
        REGISTRATE.registerRegistrate();
    }

    @SubscribeEvent
    public static void onPortalIgnition(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, FormattingUtil.toLowerCaseUnderscore(path));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("GTT Common Setup");
            //LOGGER.info("Look, I found a {}!", Items.DIAMOND);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("GTT Client Setup");
    }

    // You MUST have this for custom materials.
    // Remember to register them not to GT"s namespace, but your own.
    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(GTTCore.MOD_ID);
    }

    // As well as this.
    private void addMaterials(MaterialEvent event) {
        LOGGER.info("GTT Material");
        GTTMaterials.init();
    }

    private void modifyCraftingComponent(CraftingComponentModificationEvent event){
        GTTCraftingComponents.init();
    }

    // This is optional, though.
    private void modifyMaterials(PostMaterialEvent event) {
        GTTMaterials.modify();
    }

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        GTTRecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        GTTMultiMachines.init();
        GTTMachines.init();
    }
}
