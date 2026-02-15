package com.gtt.gttcore.common.data.recipes.remove;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.common.data.GTItems;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.filter.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

@SuppressWarnings("removal")
public class GregTechRecipeRemoval {
    public static void init(Consumer<RecipeFilter> provider) {
        provider.accept(createOutputFilter(GTItems.RESISTOR));
    }
    public static void initID(Consumer<ResourceLocation> provider) {
        if (GTCEu.Mods.isCreateLoaded()) {
            provider.accept(new ResourceLocation("gtceu:shapeless/dust_brass"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_steel"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_wrought_iron"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_iron"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_brass"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_bronze"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_copper"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_electrum"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_lead"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_tin"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_gold"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_zinc"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_invar"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_silver"));
            provider.accept(new ResourceLocation("gtceu:shaped/mortar_grind_annealed_copper"));
        }
        provider.accept(new ResourceLocation("gtceu:shaped/casing_grate_casing"));
        provider.accept(new ResourceLocation("gtceu:assembler/casing_grate_casing"));
        provider.accept(new ResourceLocation("gtceu:shapeless/coated_board_1x"));
        provider.accept(new ResourceLocation("gtceu:shaped/coated_board"));
        provider.accept(new ResourceLocation("gtceu:assembler/basic_circuit_board"));
        provider.accept(new ResourceLocation("gtceu:shaped/basic_circuit_board"));
        provider.accept(new ResourceLocation("gtceu:assembler/phenolic_board"));
        provider.accept(new ResourceLocation("gtceu:shaped/basic_circuit_board"));
        provider.accept(new ResourceLocation("gtceu:mixer/tin_alloy"));
        provider.accept(new ResourceLocation("gtceu:mixer/graphene"));






        provider.accept(new ResourceLocation("gtceu:centrifuge/pgs_separation"));
        provider.accept(new ResourceLocation("gtceu:centrifuge/decomposition_centrifuging__platinum_sludge_residue"));
        provider.accept(new ResourceLocation("gtceu:electrolyzer/raw_platinum_separation"));
        removeChemical(provider, "raw_palladium_separation");
        removeChemical(provider, "inert_metal_mixture_separation");
        removeChemical(provider, "ruthenium_tetroxide_separation");
        provider.accept(new ResourceLocation("gtceu:electrolyzer/rhodium_sulfate_separation"));
        removeChemical(provider, "rarest_metal_mixture_separation");
        provider.accept(new ResourceLocation("gtceu:centrifuge/iridium_metal_residue_separation"));
        removeChemical(provider, "iridium_chloride_separation");
        removeChemical(provider, "qbit_cpu_wafer_radon");
        removeChemical(provider, "qbit_cpu_wafer_quantum_eye");
        provider.accept(new ResourceLocation("gtceu:distillation_tower/acidic_osmium_solution_separation"));



        //provider.accept(new ResourceLocation("gtceu:centrifuge/red_sand_separation"));


        provider.accept(new ResourceLocation("gtceu:centrifuge/rare_earth_separation"));
    }

    private static void removeChemical(Consumer<ResourceLocation> provider, String location){
        provider.accept(new ResourceLocation("gtceu:chemical_reactor/%s".formatted(location)));
        provider.accept(new ResourceLocation("gtceu:large_chemical_reactor/%s".formatted(location)));
        provider.accept(new ResourceLocation("gtceu:chemical_plant/%s".formatted(location)));
    }

    private static RecipeFilter createOutputFilter(ItemLike output){
        AndFilter filter = new AndFilter();
        filter.list.add(new ModFilter(GTCEu.MOD_ID));
        filter.list.add(new OutputFilter(new IngredientMatch(Ingredient.of(output), false)));
        return filter;
    }

    private static RecipeFilter createInputFilter(ItemLike input){
        AndFilter filter = new AndFilter();
        filter.list.add(new ModFilter(GTCEu.MOD_ID));
        filter.list.add(new InputFilter(new IngredientMatch(Ingredient.of(input), false)));
        return filter;
    }

    private static RecipeFilter createInputFilter(ItemLike input, ResourceLocation recipeType){
        AndFilter filter = new AndFilter();
        filter.list.add(new ModFilter(GTCEu.MOD_ID));
        filter.list.add(new TypeFilter(recipeType));
        filter.list.add(new InputFilter(new IngredientMatch(Ingredient.of(input), false)));
        return filter;
    }
}
