package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeType;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTTRecipeTypes {
    public static void init(){

    }
    public static GTRecipeType register(String name, String group, RecipeType<?>... proxyRecipes) {
        var recipeType = new GTRecipeType(GTCEu.id(name), group, proxyRecipes);
        GTRegistries.register(BuiltInRegistries.RECIPE_TYPE, recipeType.registryName, recipeType);
        GTRegistries.register(BuiltInRegistries.RECIPE_SERIALIZER, recipeType.registryName, new GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(recipeType.registryName, recipeType);
        return recipeType;
    }
    public final static GTRecipeType FISSION_RECIPES = register("fission_reactor", MULTIBLOCK).setMaxIOSize(1, 1, 1, 1)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.WIREMILL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);
    public final static GTRecipeType GREENHOUSE_RECIPES = register("greenhouse", MULTIBLOCK).setMaxIOSize(2, 1, 1, 1)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.WIREMILL_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);
}
