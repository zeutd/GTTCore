package com.gtt.gttcore.common.data.recipes.remove;

import com.gregtechceu.gtceu.GTCEu;
import dev.latvian.mods.kubejs.recipe.filter.RecipeFilter;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class GregTechRecipeRemoval {
    public static void init(Consumer<RecipeFilter> provider) {

    }
    public static void initID(Consumer<ResourceLocation> provider) {
        provider.accept(new ResourceLocation("gtceu:shapeless/dust_brass"));
//        provider.accept(new ResourceLocation(""));
//        provider.accept(new ResourceLocation(""));
//        provider.accept(new ResourceLocation(""));
//        provider.accept(new ResourceLocation(""));

    }
}
