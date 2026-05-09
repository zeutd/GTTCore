package com.gtt.gttcore.integration.emi;

import com.gtt.gttcore.api.particle.ParticleType;
import com.gtt.gttcore.api.registry.GTTRegistries;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;

//@EmiEntrypoint
public class GTTEmiPlugin implements EmiPlugin {
//
//    public static EmiRecipeCategory GREGTECH_CREATE_MIXING_CATEGORY = new EmiRecipeCategory(
//            GTTCore.id("automatic_gregtech_mixing"),
//            new DoubleItemEmiRenderable(() -> AllBlocks.MECHANICAL_MIXER.get().asItem().getDefaultInstance(), () -> GTMachines.MIXER[0].asStack())
//
//    );
    @Override
    public void register(EmiRegistry registry) {
        for (ParticleType particleType : GTTRegistries.PARTICLE_TYPES) {
            registry.addEmiStack(new ParticleEmiStack(particleType, 1));
            //GTTCore.LOGGER.info("added {}", particleType);
        }
//        registry.addCategory(GREGTECH_CREATE_MIXING_CATEGORY);
//
//        RecipeUtil.consumeAllRecipes(recipe -> {
//            if(recipe instanceof GTRecipe gtRecipe
//                    && gtRecipe.recipeType == GTRecipeTypes.MIXER_RECIPES
//                    && gtRecipe.getInputEUt().voltage() <= V[ULV])
//                registry.addRecipe(new EmiRecipe() {
//                    @Override
//                    public EmiRecipeCategory getCategory() {
//                        return GREGTECH_CREATE_MIXING_CATEGORY;
//                    }
//
//                    @Override
//                    public @Nullable ResourceLocation getId() {
//                        return gtRecipe.id;
//                    }
//
//                    @Override
//                    public List<EmiIngredient> getInputs() {
//                        return List.of();
//                    }
//
//                    @Override
//                    public List<EmiStack> getOutputs() {
//                        return List.of();
//                    }
//
//                    @Override
//                    public int getDisplayWidth() {
//                        return 0;
//                    }
//
//                    @Override
//                    public int getDisplayHeight() {
//                        return 0;
//                    }
//
//                    @Override
//                    public void addWidgets(WidgetHolder widgets) {
//
//                    }
//                }RecipeUtil.convertGregTechToCreate(gtRecipe, BasinRecipe::new);
//        });
//        registry.addWorkstation(GREGTECH_CREATE_MIXING_CATEGORY, EmiIngredient.of(Ingredient.of(AllBlocks.MECHANICAL_MIXER)));
//        registry.addWorkstation(GREGTECH_CREATE_MIXING_CATEGORY, EmiIngredient.of(Ingredient.of(AllBlocks.BASIN)));
    }
}
