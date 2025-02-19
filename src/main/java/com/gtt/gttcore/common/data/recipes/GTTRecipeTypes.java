package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gtt.gttcore.GTTCore;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeType;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTTRecipeTypes {
    public static void init(){

    }
    public static GTRecipeType register(String name, String group, RecipeType<?>... proxyRecipes) {
        var recipeType = new GTRecipeType(GTTCore.id(name), group, proxyRecipes);
        GTRegistries.register(BuiltInRegistries.RECIPE_TYPE, recipeType.registryName, recipeType);
        GTRegistries.register(BuiltInRegistries.RECIPE_SERIALIZER, recipeType.registryName, new GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(recipeType.registryName, recipeType);
        return recipeType;
    }
    public final static GTRecipeType FISSION_RECIPES = register("fission_reactor", GENERATOR).setMaxIOSize(1, 1, 1, 1)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.WIREMILL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);
    public final static GTRecipeType GREENHOUSE_RECIPES = register("greenhouse", MULTIBLOCK).setMaxIOSize(3, 6, 1, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CUT);
    public final static GTRecipeType SUPERCRITICAL_STEAM_TURBINE_FUELS = register("supercritical_steam_turbine", GENERATOR).setMaxIOSize(0, 0, 1, 1)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.TURBINE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);
    public final static GTRecipeType PLASMA_HEAT_EXCHANGER_RECIPES  = register("plasma_heat_exchanger", GENERATOR).setMaxIOSize(0, 0, 2, 2)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BOILER_FUEL.get(true), LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BOILER);
}
