package com.gtt.gttcore.common.data.recipes;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.CertusQuartz;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.FluixCrystal;

public class IntegrationRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        registerAE2Recipes(provider);
        registerAdAstraRecipes(provider);
    }

    public static void registerAdAstraRecipes(Consumer<FinishedRecipe> provider) {

    }
    public static void registerAE2Recipes(Consumer<FinishedRecipe> provider) {
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("calculation_processor_press"))
                .notConsumable(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("engineering_processor_press"))
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("logic_processor_press"))
                .notConsumable(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("silicon_processor_press"))
                .notConsumable(AEItems.SILICON_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.SILICON_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("silicon_print"))
                .notConsumable(AEItems.SILICON_PRESS.asItem())
                .inputItems(plate, Silicon)
                .outputItems(AEItems.SILICON_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("engineering_processor_print"))
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .inputItems(plate, Diamond)
                .outputItems(AEItems.ENGINEERING_PROCESSOR_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("logic_processor_print"))
                .notConsumable(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .inputItems(plate, Gold)
                .outputItems(AEItems.LOGIC_PROCESSOR_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("calculation_processor_print"))
                .notConsumable(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .inputItems(plate, CertusQuartz)
                .outputItems(AEItems.CALCULATION_PROCESSOR_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("calculation_processor"))
                .inputItems(AEItems.CALCULATION_PROCESSOR_PRINT.asItem())
                .inputItems(plate, Redstone)
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .outputItems(AEItems.CALCULATION_PROCESSOR.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("engineering_processor"))
                .inputItems(AEItems.ENGINEERING_PROCESSOR_PRINT.asItem())
                .inputItems(plate, Redstone)
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .outputItems(AEItems.ENGINEERING_PROCESSOR.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("logic_processor"))
                .inputItems(AEItems.LOGIC_PROCESSOR_PRINT.asItem())
                .inputItems(plate, Redstone)
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .outputItems(AEItems.LOGIC_PROCESSOR.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        ALLOY_SMELTER_RECIPES.recipeBuilder(GTTCore.id("quartz_glass"))
                .inputItems(gem, CertusQuartz)
                .inputItems(block, Glass)
                .outputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .EUt(VA[LV])
                .duration(100)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder(GTTCore.id("quartz_vibrant_glass"))
                .inputItems(dust, Glowstone)
                .inputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .outputItems(AEBlocks.QUARTZ_VIBRANT_GLASS.asItem())
                .EUt(VA[LV])
                .duration(100)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("fluix_crystal"))
                .inputItems(dust, Redstone)
                .inputItems(dust, CertusQuartz)
                .inputItems(dust, NetherQuartz)
                .inputFluids(Water.getFluid(250))
                .outputItems(gem, FluixCrystal, 2)
                .duration(20 * 90)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("fluix_crystal_distilled"))
                .inputItems(dust, Redstone)
                .inputItems(dust, CertusQuartz)
                .inputItems(dust, NetherQuartz)
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(gem, FluixCrystal, 2)
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("damaged_budding_quartz"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(block, CertusQuartz)
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(AEBlocks.DAMAGED_BUDDING_QUARTZ.asItem())
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("chipped_budding_quartz"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(AEBlocks.DAMAGED_BUDDING_QUARTZ.asItem())
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(AEBlocks.CHIPPED_BUDDING_QUARTZ.asItem())
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("flawed_budding_quartz"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(AEBlocks.CHIPPED_BUDDING_QUARTZ.asItem())
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(AEBlocks.FLAWED_BUDDING_QUARTZ.asItem())
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        MACERATOR_RECIPES.recipeBuilder(GTTCore.id("sky_dust"))
                .inputItems(AEBlocks.SKY_STONE_BLOCK.asItem())
                .outputItems(AEItems.SKY_DUST.asItem())
                .EUt(VA[LV])
                .duration(20 * 20)
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("sky_dust_separation"))
                .inputItems(AEItems.SKY_DUST.asItem())
                .chancedOutput(dust, CertusQuartz, 700, 0)
                .chancedOutput(dust, FluixCrystal, 700, 0)
                .chancedOutput(dust, Tantalite, 700, 0)
                .chancedOutput(dust, Iron, 2500, 0)
                .EUt(VA[LV])
                .duration(20 * 15)
                .save(provider);
        ARC_FURNACE_RECIPES.recipeBuilder(GTTCore.id("certus_quartz_charging"))
                .inputItems(gem, CertusQuartz)
                .outputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .EUt(VA[LV])
                .duration(20 * 15)
                .save(provider);

    }
}
