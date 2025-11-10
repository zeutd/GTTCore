package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.CABLE;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.PISTON;
import static com.gtt.gttcore.common.data.GTTItems.*;
import static com.gtt.gttcore.common.data.GTTMachines.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.Items.GLASS;

public class MachineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerFissionRecipes(provider);
        registerGreenhouseRecipes(provider);
        registerCultivatorRecipes(provider);
        registerHighEnergyLaserRecipes(provider);
        registerMachineRecipes(provider);
    }
    private static void registerMachineRecipes(Consumer<FinishedRecipe> provider){
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("steam_turbine_ulv"), ULV_STEAM_TURBINE.asStack(),
                "PCP", "RMR", "EWE", 'M', GTMachines.HULL[GTValues.LV].asStack(), 'E', ELECTRIC_MOTOR_ULV, 'R',
                new MaterialEntry(TagPrefix.rotor, Lead), 'C', CustomTags.ULV_CIRCUITS, 'W',
                new MaterialEntry(TagPrefix.cableGtSingle, RedAlloy), 'P',
                new MaterialEntry(TagPrefix.pipeNormalFluid, Lead));





        registerMachineRecipe(provider, ULV_ALLOY_SMELTER,
                "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W',
                CABLE, 'C', COIL_HEATING_DOUBLE
        );
        registerMachineRecipe(provider, ULV_BENDER, "PBP", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C',
                CIRCUIT, 'W', CABLE, 'B', PLATE);
        registerMachineRecipe(provider, ULV_COMPRESSOR, " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON, 'C',
                CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, ULV_CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR,
                'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', SAWBLADE);
        registerMachineRecipe(provider, ULV_ELECTRIC_FURNACE, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W',
                CABLE, 'C', COIL_HEATING);
        registerMachineRecipe(provider, ULV_LATHE, "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C',
                CIRCUIT, 'W', CABLE, 'D', GRINDER);
        registerMachineRecipe(provider, ULV_EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP,
                'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_MACERATOR, "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR, 'P', PISTON,
                'C', CIRCUIT, 'W', CABLE, 'G', GRINDER);
        registerMachineRecipe(provider, ULV_WIREMILL, "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT,
                'W', CABLE);
        registerMachineRecipe(provider, ULV_CENTRIFUGE, "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT,
                'W', CABLE);
        registerMachineRecipe(provider, ULV_ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR,
                'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E',
                MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', PIPE_REACTOR);
        registerMachineRecipe(provider, ULV_BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B',
                ROD_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT,
                'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B',
                ROD_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C',
                CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', Tags.Items.CHESTS_WOODEN);
        registerMachineRecipe(provider, ULV_CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V',
                CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_POLARIZER, "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S',
                ROD_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'W', CABLE);
        registerMachineRecipe(provider, ULV_ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S',
                ROD_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, ULV_AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT,
                'I', PLATE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C',
                CIRCUIT, 'G', GLASS);
        registerMachineRecipe(provider, ULV_FORMING_PRESS, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C',
                CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, ULV_FORGE_HAMMER, "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON, 'C',
                CIRCUIT, 'W', CABLE, 'A', Blocks.ANVIL);
        registerMachineRecipe(provider, ULV_FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O',
                COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(provider, ULV_SIFTER, "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON, 'F',
                GTItems.ITEM_FILTER, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, ULV_ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W',
                CABLE, 'I', WIRE_ELECTRIC, 'G', GLASS);
        registerMachineRecipe(provider, ULV_ASSEMBLER, "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A',
                ROBOT_ARM, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, ULV_GAS_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F',
                GTItems.FLUID_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);


        registerMachineRecipe(provider, CULTIVATOR, "CEC", "PHP", "WCW", 'C', CIRCUIT, 'E', PETRI_DISH, 'P', PUMP, 'H', HULL, 'W', CABLE);








        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("electric_piston_ulv"), ELECTRIC_PISTON_ULV.asStack(), "PPP",
                "CRR", "CMG", 'P', new MaterialEntry(plate, WroughtIron), 'C', new MaterialEntry(cableGtSingle, Tin),
                'R', new MaterialEntry(rod, WroughtIron), 'G', new MaterialEntry(gearSmall, WroughtIron), 'M',
                ELECTRIC_MOTOR_ULV.asStack());
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("electric_piston_ulv"))
                .inputItems(rod, WroughtIron, 2)
                .inputItems(cableGtSingle, RedAlloy, 2)
                .inputItems(plate, WroughtIron, 3)
                .inputItems(gearSmall, WroughtIron)
                .inputItems(ELECTRIC_MOTOR_ULV)
                .outputItems(ELECTRIC_PISTON_ULV)
                .duration(100).EUt(VA[LV]).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "electric_motor_ulv", ELECTRIC_MOTOR_ULV.asStack(),
                "CWR", "WMW", "RWC", 'C', new MaterialEntry(cableGtSingle, Tin), 'W',
                new MaterialEntry(wireGtSingle, Tin), 'R', new MaterialEntry(rod, Iron), 'M',
                new MaterialEntry(rod, IronMagnetic));
        ASSEMBLER_RECIPES.recipeBuilder("electric_motor_ulv")
                .inputItems(cableGtSingle, Tin, 2)
                .inputItems(rod, Iron, 2)
                .inputItems(rod, IronMagnetic)
                .inputItems(wireGtSingle, Tin, 4)
                .outputItems(ELECTRIC_MOTOR_ULV)
                .duration(100).EUt(VA[LV]).save(provider);

        final Map<String, Material> rubberMaterials = new Object2ObjectOpenHashMap<>();
        rubberMaterials.put("rubber", Rubber);
        rubberMaterials.put("silicone_rubber", SiliconeRubber);
        rubberMaterials.put("styrene_butadiene_rubber", StyreneButadieneRubber);

        for (Map.Entry<String, Material> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getValue();
            String name = materialEntry.getKey();
            VanillaRecipeHelper.addShapedRecipe(provider, material.equals(Rubber),
                    String.format("conveyor_module_ulv_%s", name), CONVEYOR_MODULE_ULV.asStack(), "RRR", "MCM", "RRR",
                    'R', new MaterialEntry(plate, material), 'C', new MaterialEntry(cableGtSingle, RedAlloy), 'M',
                    ELECTRIC_MOTOR_ULV.asStack());
            ASSEMBLER_RECIPES.recipeBuilder("conveyor_module_ulv_" + name)
                    .inputItems(cableGtSingle, RedAlloy)
                    .inputItems(ELECTRIC_MOTOR_ULV, 2)
                    .inputFluids(materialEntry.getValue().getFluid(L * 6))
                    .circuitMeta(1)
                    .outputItems(CONVEYOR_MODULE_ULV)
                    .duration(100).EUt(VA[LV]).save(provider);
            VanillaRecipeHelper.addShapedRecipe(provider, material.equals(Rubber),
                    String.format("electric_pump_ulv_%s", name), ELECTRIC_PUMP_ULV.asStack(), "SXR", "dPw", "RMC", 'S',
                    new MaterialEntry(screw, Tin), 'X', new MaterialEntry(rotor, Tin), 'P',
                    new MaterialEntry(pipeNormalFluid, Bronze), 'R', new MaterialEntry(ring, material), 'C',
                    new MaterialEntry(cableGtSingle, RedAlloy), 'M', ELECTRIC_MOTOR_ULV.asStack());
            ASSEMBLER_RECIPES.recipeBuilder("electric_pump_ulv_" + name)
                    .inputItems(cableGtSingle, RedAlloy)
                    .inputItems(pipeNormalFluid, Bronze)
                    .inputItems(screw, Tin)
                    .inputItems(rotor, Tin)
                    .inputItems(ring, materialEntry.getValue(), 2)
                    .inputItems(ELECTRIC_MOTOR_ULV)
                    .outputItems(ELECTRIC_PUMP_ULV)
                    .duration(100).EUt(VA[LV]).save(provider);
        }
        ASSEMBLER_RECIPES.recipeBuilder("fluid_regulator_ulv")
                .inputItems(ELECTRIC_PUMP_ULV)
                .inputItems(CustomTags.ULV_CIRCUITS, 2)
                .circuitMeta(1)
                .outputItems(FLUID_REGULATOR_ULV)
                .EUt(VA[LV])
                .duration(400)
                .save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "robot_arm_ulv", ROBOT_ARM_ULV.asStack(), "CCC", "MRM", "PXR",
                'C', new MaterialEntry(cableGtSingle, RedAlloy), 'R', new MaterialEntry(rod, WroughtIron), 'M',
                ELECTRIC_MOTOR_ULV.asStack(), 'P', ELECTRIC_PISTON_ULV.asStack(), 'X', CustomTags.ULV_CIRCUITS);
        ASSEMBLER_RECIPES.recipeBuilder("robot_arm_ulv")
                .inputItems(cableGtSingle, RedAlloy, 3)
                .inputItems(rod, WroughtIron, 2)
                .inputItems(ELECTRIC_MOTOR_ULV, 2)
                .inputItems(ELECTRIC_PISTON_ULV)
                .inputItems(CustomTags.ULV_CIRCUITS)
                .outputItems(ROBOT_ARM_ULV)
                .duration(100).EUt(VA[LV]).save(provider);
    }

    public static void registerMachineRecipe(Consumer<FinishedRecipe> provider, boolean setMaterialInfoData,
                                             MachineDefinition machine, Object... recipe) {

        // Needed to skip certain tiers if not enabled.
        // Leaves UHV+ machine recipes to be implemented by addons.
        if (machine != null) {
            Object[] prepRecipe = prepareRecipe(machine.getTier(), Arrays.copyOf(recipe, recipe.length));
            if (prepRecipe == null) {
                return;
            }
            VanillaRecipeHelper.addShapedRecipe(provider, setMaterialInfoData, machine.getName(), machine.asStack(),
                    prepRecipe);
        }
    }

    public static void registerMachineRecipe(Consumer<FinishedRecipe> provider, MachineDefinition[] machines,
                                             Object... recipe) {
        for (MachineDefinition machine : machines) {
            registerMachineRecipe(provider, true, machine, recipe);
        }
    }

    public static void registerMachineRecipe(Consumer<FinishedRecipe> provider, MachineDefinition machine,
                                             Object... recipe) {
        registerMachineRecipe(provider, true, machine, recipe);
    }

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        for (int i = 3; i < recipe.length; i++) {
            if (recipe[i] instanceof CraftingComponent) {
                Object component = ((CraftingComponent) recipe[i]).get(tier);
                recipe[i] = component;
            } else if (recipe[i] instanceof Item item) {
                recipe[i] = new ItemStack(item);
            } else if (recipe[i] instanceof Block block) {
                recipe[i] = new ItemStack(block);
            } else if (recipe[i] instanceof ItemProviderEntry<?> itemEntry) {
                recipe[i] = itemEntry.asStack();
            }
        }
        return recipe;
    }

    private static void registerCultivatorRecipes(Consumer<FinishedRecipe> provider){
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("fermented_biomass"))
                .inputFluids(Biomass.getFluid(100))
                .outputFluids(FermentedBiomass.getFluid(100))
                .duration(75).EUt(2).save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("cultivate_products"))
                .inputFluids(FermentedBiomass.getFluid(100))
                .outputFluids(CultivateProducts.getFluid(100))
                .duration(150)
                .EUt(VH[LV])
                .circuitMeta(1)
                .save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("wetware_processor_awakening"))
                .inputItems(UNAWAKENED_WETWARE_PROCESSOR_LuV)
                .inputFluids(SterileGrowthMedium.getFluid(500))
                .outputItems(WETWARE_PROCESSOR_LuV)
                .duration(400)
                .EUt(VA[EV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("wetware_processor_assembly_awakening"))
                .inputItems(UNAWAKENED_WETWARE_PROCESSOR_ASSEMBLY_ZPM)
                .inputFluids(SterileGrowthMedium.getFluid(500))
                .outputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
                .duration(400)
                .EUt(VA[EV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("wetware_super_computer_awakening"))
                .inputItems(UNAWAKENED_WETWARE_SUPER_COMPUTER_UV)
                .inputFluids(SterileGrowthMedium.getFluid(500))
                .outputItems(WETWARE_SUPER_COMPUTER_UV)
                .duration(400)
                .EUt(VA[EV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("wetware_mainframe_awakening"))
                .inputItems(UNAWAKENED_WETWARE_MAINFRAME_UHV)
                .inputFluids(SterileGrowthMedium.getFluid(500))
                .outputItems(WETWARE_MAINFRAME_UHV)
                .duration(400)
                .EUt(VA[EV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("stem_cells")).EUt(VA[LuV]).duration(300)
                .inputItems(dust, Osmiridium)
                .inputFluids(Bacteria.getFluid(500))
                .inputFluids(SterileGrowthMedium.getFluid(500))
                .outputItems(STEM_CELLS, 32)
                .outputFluids(BacterialSludge.getFluid(500))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);
        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("enriched_bacterial_sludge_from_u238")).EUt(4).duration(64)
                .inputItems(dust, Uranium238)
                .inputFluids(BacterialSludge.getFluid(1000))
                .outputFluids(EnrichedBacterialSludge.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("enriched_bacterial_sludge_from_u235")).EUt(4).duration(64)
                .inputItems(dustTiny, Uranium235)
                .inputFluids(BacterialSludge.getFluid(1000))
                .outputFluids(EnrichedBacterialSludge.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        CULTIVATOR_RECIPES.recipeBuilder(GTTCore.id("enriched_bacterial_sludge_from_naquadria")).EUt(4).duration(64)
                .inputItems(dustTiny, Naquadria)
                .inputFluids(BacterialSludge.getFluid(1000))
                .outputFluids(EnrichedBacterialSludge.getFluid(2000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);
    }

    private static void registerFissionRecipes(Consumer<FinishedRecipe> provider) {
        FISSION_RECIPES.recipeBuilder(GTTCore.id("fission_uranium"))
                .inputItems(rod, Uranium238, 16)
                .outputItems(rod, DepletedUranium238, 16)
                .EUt(-VA[ZPM])
                .duration(100)
                .save(provider);
        FISSION_RECIPES.recipeBuilder(GTTCore.id("fission_plutonium"))
                .inputItems(rod, Plutonium239, 16)
                .outputItems(rod, DepletedPlutonium239, 16)
                .EUt(-VA[ZPM] - VA[IV])
                .duration(100)
                .save(provider);
        FISSION_RECIPES.recipeBuilder(GTTCore.id("fission_thorium"))
                .inputItems(rod, Thorium, 16)
                .outputItems(rod, DepletedThorium, 16)
                .EUt(-VA[ZPM])
                .duration(100)
                .save(provider);
    }

    private static void registerHighEnergyLaserRecipes(Consumer<FinishedRecipe> provider){
        PARTICLE_ACCELERATOR_RECIPES.recipeBuilder(GTTCore.id("test"))
                .EUt(VA[ULV])
                .inputItems(DIRT)
                .duration(10)
                .outputItems(DIRT_PATH)
                .output(HighEnergyLaserRecipeCapability.CAP, 1000)
                .save(provider);
        LASER_ENGRAVING_PLANT_RECIPES.recipeBuilder(GTTCore.id("test"))
                .EUt(VA[ULV])
                .inputItems(DIRT_PATH)
                .duration(10)
                .outputItems(DIRT)
                .input(HighEnergyLaserRecipeCapability.CAP, 1000)
                .save(provider);
        HIGH_ENERGY_LASER_PIPE_COOLANT.recipeBuilder(GTTCore.id("high_energy_laser_pipe_coolant"))
                .duration(400)
                .inputFluids(PCBCoolant.getFluid(1000))
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
    private static void greenhouse(String id, ItemStack input, int fluid, ItemStack[] output, boolean boosted, Consumer<FinishedRecipe> provider) {
        if (boosted) {
            GREENHOUSE_RECIPES.recipeBuilder(GTTCore.id(id))
                    .circuitMeta(2)
                    .notConsumable(input)
                    .inputItems(FERTILIZER)
                    .inputFluids(Water.getFluid(fluid))
                    .outputItems(output)
                    .duration(320)
                    .EUt(32)
                    .save(provider);
        } else {
            GREENHOUSE_RECIPES.recipeBuilder(GTTCore.id(id))
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