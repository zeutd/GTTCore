package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTMaterials;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.ULV;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

@SuppressWarnings("deprecation")
public class GTTRecipeTypes {
    public static List<GTRecipeBuilder> toReRegisterCreatePressing;
    public static List<GTRecipeBuilder> toReRegisterCreateMixing;
    public static List<GTRecipeBuilder> toReRegisterCreateMilling;
    //public static List<GTRecipeBuilder> toReRegisterCreate;
    public static void init(){
        CHEMICAL_RECIPES.onRecipeBuild((recipeBuilder, provider) -> {
            CHEMICAL_PLANT_RECIPES
                    .copyFrom(recipeBuilder)
                    .duration(recipeBuilder.duration / 4)
                    .save(provider);
            LARGE_CHEMICAL_RECIPES
                    .copyFrom(recipeBuilder)
                    .save(provider);
        });
        LARGE_CHEMICAL_RECIPES.onRecipeBuild((recipeBuilder, provider) ->
                CHEMICAL_PLANT_RECIPES
                        .copyFrom(recipeBuilder)
                        .save(provider)
        );
        ROCK_BREAKER_RECIPES.onRecipeBuild((recipeBuilder, provider) ->
                LARGE_ROCK_BREAKER_RECIPES
                        .copyFrom(recipeBuilder)
                        .save(provider)
        );
        PLASMA_GENERATOR_FUELS.onRecipeBuild((recipeBuilder, provider) -> {
            int eu = (int) (recipeBuilder.duration * GTValues.V[GTValues.EV]);
            PLASMA_HEAT_EXCHANGER_RECIPES.recipeBuilder(recipeBuilder.id)
                    .inputFluids((FluidIngredient) recipeBuilder.input.get(GTRecipeCapabilities.FLUID).get(0).content)
                    .inputFluids(GTMaterials.DistilledWater.getFluid(eu / 100))
                    .outputFluids(GTTMaterials.SupercriticalSteam.getFluid(eu / 100 * 200))
                    .outputFluids((FluidIngredient) recipeBuilder.output.get(GTRecipeCapabilities.FLUID).get(0).content)
                    .save(provider);
        });
    }
    public static void gatherData(){
        toReRegisterCreatePressing = new ArrayList<>();
        toReRegisterCreateMixing = new ArrayList<>();
        toReRegisterCreateMilling = new ArrayList<>();
        FORGE_HAMMER_RECIPES.onRecipeBuild((recipeBuilder, provider) -> {
            if (recipeBuilder.EUt().voltage() > GTValues.V[ULV]) return;
            if (toReRegisterCreatePressing.stream().noneMatch(rb -> rb.id.getPath().equals(recipeBuilder.id.getPath())))toReRegisterCreatePressing.add(recipeBuilder);
        });
        MIXER_RECIPES.onRecipeBuild((recipeBuilder, provider) -> {
            if (recipeBuilder.EUt().voltage() > GTValues.V[ULV]) return;
            if (toReRegisterCreateMixing.stream().noneMatch(rb -> rb.id.getPath().equals(recipeBuilder.id.getPath())))toReRegisterCreateMixing.add(recipeBuilder);
        });
        MACERATOR_RECIPES.onRecipeBuild((recipeBuilder, provider) -> {
            if (recipeBuilder.EUt().voltage() > GTValues.V[ULV]) return;
            if (toReRegisterCreateMilling.stream().noneMatch(rb -> rb.id.getPath().equals(recipeBuilder.id.getPath())))toReRegisterCreateMilling.add(recipeBuilder);
        });
    }
    public static GTRecipeType register(String name, String group, RecipeType<?>... proxyRecipes) {
        var recipeType = new GTRecipeType(GTTCore.id(name), group, proxyRecipes);
        GTRegistries.register(BuiltInRegistries.RECIPE_TYPE, recipeType.registryName, recipeType);
        GTRegistries.register(BuiltInRegistries.RECIPE_SERIALIZER, recipeType.registryName, new GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(recipeType.registryName, recipeType);
        return recipeType;
    }
    public final static GTRecipeType FISSION_RECIPES = register("fission_reactor", GENERATOR).setMaxIOSize(1, 1, 1, 1)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BOILER);
    public final static GTRecipeType ROCKET_ASSEMBLER_RECIPES = register("rocket_assembler", MULTIBLOCK).setMaxIOSize(9, 1, 0, 0)
            .setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ASSEMBLER);
    public final static GTRecipeType GREENHOUSE_RECIPES = register("greenhouse", MULTIBLOCK).setMaxIOSize(3, 6, 1, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CUT);
    public final static GTRecipeType SUPERCRITICAL_STEAM_TURBINE_FUELS = register("supercritical_steam_turbine", GENERATOR).setMaxIOSize(0, 0, 1, 1)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, false, GuiTextures.TURBINE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);
    public final static GTRecipeType PLASMA_HEAT_EXCHANGER_RECIPES  = register("plasma_heat_exchanger", GENERATOR).setMaxIOSize(0, 0, 2, 2)
            .setSlotOverlay(false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BOILER_FUEL.get(true), LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BOILER);
    public final static GTRecipeType PARTICLE_ACCELERATOR_RECIPES = register("particle_accelerator", MULTIBLOCK).setMaxIOSize(2, 1, 2, 2)
            .setEUIO(IO.IN)
            .setSlotOverlay(true, true, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, true, GuiTextures.MOLECULAR_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT);
    public final static GTRecipeType LARGE_ROCK_BREAKER_RECIPES = register("large_rock_breaker", ELECTRIC).setMaxIOSize(1, 4, 0, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.FIRE);
    public final static GTRecipeType CULTIVATOR_RECIPES = register("cultivator", ELECTRIC)
            .setMaxIOSize(1, 1, 1, 1)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.BREWER_OVERLAY)
            .setSlotOverlay(false, false, GuiTextures.BREWER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.CHEMICAL);
    public static final GTRecipeType CHEMICAL_PLANT_RECIPES = register("chemical_plant", MULTIBLOCK)
            .setMaxIOSize(9, 9, 9, 9).setEUIO(IO.IN)
            .setEUIO(IO.IN)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setSound(GTSoundEntries.CHEMICAL)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT);
    public static final GTRecipeType LASER_ENGRAVING_PLANT_RECIPES = register("laser_engraving_plant", MULTIBLOCK)
            .setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, true, GuiTextures.LENS_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.ELECTROLYZER);
    public static final GTRecipeType HIGH_ENERGY_LASER_PIPE_COOLANT = register("high_energy_laser_pipe_coolant", MULTIBLOCK)
            .setMaxIOSize(0, 0, 1, 0)
            .setSlotOverlay(false, true, false, GuiTextures.ATOMIC_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.REPLICATOR);
    public static final GTRecipeType EVAPORATION_RECIPES = register("evaporation", ELECTRIC)
            .setMaxIOSize(0, 1, 1, 6)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR);
}
