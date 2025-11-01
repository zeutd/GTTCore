package com.gtt.gttcore.common.data.recipes.remove;

import dev.latvian.mods.kubejs.recipe.filter.IDFilter;
import dev.latvian.mods.kubejs.recipe.filter.RecipeFilter;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class AE2RecipeRemoval {
    public static void init(Consumer<RecipeFilter> provider) {
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/inscribers")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:decorative/quartz_glass")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/crystal_processing_charger")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/engineering_processor_print")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/certus_quartz_dust")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/engineering_processor")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/ender_dust")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/calculation_processor")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/engineering_processor_press")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/calculation_processor_press")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/logic_processor_press")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/silicon_press")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/logic_processor")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/logic_processor_print")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/silicon_print")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/fluix_dust")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/calculation_processor_print")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:inscriber/sky_stone_dust")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/crystal_resonance_generator")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/energy_vibration_chamber")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/crystal_processing_growth_accelerator")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/energy_energy_cell")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/io_condenser")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:charger/charged_certus_quartz_crystal")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:charger/meteorite_compass")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:charger/guide")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:network/blocks/controller")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:smelting/silicon_from_certus_quartz_dust")));
        provider.accept(new IDFilter(new ResourceLocation("ae2:decorative/quartz_block")));
    }
}
