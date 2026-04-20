package com.gtt.gttcore.integration.kjs;

import com.google.gson.JsonElement;
import com.gtt.gttcore.api.particle.ParticleStack;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;

public class GTTRecipeComponents {
    public static final RecipeComponent<ParticleStack> PARTICLE_STACK = new RecipeComponent<>() {

        @Override
        public String componentType() {
            return "particle_ingredient";
        }

        @Override
        public Class<?> componentClass() {
            return ParticleStack.class;
        }

        @Override
        public JsonElement write(RecipeJS recipe, ParticleStack value) {
            return value.toJson();
        }

        @Override
        public ParticleStack read(RecipeJS recipe, Object from) {
            return ParticleStack.of(from);
        }
    };
}
