package com.gtt.gttcore.mixin.create;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.utils.DummyRecipeUtils;
import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.gregtechceu.gtceu.api.recipe.RecipeHelper.matchRecipe;

@Pseudo
@Mixin(value = CrushingWheelControllerBlockEntity.class, remap = false)
public class CrushingWheelControllerBlockEntityMixin {
    @Shadow public ProcessingInventory inventory;

    @Inject(method = "applyRecipe", at = @At("HEAD"), cancellable = true)
    private void applyRecipeMixin(CallbackInfo ci) {
        var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(ItemStack.EMPTY, inventory.getStackInSlot(0)));
        var outputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.OUT, 24);
        var allItems = ItemHelper.getNonEmptyStacks(inventory);
        allItems.subList(1, allItems.size()).forEach(itemStack -> ItemHandlerHelper.insertItem(outputItemHandler.getStorage(), itemStack, false));
        IRecipeCapabilityHolder capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
                RecipeHandlerList.of(IO.IN, inputItemHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1)),
                RecipeHandlerList.of(IO.OUT, outputItemHandler)
        );
        var match = GTRecipeTypes.MACERATOR_RECIPES.searchRecipe(capabilityHolder, r -> matchRecipe(capabilityHolder, r).isSuccess());
        if (!match.hasNext()) return;
        GTRecipe recipe = match.next();
        if (
                RecipeHelper.handleRecipeIO(
                        capabilityHolder,
                        recipe,
                        IO.IN,
                        Collections.emptyMap()
                ).isSuccess() &&
                RecipeHelper.handleRecipeIO(
                        capabilityHolder,
                        recipe,
                        IO.OUT,
                        Collections.emptyMap()
                ).isSuccess()) {
            inventory.clear();
            var remaining = inputItemHandler.getContents();
            if(!remaining.isEmpty()) inventory.setStackInSlot(0, ((ItemStack) inputItemHandler.getContents().get(0)));
            var outputContent = outputItemHandler.getContents();
            for (int i = 0; i < outputContent.size(); i++) {
                inventory.setStackInSlot(i + 1, (ItemStack) outputContent.get(i));
            }
        }
        ci.cancel();
    }
}
