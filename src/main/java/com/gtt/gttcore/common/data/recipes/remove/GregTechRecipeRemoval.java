package com.gtt.gttcore.common.data.recipes.remove;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.common.data.GTItems;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.filter.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GregTechRecipeRemoval {
    public static void init(Consumer<RecipeFilter> provider) {
        provider.accept(createOutputFilter(GTItems.RESISTOR));
        provider.accept(createInputFilter(ChemicalHelper.get(dust, InertMetalMixture).getItem()));
        provider.accept(createInputFilter(ChemicalHelper.get(dust, RutheniumTetroxide).getItem()));
        provider.accept(createInputFilter(ChemicalHelper.get(dust, IridiumMetalResidue).getItem()));
        provider.accept(createOutputFilter(ChemicalHelper.get(dust, IridiumChloride).getItem()));
    }
    public static void initID(Consumer<ResourceLocation> provider) {
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
        provider.accept(new ResourceLocation("gtceu:shaped/casing_grate_casing"));
        provider.accept(new ResourceLocation("gtceu:assembler/casing_grate_casing"));
        provider.accept(new ResourceLocation("gtceu:shapeless/coated_board_1x"));
        provider.accept(new ResourceLocation("gtceu:shaped/coated_board"));
        provider.accept(new ResourceLocation("gtceu:assembler/basic_circuit_board"));
        provider.accept(new ResourceLocation("gtceu:shaped/basic_circuit_board"));
        provider.accept(new ResourceLocation("gtceu:assembler/phenolic_board"));
        provider.accept(new ResourceLocation("gtceu:shaped/basic_circuit_board"));
        provider.accept(new ResourceLocation("gtceu:mixer/tin_alloy"));
        provider.accept(new ResourceLocation("gtceu:centrifuge/pgs_separation"));
        provider.accept(new ResourceLocation("gtceu:centrifuge/iridium_metal_residue_separation"));
        provider.accept(new ResourceLocation("gtceu:centrifuge/decomposition_centrifuging__platinum_sludge_residue"));
        provider.accept(new ResourceLocation("gtceu:electrolyzer/raw_platinum_separation"));
        provider.accept(new ResourceLocation("gtceu:electrolyzer/rhodium_sulfate_separation"));
        provider.accept(new ResourceLocation("gtceu:distillation_tower/distill_fermented_biomass"));
        provider.accept(new ResourceLocation("gtceu:distillation_tower/acidic_osmium_solution_separation"));
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
//
//    private static final String[] chemicalReactors = new String[]{"chemical_reactor", "large_chemical_reactor", "chemical_plant"};
//    private static void removeChemicalRecipe(Consumer<ResourceLocation> provider, String id){
//
//    }
}
