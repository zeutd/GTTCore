package com.gtt.gttcore.common.data.recipes.remove;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import dev.latvian.mods.kubejs.recipe.filter.*;
import net.minecraft.resources.ResourceLocation;

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
        provider.accept(new IDFilter(Create.asResource("mixing/compat/ae2/fluix_crystal")));
        provider.accept(new IDFilter(Create.asResource("mixing/lava_from_cobble")));
        provider.accept(createTypeFilter(AllRecipeTypes.CRUSHING.getId()));
        provider.accept(createTypeFilter(AllRecipeTypes.MILLING.getId()));
        provider.accept(createTypeFilter(AllRecipeTypes.CUTTING.getId()));
        provider.accept(new IDFilter(Create.asResource("crafting/materials/andesite_alloy_block")));
        provider.accept(new IDFilter(Create.asResource("crafting/kinetics/whisk")));
        provider.accept(new IDFilter(Create.asResource("crafting/materials/andesite_alloy")));
        provider.accept(new IDFilter(Create.asResource("crafting/materials/andesite_alloy_from_zinc")));
        provider.accept(new IDFilter(Create.asResource("crafting/materials/andesite_alloy_from_block")));
        provider.accept(new IDFilter(Create.asResource("crafting/materials/electron_tube")));
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

    private static RecipeFilter createTypeFilter(ResourceLocation type){
        AndFilter filter = new AndFilter();
        filter.list.add(new ModFilter(Create.ID));
        filter.list.add(new TypeFilter(type));
        return filter;
    }
}
