package com.gtt.gttcore;

import com.gtt.gttcore.common.data.recipes.GTTRecipes;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.bindings.event.WorldgenEvents;
import dev.latvian.mods.kubejs.level.gen.RemoveWorldgenEventJS;
import dev.latvian.mods.kubejs.recipe.RecipesEventJS;
import dev.latvian.mods.kubejs.recipe.filter.ConstantFilter;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.Objects;

public class GTTKubeJSPlugin extends KubeJSPlugin {
    @Override
    public void initStartup() {
        super.initStartup();
        GTTCore.LOGGER.info("GTT KubeJS Plugin Startup Init");
        WorldgenEvents.REMOVE.listenJava(ScriptType.STARTUP, null, e -> {
            GTTCore.LOGGER.info("Removing Features");
            RemoveWorldgenEventJS removeWorldgenEvent = (RemoveWorldgenEventJS) e;
            removeWorldgenEvent.removeFeatureById(GenerationStep.Decoration.UNDERGROUND_ORES,
                    new ResourceLocation[]{
                            new ResourceLocation("ad_astra:moon_iron_ore"),
                            new ResourceLocation("ad_astra:moon_desh_ore"),
                            new ResourceLocation("ad_astra:moon_ice_shard_ore"),

                            new ResourceLocation("ad_astra:mars_diamond_ore"),
                            new ResourceLocation("ad_astra:mars_ice_shard_ore"),
                            new ResourceLocation("ad_astra:mars_iron_ore"),
                            new ResourceLocation("ad_astra:mars_ostrum_ore"),

                            new ResourceLocation("ad_astra:venus_calorite_ore"),
                            new ResourceLocation("ad_astra:venus_coal_ore"),
                            new ResourceLocation("ad_astra:venus_diamond_ore"),
                            new ResourceLocation("ad_astra:venus_gold_ore"),

                            new ResourceLocation("ad_astra:mercury_iron_ore"),

                            new ResourceLocation("ad_astra:glacio_coal_ore"),
                            new ResourceLocation("ad_astra:glacio_copper_ore"),
                            new ResourceLocation("ad_astra:glacio_ice_shard_ore"),
                            new ResourceLocation("ad_astra:glacio_iron_ore"),
                            new ResourceLocation("ad_astra:glacio_lapis_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_coal_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_copper_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_iron_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_lapis_ore"),
                            new ResourceLocation("create:zinc_ore")
                    }
            );
            return null;
        });
    }

    @Override
    public void onServerReload() {
        super.onServerReload();
        ServerEvents.RECIPES.listenJava(ScriptType.SERVER, null, e -> {
            GTTRecipes.replaceOutputMap.forEach((k, v) -> {
                RecipesEventJS.instance.replaceOutput(r -> !Objects.equals(r.kjs$getMod(), GTTCore.MOD_ID), k, v);
            });
            GTTRecipes.replaceInputMap.forEach((k, v) -> {
                RecipesEventJS.instance.replaceInput(r -> !Objects.equals(r.kjs$getMod(), GTTCore.MOD_ID), k, v);
            });
            GTTRecipes.remove(filter -> {
                RecipesEventJS.instance.remove(filter);
            });
            RecipesEventJS.instance.forEachRecipe(new ConstantFilter(true), recipe -> {
                try {
                    int newDuration = ((Integer)recipe.get("duration"));
                    recipe.set("duration", Math.max(newDuration/2, 1));
                } catch (Exception err) {
                    //LOGGER.info(recipe.id + " has no duration field, skipped.");
                }
            });
            return null;
        });
    }
}
