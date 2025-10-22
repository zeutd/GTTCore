package com.gtt.gttcore.mixin;

import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.recipes.GTTRecipes;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.recipe.RecipesEventJS;
import dev.latvian.mods.kubejs.recipe.filter.ConstantFilter;
import dev.latvian.mods.kubejs.recipe.filter.IDFilter;
import dev.latvian.mods.kubejs.recipe.filter.RecipeFilter;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(dev.latvian.mods.kubejs.server.ServerScriptManager.class)
public class ServerScriptManagerMixin {
    @Inject(method = "wrapResourceManager", at = @At(value = "INVOKE", target = "Ldev/latvian/mods/kubejs/event/EventHandler;hasListeners()Z"), remap = false)
    private void wrapResourceManagerMixin(CloseableResourceManager original, CallbackInfoReturnable<MultiPackResourceManager> cir) {
        ServerEvents.RECIPES.listenJava(ScriptType.SERVER, null, e -> {
            GTTRecipes.replaceOutputMap.forEach((k, v) -> {
                RecipesEventJS.instance.replaceOutput(r -> !Objects.equals(r.kjs$getMod(), "gttcore"), k, v);
            });
            GTTRecipes.replaceInputMap.forEach((k, v) -> {
                RecipesEventJS.instance.replaceInput(r -> !Objects.equals(r.kjs$getMod(), "gttcore"), k, v);
            });
            GTTRecipes.remove(filter -> {
                RecipesEventJS.instance.remove(filter);
            });
            return null;
        });
    }
}
