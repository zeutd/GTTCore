package com.gtt.gttcore.mixin.create;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.utils.DummyRecipeUtils;
import com.gtt.gttcore.util.DummyFluidHandler;
import com.gtt.gttcore.util.IgnoreCircuitRecipeHandler;
import com.gtt.gttcore.util.TypedStackingDummyItemHandler;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinOperatingBlockEntity;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.gregtechceu.gtceu.api.recipe.RecipeHelper.matchRecipe;

@Pseudo
@Mixin(value = BasinOperatingBlockEntity.class, remap = false)
public abstract class BasinOperatingBlockEntityMixin {
    @Shadow
    public abstract boolean continueWithPreviousRecipe();

    @Shadow
    protected abstract Optional<BasinBlockEntity> getBasin();

    @Shadow protected abstract List<Recipe<?>> getMatchingRecipes();

    @Shadow public abstract void startProcessingBasin();

    @Unique
    public NonNullList<ItemStack> gTTCore$getInputItemStacks() {
        return getBasin()
                .map(
                        basin -> {
                            var handler = basin.inputInventory;
                            NonNullList<ItemStack> list = NonNullList.create();
                            for (int i = 0; i < handler.getSlots(); i++) {
                                var stack = handler.getStackInSlot(i);
                                if (stack.isEmpty()) continue;
                                ItemHelper.addToList(stack, list);
                            }
                            return list;
                        }).orElse(NonNullList.of(ItemStack.EMPTY));
    }

    @Unique
    public NonNullList<FluidStack> gTTCore$getInputFluidStacks() {
        return getBasin()
                .map(
                        basin -> basin
                                .inputTank
                                .getCapability()
                                .map(handler -> {
                                    NonNullList<FluidStack> list = NonNullList.create();
                                    for (int i = 0; i < handler.getTanks(); i++) {
                                        var stack = handler.getFluidInTank(i);
                                        if (stack.isEmpty()) continue;
                                        list.add(stack);
                                    }
                                    return list;
                                }).orElse(NonNullList.of(FluidStack.EMPTY))
                ).orElse(NonNullList.of(FluidStack.EMPTY));
    }

    @Unique
    public NonNullList<ItemStack> gTTCore$getOutputItemStacks() {
        return getBasin()
                .map(
                        basin -> {
                            var handler = basin.getOutputInventory();
                            NonNullList<ItemStack> list = NonNullList.create();
                            for (int i = 0; i < handler.getSlots(); i++) {
                                var stack = handler.getStackInSlot(i);
                                if (stack.isEmpty()) continue;
                                ItemHelper.addToList(stack, list);
                            }
                            return list;
                        }).orElse(NonNullList.of(ItemStack.EMPTY));
    }

    @Unique
    public NonNullList<FluidStack> gTTCore$getOutputFluidStacks() {
        return getBasin()
                .map(
                        basin -> ((BasinBlockEntityAccessor) basin)
                                .getOutputTank()
                                .getCapability()
                                .map(handler -> {
                                    NonNullList<FluidStack> list = NonNullList.create();
                                    for (int i = 0; i < handler.getTanks(); i++) {
                                        var stack = handler.getFluidInTank(i);
                                        if (stack.isEmpty()) continue;
                                        list.add(stack);
                                    }
                                    return list;
                                }).orElse(NonNullList.of(FluidStack.EMPTY))
                ).orElse(NonNullList.of(FluidStack.EMPTY));
    }

    @Inject(method = "applyBasinRecipe", at = @At("HEAD"), cancellable = true)
    protected void applyBasinRecipeMixin(CallbackInfo ci) {
        Optional<GTRecipe> recipeOptional = gTTCore$getGTRecipe();
        if (recipeOptional.isPresent()) {
            var recipe = recipeOptional.get();
            ci.cancel();
            Optional<BasinBlockEntity> basinOptional = getBasin();

            if (basinOptional.isEmpty()) {
               return;
            }
            BasinBlockEntity basin = basinOptional.get();
            var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, gTTCore$getInputItemStacks());
            var inputFluidHandler = new DummyFluidHandler(IO.IN, gTTCore$getInputFluidStacks());
            var outputItemHandler = new TypedStackingDummyItemHandler(9);
            var outputFluidHandler = new DummyFluidHandler(IO.OUT, 2, 1000);
            gTTCore$getOutputItemStacks().forEach(itemStack -> ItemHandlerHelper.insertItem(outputItemHandler.getStorage(), itemStack, false));
            var outputFluid = gTTCore$getOutputFluidStacks();
            for (int i = 0; i < outputFluid.size(); i++) {
                outputFluidHandler.getStorages()[i].fill(outputFluid.get(i), IFluidHandler.FluidAction.EXECUTE);
            }

            var capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
                    RecipeHandlerList.of(IO.IN, inputItemHandler, inputFluidHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1), IgnoreCircuitRecipeHandler.INSTANCE),
                    RecipeHandlerList.of(IO.OUT, outputItemHandler, outputFluidHandler)
            );

            if (RecipeHelper.handleRecipeIO(
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
                basin.inputInventory.clearContent();
                basin.getOutputInventory().clearContent();

                inputItemHandler.getContents().forEach(o -> ItemHandlerHelper.insertItemStacked(basin.inputInventory, (ItemStack) o, false));
                basin.getOutputInventory().allowInsertion();
                outputItemHandler.getContents().forEach(o -> ItemHandlerHelper.insertItemStacked(basin.getOutputInventory(), (ItemStack) o, false));
                basin.getOutputInventory().forbidInsertion();

                basin.inputTank.getCapability().ifPresent(fluidHandler -> {
                    for (int i = 0; i < fluidHandler.getTanks(); i++) {
                        fluidHandler.drain(i, IFluidHandler.FluidAction.EXECUTE);
                    }
                });
                ((BasinBlockEntityAccessor) basin).getOutputTank().getCapability().ifPresent(fluidHandler -> {
                    for (int i = 0; i < fluidHandler.getTanks(); i++) {
                        fluidHandler.drain(i, IFluidHandler.FluidAction.EXECUTE);
                    }
                });
                inputFluidHandler.getContents().forEach(o -> basin.inputTank.getCapability().ifPresent(fluidHandler -> fluidHandler.fill((FluidStack) o, IFluidHandler.FluidAction.EXECUTE)));
                ((BasinBlockEntityAccessor) basin).getOutputTank().allowInsertion();
                outputFluidHandler.getContents().forEach(o -> ((BasinBlockEntityAccessor) basin).getOutputTank().getCapability().ifPresent(fluidHandler -> fluidHandler.fill((FluidStack) o, IFluidHandler.FluidAction.EXECUTE)));
                ((BasinBlockEntityAccessor) basin).getOutputTank().forbidInsertion();
                if (matchRecipe(capabilityHolder, recipe).isSuccess()) continueWithPreviousRecipe();
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    protected void tickMixin(CallbackInfo ci){
    }

    @Inject(method = "updateBasin", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/processing/basin/BasinOperatingBlockEntity;getMatchingRecipes()Ljava/util/List;"), cancellable = true)
    protected void updateBasinMixin(CallbackInfoReturnable<Boolean> cir){
        Optional<BasinBlockEntity> basinOptional = getBasin();
        if (basinOptional.isEmpty()) return;
        var recipeOptional = gTTCore$getGTRecipe();
        if (recipeOptional.isEmpty()) {
            cir.setReturnValue(true);
            return;
        }
        startProcessingBasin();
        ((BasinOperatingBlockEntity)(Object) this).sendData();
    }

    @Unique
    protected Optional<GTRecipe> gTTCore$getGTRecipe() {
        var recipeTypeOptional = gTTCore$getGTRecipeType();
        if (recipeTypeOptional.isEmpty()) return Optional.empty();
        var basinOptional = getBasin();
        if (basinOptional.isEmpty()) return Optional.empty();
        var basin = basinOptional.get();
        var inputItemHandler = new DummyRecipeUtils.DummyItemHandler(IO.IN, gTTCore$getInputItemStacks());
        var inputFluidHandler = new DummyFluidHandler(IO.IN, gTTCore$getInputFluidStacks());
        var outputItemHandler = new TypedStackingDummyItemHandler(9);
        var outputFluidHandler = new DummyFluidHandler(IO.OUT, 2, 1000);
        gTTCore$getOutputItemStacks().forEach(itemStack -> ItemHandlerHelper.insertItem(outputItemHandler.getStorage(), itemStack, false));
        var outputFluid = gTTCore$getOutputFluidStacks();
        for (int i = 0; i < outputFluid.size(); i++) {
            outputFluidHandler.getStorages()[i].fill(outputFluid.get(i), IFluidHandler.FluidAction.EXECUTE);
        }

        var capabilityHolder = new DummyRecipeUtils.DummyRecipeCapabilityHolder(
                RecipeHandlerList.of(IO.IN, inputItemHandler, inputFluidHandler, new DummyRecipeUtils.DummyEnergyContainer(V[LV], V[LV], 1), IgnoreCircuitRecipeHandler.INSTANCE),
                RecipeHandlerList.of(IO.OUT, outputItemHandler, outputFluidHandler)
        );
        Iterator<GTRecipe> match = recipeTypeOptional.get().searchRecipe(capabilityHolder, r ->
                (RecipeHelper.getOutputFluids(r).stream().anyMatch(basin.getFilter()::test) ||
                        RecipeHelper.getOutputItems(r).stream().anyMatch(basin.getFilter()::test))
                        && matchRecipe(capabilityHolder, r).isSuccess()
        );
        if (match.hasNext()) {
            return Optional.of(match.next());
        }
        return Optional.empty();
    }

    @Unique
    public Optional<GTRecipeType> gTTCore$getGTRecipeType() {
        BasinOperatingBlockEntity self = (BasinOperatingBlockEntity) (Object) this;
        if (self instanceof MechanicalPressBlockEntity) {
            return Optional.of(GTRecipeTypes.COMPRESSOR_RECIPES);
        } else if (self instanceof MechanicalMixerBlockEntity) {
            return Optional.of(GTRecipeTypes.MIXER_RECIPES);
        }
        return Optional.empty();
    }
}
