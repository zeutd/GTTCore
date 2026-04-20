package com.gtt.gttcore.common.data.recipes.remove;

import dev.latvian.mods.kubejs.recipe.filter.IDFilter;
import dev.latvian.mods.kubejs.recipe.filter.RecipeFilter;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

@SuppressWarnings("removal")
public class BWGRecipeRemoval {
    public static void init(Consumer<RecipeFilter> provider) {
        provider.accept(new IDFilter(new ResourceLocation("biomeswevegone:purple_stained_glass_from_purple_sand")));
        provider.accept(new IDFilter(new ResourceLocation("biomeswevegone:black_stained_glass_from_black_sand")));
        provider.accept(new IDFilter(new ResourceLocation("biomeswevegone:white_stained_glass_from_white_sand")));
        provider.accept(new IDFilter(new ResourceLocation("biomeswevegone:pink_stained_glass_from_pink_sand")));
        provider.accept(new IDFilter(new ResourceLocation("biomeswevegone:blue_stained_glass_from_blue_sand")));
    }
}
