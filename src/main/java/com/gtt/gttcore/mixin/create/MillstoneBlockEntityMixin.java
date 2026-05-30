package com.gtt.gttcore.mixin.create;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.utils.DummyRecipeUtils;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.gregtechceu.gtceu.api.recipe.RecipeHelper.matchRecipe;
import static com.gregtechceu.gtceu.common.data.GTMachines.MACERATOR;

@Pseudo
@Mixin(value = MillstoneBlockEntity.class, remap = false)
public class MillstoneBlockEntityMixin {
    @Shadow
    public ItemStackHandler inputInv;

    @Shadow
    public ItemStackHandler outputInv;

    @Inject(method = "process", at = @At("HEAD"), cancellable = true)
    private void processMixin(CallbackInfo ci) {
        var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(ItemStack.EMPTY, inputInv.getStackInSlot(0)));
        var outputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.OUT, 9);
        ItemHelper.getNonEmptyStacks(outputInv).forEach(itemStack -> ItemHandlerHelper.insertItem(outputItemHandler.getStorage(), itemStack, false));
        IRecipeCapabilityHolder capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
                RecipeHandlerList.of(IO.IN, inputItemHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1)),
                RecipeHandlerList.of(IO.OUT, outputItemHandler)
        );
        var match = GTRecipeTypes.MACERATOR_RECIPES.searchRecipe(capabilityHolder, r -> matchRecipe(capabilityHolder, r).isSuccess());
        if (!match.hasNext()) return;
        GTRecipe recipe = RecipeHelper.trimRecipeOutputs(match.next(), MACERATOR[LV].getRecipeOutputLimits());
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
            for (int i = 0; i < inputInv.getSlots(); i++) {
                inputInv.setStackInSlot(i, ItemStack.EMPTY);
            }
            for (int i = 0; i < outputInv.getSlots(); i++) {
                outputInv.setStackInSlot(i, ItemStack.EMPTY);
            }
            outputItemHandler.getContents().forEach(o -> ItemHandlerHelper.insertItemStacked(outputInv, (ItemStack) o, false));
            var remaining = inputItemHandler.getContents();
            if (!remaining.isEmpty()) inputInv.setStackInSlot(0, ((ItemStack) remaining.get(0)));
        }
        ci.cancel();
    }

    @Inject(method = "canProcess", at = @At("HEAD"), cancellable = true)
    private void canProcessMixin(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
//        var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(ItemStack.EMPTY, inputInv.getStackInSlot(0)));
//        IRecipeCapabilityHolder capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
//                RecipeHandlerList.of(IO.IN, inputItemHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1))
//        );
//        var match = GTRecipeTypes.MACERATOR_RECIPES.searchRecipe(capabilityHolder, r -> handleRecipe(capabilityHolder, r, IO.IN, r.inputs, Collections.emptyMap(), false, true).isSuccess());
//        if (!match.hasNext()) {
//            cir.setReturnValue(false);
//            return;
//        }
        cir.setReturnValue(true);
    }
}
