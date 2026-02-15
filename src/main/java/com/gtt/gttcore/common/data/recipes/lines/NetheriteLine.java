package com.gtt.gttcore.common.data.recipes.lines;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.common.data.GCYMRecipeTypes.ALLOY_BLAST_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.minecraft.world.item.Items.NETHERITE_SCRAP;

public class NetheriteLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("netherite_scrap"))
                .EUt(VHA[EV])
                .duration(100)
                .inputFluids(Lava.getFluid(200))
                .inputItems(GTTItems.NETHERITE_SCRAP_SEED)
                .outputItems(Items.NETHERITE_SCRAP)
                .inputItems(dust, Tungsten, 1)
                .save(provider);
        FORGE_HAMMER_RECIPES.recipeBuilder(GTTCore.id("netherite_scrap_seed"))
                .EUt(VHA[HV])
                .duration(20)
                .inputItems(Items.NETHERITE_SCRAP)
                .outputItems(GTTItems.NETHERITE_SCRAP_SEED)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder("gold_dust_into_netherite")
                .duration(100).EUt(VHA[HV])
                .inputItems(dust, Gold)
                .inputItems(NETHERITE_SCRAP)
                .outputItems(ingot, Netherite)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder("gold_ingot_into_netherite")
                .duration(100).EUt(VHA[HV])
                .inputItems(ingot, Gold)
                .inputItems(NETHERITE_SCRAP)
                .outputItems(ingot, Netherite)
                .save(provider);
        ALLOY_BLAST_RECIPES.recipeBuilder(GTTCore.id("netherite"))
                .inputItems(TagPrefix.dust, Gold)
                .inputItems(NETHERITE_SCRAP)
                .circuitMeta(12)
                .outputFluids(Netherite.getFluid(FluidStorageKeys.MOLTEN, GTValues.L * 2))
                .duration(2000)
                .EUt(GTValues.VHA[HV])
                .blastFurnaceTemp(3000)
                .save(provider);
        VACUUM_RECIPES.recipeBuilder("netherite")
                .inputFluids(Netherite.getFluid(FluidStorageKeys.MOLTEN, L))
                .duration(540)
                .notConsumable(GTItems.SHAPE_MOLD_INGOT.asStack())
                .outputItems(TagPrefix.ingot, Netherite)
                .save(provider);
    }
}
