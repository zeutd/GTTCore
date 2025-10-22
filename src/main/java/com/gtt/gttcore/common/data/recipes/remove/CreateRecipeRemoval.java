package com.gtt.gttcore.common.data.recipes.remove;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import dev.latvian.mods.kubejs.recipe.ReplacementMatch;
import dev.latvian.mods.kubejs.recipe.filter.IDFilter;
import dev.latvian.mods.kubejs.recipe.filter.InputFilter;
import dev.latvian.mods.kubejs.recipe.filter.RecipeFilter;
import dev.latvian.mods.kubejs.recipe.filter.TypeFilter;
import earth.terrarium.adastra.common.registry.ModBlocks;
import earth.terrarium.adastra.common.registry.ModRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CreateRecipeRemoval {

    public static void init(Consumer<RecipeFilter> provider) {
        provider.accept(new IDFilter(Create.asResource("sequenced_assembly/sturdy_sheet")));
        provider.accept(new IDFilter(Create.asResource("sequenced_assembly/precision_mechanism")));
        provider.accept(new IDFilter(Create.asResource("pressing/iron_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/gold_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/brass_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/copper_ingot")));
        provider.accept(new IDFilter(Create.asResource("mixing/brass_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/sugar_cane")));
        provider.accept(new IDFilter(Create.asResource("pressing/calorite_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/steel_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/ostrum_ingot")));
        provider.accept(new IDFilter(Create.asResource("pressing/desh_ingot")));
        provider.accept(new IDFilter(Create.asResource("mixing/andesite_alloy_from_zinc")));
        provider.accept(new IDFilter(Create.asResource("mixing/dough_by_mixing")));
        provider.accept(new IDFilter(Create.asResource("mixing/chocolate")));
        provider.accept(new IDFilter(Create.asResource("mixing/tea")));
        provider.accept(new IDFilter(Create.asResource("mixing/andesite_alloy")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
//        provider.accept(new IDFilter(Create.asResource("")));
    }
}
