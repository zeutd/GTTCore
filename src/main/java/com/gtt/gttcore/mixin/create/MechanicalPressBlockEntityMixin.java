package com.gtt.gttcore.mixin.create;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.utils.DummyRecipeUtils;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.gregtechceu.gtceu.api.recipe.RecipeHelper.matchRecipe;

@Pseudo
@Mixin(value = MechanicalPressBlockEntity.class, remap = false)
public class MechanicalPressBlockEntityMixin {
    @Shadow
    public PressingBehaviour pressingBehaviour;

    @Inject(method = "tryProcessInWorld", at = @At("HEAD"), cancellable = true)
    public void tryProcessInWorldMixin(ItemEntity itemEntity, boolean simulate, CallbackInfoReturnable<Boolean> cir) {
        ItemStack item = itemEntity.getItem();
        BlockEntity self = (BlockEntity) (Object) this;
        var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(ItemStack.EMPTY, item));
        var outputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.OUT, 9);
        IRecipeCapabilityHolder capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
                RecipeHandlerList.of(IO.IN, inputItemHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1)),
                RecipeHandlerList.of(IO.OUT, outputItemHandler)
        );
        var match = GTRecipeTypes.FORGE_HAMMER_RECIPES.searchRecipe(capabilityHolder, r -> matchRecipe(capabilityHolder, r).isSuccess());
        if (!match.hasNext()) return;
        GTRecipe recipe = match.next();
        if (!simulate &&
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
            pressingBehaviour.particleItems.add(item);
            Level level = Objects.requireNonNull(self.getLevel());
            ItemEntity created =
                    new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), (ItemStack) outputItemHandler.getContents().get(0));
            created.setDefaultPickUpDelay();
            created.setDeltaMovement(VecHelper.offsetRandomly(Vec3.ZERO, level.random, .05f));
            level.addFreshEntity(created);
            var remainder = inputItemHandler.getContents();
            if (remainder.isEmpty() || ((ItemStack) remainder.get(0)).isEmpty()) itemEntity.discard();
            else itemEntity.setItem((ItemStack) remainder.get(0));
        }
        cir.setReturnValue(true);
    }
    @Inject(method = "tryProcessOnBelt", at = @At("HEAD"), cancellable = true)
    public void tryProcessOnBeltMixin(TransportedItemStack input, List<ItemStack> outputList, boolean simulate, CallbackInfoReturnable<Boolean> cir) {
        ItemStack item = input.stack;
        var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(ItemStack.EMPTY, item));
        var outputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.OUT, 9);
        IRecipeCapabilityHolder capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
                RecipeHandlerList.of(IO.IN, inputItemHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1)),
                RecipeHandlerList.of(IO.OUT, outputItemHandler)
        );
        var match = GTRecipeTypes.FORGE_HAMMER_RECIPES.searchRecipe(capabilityHolder, r -> matchRecipe(capabilityHolder, r).isSuccess());
        if (!match.hasNext()) return;
        GTRecipe recipe = match.next();
        if (!simulate &&
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
            pressingBehaviour.particleItems.add(item);
            outputList.add((ItemStack) outputItemHandler.getContents().get(0));
        }
        cir.setReturnValue(true);
    }
}
