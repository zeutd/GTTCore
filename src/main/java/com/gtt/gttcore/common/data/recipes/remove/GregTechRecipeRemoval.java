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
}
