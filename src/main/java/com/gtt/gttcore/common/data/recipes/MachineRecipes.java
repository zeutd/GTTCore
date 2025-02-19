package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;
import static net.minecraft.world.item.Items.*;

public class MachineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerFissionRecipes(provider);
        registerGreenhouseRecipes(provider);
        registerPlasmaHeatExchangerRecipes(provider);
    }

    private static void registerFissionRecipes(Consumer<FinishedRecipe> provider) {
        FISSION_RECIPES.recipeBuilder("fission_uranium")
                .inputItems(rod, Uranium238, 16)
                .outputItems(rod, DepletedUranium238, 16)
                .EUt(-V[ZPM])
                .duration(100)
                .save(provider);
        FISSION_RECIPES.recipeBuilder("fission_plutonium")
                .inputItems(rod, Plutonium239, 16)
                .outputItems(rod, DepletedPlutonium239, 16)
                .EUt(-V[ZPM] - V[IV])
                .duration(100)
                .save(provider);
        FISSION_RECIPES.recipeBuilder("fission_thorium")
                .inputItems(rod, Thorium, 16)
                .outputItems(rod, DepletedThorium, 16)
                .EUt(-V[ZPM])
                .duration(100)
                .save(provider);
    }

    private static void registerGreenhouseRecipes(Consumer<FinishedRecipe> provider){
        ////// Trees //////

        // Rubber
        greenhouse("rubber_sapling", RUBBER_SAPLING.asStack(), 1000, new ItemStack[]{RUBBER_LOG.asStack(64), STICKY_RESIN.asStack(8), RUBBER_SAPLING.asStack(4)}, false, provider);
        greenhouse("rubber_sapling_boosted", RUBBER_SAPLING.asStack(), 1000, new ItemStack[]{RUBBER_LOG.asStack(64), RUBBER_LOG.asStack(64), STICKY_RESIN.asStack(16), RUBBER_SAPLING.asStack(4)}, true, provider);

        // Oak
        greenhouse("oak_sapling", OAK_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{OAK_LOG.getDefaultInstance().copyWithCount(64), OAK_SAPLING.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("oak_sapling_boosted", OAK_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{OAK_LOG.getDefaultInstance().copyWithCount(64), OAK_LOG.getDefaultInstance().copyWithCount(64), OAK_SAPLING.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Dark Oak
        greenhouse("dark_oak_sapling", DARK_OAK_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{DARK_OAK_LOG.getDefaultInstance().copyWithCount(64), DARK_OAK_SAPLING.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("dark_oak_sapling_boosted", DARK_OAK_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{DARK_OAK_LOG.getDefaultInstance().copyWithCount(64), DARK_OAK_LOG.getDefaultInstance().copyWithCount(64), DARK_OAK_SAPLING.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Spruce
        greenhouse("spruce_sapling", SPRUCE_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{SPRUCE_LOG.getDefaultInstance().copyWithCount(64), SPRUCE_SAPLING.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("spruce_sapling_boosted", SPRUCE_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{SPRUCE_LOG.getDefaultInstance().copyWithCount(64), SPRUCE_LOG.getDefaultInstance().copyWithCount(64), SPRUCE_SAPLING.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Birch
        greenhouse("birch_sapling", BIRCH_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{BIRCH_LOG.getDefaultInstance().copyWithCount(64), BIRCH_SAPLING.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("birch_sapling_boosted", BIRCH_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{BIRCH_LOG.getDefaultInstance().copyWithCount(64), BIRCH_LOG.getDefaultInstance().copyWithCount(64), BIRCH_SAPLING.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Acacia
        greenhouse("acacia_sapling", ACACIA_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{ACACIA_LOG.getDefaultInstance().copyWithCount(64), ACACIA_SAPLING.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("acacia_sapling_boosted", ACACIA_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{ACACIA_LOG.getDefaultInstance().copyWithCount(64), ACACIA_LOG.getDefaultInstance().copyWithCount(64), ACACIA_SAPLING.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Jungle
        greenhouse("jungle_sapling", JUNGLE_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{JUNGLE_LOG.getDefaultInstance().copyWithCount(64), JUNGLE_SAPLING.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("jungle_sapling_boosted", JUNGLE_SAPLING.getDefaultInstance(), 1000, new ItemStack[]{JUNGLE_LOG.getDefaultInstance().copyWithCount(64), JUNGLE_LOG.getDefaultInstance().copyWithCount(64), JUNGLE_SAPLING.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Azalea
        greenhouse("azalea", AZALEA.getDefaultInstance(), 1000, new ItemStack[]{OAK_LOG.getDefaultInstance().copyWithCount(64), AZALEA.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("azalea_boosted", AZALEA.getDefaultInstance(), 1000, new ItemStack[]{OAK_LOG.getDefaultInstance().copyWithCount(64), AZALEA.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Flowering Azalea
        greenhouse("flowering_azalea", AZALEA.getDefaultInstance(), 1000, new ItemStack[]{OAK_LOG.getDefaultInstance().copyWithCount(64), FLOWERING_AZALEA.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("flowering_azalea_boosted", AZALEA.getDefaultInstance(), 1000, new ItemStack[]{OAK_LOG.getDefaultInstance().copyWithCount(64), OAK_LOG.getDefaultInstance().copyWithCount(64), FLOWERING_AZALEA.getDefaultInstance().copyWithCount(4)}, true, provider);

        // Mangrove
        greenhouse("mangrove_propagule", MANGROVE_PROPAGULE.getDefaultInstance(), 1000, new ItemStack[]{MANGROVE_LOG.getDefaultInstance().copyWithCount(64), MANGROVE_PROPAGULE.getDefaultInstance().copyWithCount(4)}, false, provider);
        greenhouse("mangrove_propagule_boosted", MANGROVE_PROPAGULE.getDefaultInstance(), 1000, new ItemStack[]{MANGROVE_LOG.getDefaultInstance().copyWithCount(64), MANGROVE_LOG.getDefaultInstance().copyWithCount(64), MANGROVE_PROPAGULE.getDefaultInstance().copyWithCount(4)}, true, provider);

        ////// Crops //////

        // Sugarcane
        greenhouse("sugar_cane", SUGAR_CANE.getDefaultInstance(), 1000, new ItemStack[]{SUGAR_CANE.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("sugar_cane_boosted", SUGAR_CANE.getDefaultInstance(), 1000, new ItemStack[]{SUGAR_CANE.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Kelp
        greenhouse("kelp", KELP.getDefaultInstance(), 2000, new ItemStack[]{KELP.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("kelp_boosted", KELP.getDefaultInstance(), 2000, new ItemStack[]{KELP.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Bamboo
        greenhouse("bamboo", BAMBOO.getDefaultInstance(), 1000, new ItemStack[]{BAMBOO.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("bamboo_boosted", BAMBOO.getDefaultInstance(), 1000, new ItemStack[]{BAMBOO.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Cactus
        greenhouse("cactus", CACTUS.getDefaultInstance(), 500, new ItemStack[]{CACTUS.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("cactus_boosted", CACTUS.getDefaultInstance(), 500, new ItemStack[]{CACTUS.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Wheat
        greenhouse("wheat", WHEAT_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{WHEAT.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("wheat_boosted", WHEAT_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{WHEAT.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Carrot
        greenhouse("carrot", CARROT.getDefaultInstance(), 1000, new ItemStack[]{CARROT.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("carrot_boosted", CARROT.getDefaultInstance(), 1000, new ItemStack[]{CARROT.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Potato
        greenhouse("potato", POTATO.getDefaultInstance(), 1000, new ItemStack[]{POTATO.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("potato_boosted", POTATO.getDefaultInstance(), 1000, new ItemStack[]{POTATO.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Beetroot
        greenhouse("beetroot", BEETROOT_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{BEETROOT.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("beetroot_boosted", BEETROOT_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{BEETROOT.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Mellon
        greenhouse("melon", MELON_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{MELON.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("melon_boosted", MELON_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{MELON.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Pumpkin
        greenhouse("pumpkin", PUMPKIN_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{PUMPKIN.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("pumpkin_boosted", PUMPKIN_SEEDS.getDefaultInstance(), 1000, new ItemStack[]{PUMPKIN.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Nether Wart
        greenhouse("nether_wart", NETHER_WART.getDefaultInstance(), 1000, new ItemStack[]{NETHER_WART.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("nether_wart_boosted", NETHER_WART.getDefaultInstance(), 1000, new ItemStack[]{NETHER_WART.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Red Mushroom
        greenhouse("red_mushroom", RED_MUSHROOM.getDefaultInstance(), 1000, new ItemStack[]{RED_MUSHROOM.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("red_mushroom_boosted", RED_MUSHROOM.getDefaultInstance(), 1000, new ItemStack[]{RED_MUSHROOM.getDefaultInstance().copyWithCount(48)}, true, provider);

        // Brown Mushroom
        greenhouse("brown_mushroom", BROWN_MUSHROOM.getDefaultInstance(), 1000, new ItemStack[]{BROWN_MUSHROOM.getDefaultInstance().copyWithCount(24)}, false, provider);
        greenhouse("brown_mushroom_boosted", BROWN_MUSHROOM.getDefaultInstance(), 1000, new ItemStack[]{BROWN_MUSHROOM.getDefaultInstance().copyWithCount(48)}, true, provider);
    }
    private static void registerPlasmaHeatExchangerRecipes(Consumer<FinishedRecipe> provider){
        GTTCore.LOGGER.info("114514");
        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            for (Material material : registry.getAllMaterials()) {
                if(material.hasFluid() && material.getProperty(PropertyKey.FLUID).get(FluidStorageKeys.PLASMA) != null){
                    PLASMA_HEAT_EXCHANGER_RECIPES.recipeBuilder(material.getName() + "_heat_exchange")
                            .inputFluids(material.getFluid(FluidStorageKeys.PLASMA, 1000), DistilledWater.getFluid(10000))
                            .outputFluids(material.getFluid(1000), SupercriticalSteam.getFluid(1000000))
                            .duration(10).save(provider);
                }
            }
        }
    }
    private static void greenhouse(String id, ItemStack input, int fluid, ItemStack[] output, boolean boosted, Consumer<FinishedRecipe> provider) {
        if (boosted) {
            GREENHOUSE_RECIPES.recipeBuilder(id)
                    .circuitMeta(2)
                    .notConsumable(input)
                    .inputItems(FERTILIZER)
                    .inputFluids(Water.getFluid(fluid))
                    .outputItems(output)
                    .duration(320)
                    .EUt(32)
                    .save(provider);
        } else {
            GREENHOUSE_RECIPES.recipeBuilder(id)
                    .circuitMeta(1)
                    .notConsumable(input)
                    .inputFluids(Water.getFluid(fluid))
                    .outputItems(output)
                    .duration(640)
                    .EUt(32)
                    .save(provider);
        }
    }
}