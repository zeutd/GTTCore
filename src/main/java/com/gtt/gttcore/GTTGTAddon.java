package com.gtt.gttcore;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.integration.kjs.recipe.components.ContentJS;
import com.gtt.gttcore.common.data.*;
import com.gtt.gttcore.common.data.recipes.GTTRecipes;
import com.gtt.gttcore.common.data.recipes.remove.GregTechRecipeRemoval;
import com.mojang.datafixers.util.Pair;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

@SuppressWarnings("unused")
@GTAddon
public class GTTGTAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public void registerOreVeins(){
        GTTOres.init();
    }
    @Override
    public String addonModId() {
        return GTTCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        //CustomTagPrefixes.init();
    }

    @Override
    public void registerElements(){
        GTTElements.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTTRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> provider) {
        GregTechRecipeRemoval.initID(provider);
    }
    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.

    public static final ContentJS<Long> HIGH_ENERGY_LASER_IN = new ContentJS<>(NumberComponent.ANY_LONG, GTTRecipeCapabilities.HIGH_ENERGY_LASER, false);
    public static final ContentJS<Long> HIGH_ENERGY_LASER_OUT = new ContentJS<>(NumberComponent.ANY_LONG, GTTRecipeCapabilities.HIGH_ENERGY_LASER, true);

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        event.registerKey(GTTRecipeCapabilities.HIGH_ENERGY_LASER, Pair.of(HIGH_ENERGY_LASER_IN, HIGH_ENERGY_LASER_OUT));
    }

    @Override
    public boolean requiresHighTier() {
        return true;
    }

    @Override
    public void collectMaterialCasings(MaterialCasingCollectionEvent event){
        event.add(Iridium, CASING_IRIDIUM_REINFORCED);
        event.add(Lead, CASING_LEAD_RADIATION_PROOF);
        event.add(Zirconium, CASING_LOW_NEUTRON_ABSORPTION);
        event.add(AtomicSteel, CASING_ATOMIC);
    }

    @Override
    public void registerCovers(){
        GTTCovers.init();
        GTTItems.init();
    }

    @Override
    public void registerRecipeCapabilities() {
        GTTRecipeCapabilities.init();
    }
}
