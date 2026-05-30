package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderFluidIngredient;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.transfer.fluid.CustomFluidTank;
import lombok.Getter;
import net.minecraft.core.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DummyFluidHandler implements IRecipeHandler<FluidIngredient> {
    @Getter
    public final IO handlerIO;
    @SaveField
    @Getter
    protected final CustomFluidTank[] storages;
    @Getter
    protected boolean allowSameFluids; // Can different tanks be filled with the same fluid. It should be determined
    // while creating tanks.
    private @Nullable Boolean isEmpty;

    public DummyFluidHandler(IO io, int slots, int capacity) {
        handlerIO = io;
        storages = new CustomFluidTank[slots];
        for (int i = 0; i < storages.length; i++) {
            storages[i] = new CustomFluidTank(capacity);
        }
        if (io == IO.IN) {
            this.allowSameFluids = true;
        }
    }

    public DummyFluidHandler(IO io, NonNullList<FluidStack> stacks) {
        handlerIO = io;
        storages = stacks.stream().map(CustomFluidTank::new).toArray(CustomFluidTank[]::new);
        if (io == IO.IN) {
            this.allowSameFluids = true;
        }
    }

    @Override
    public @Nullable List<FluidIngredient> handleRecipeInner(IO io, GTRecipe recipe, List<FluidIngredient> left,
                                                             boolean simulate) {
        if (io != handlerIO) return left;
        if (io != IO.IN && io != IO.OUT) return left.isEmpty() ? null : left;

        IFluidHandler.FluidAction action = simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE;
        // Store the FluidStack in each slot after an operation
        // Necessary for simulation since we don't actually modify the slot's contents
        // Doesn't hurt for execution, and definitely cheaper than copying the entire storage
        FluidStack[] visited = new FluidStack[storages.length];
        for (var it = left.iterator(); it.hasNext();) {
            var ingredient = it.next();
            if (ingredient.isEmpty()) {
                it.remove();
                continue;
            }

            FluidStack[] fluids;

            if (ingredient instanceof IntProviderFluidIngredient provider) {
                provider.setFluidStacks(null);
                provider.setSampledCount(-1);

                if (simulate) {
                    fluids = new FluidStack[] { provider.getMaxSizeStack() };
                } else {
                    fluids = provider.getStacks();
                }
            } else {
                fluids = ingredient.getStacks();
            }
            if (fluids.length == 0 || fluids[0].isEmpty()) {
                it.remove();
                continue;
            }
            int amount = fluids[0].getAmount();

            if (io == IO.OUT && !allowSameFluids) {
                CustomFluidTank existing = null;
                int tank = 0;
                for (int i = 0; i < storages.length; ++i) {
                    var storage = storages[i];
                    if (!storage.getFluid().isEmpty() && storage.getFluid().isFluidEqual(fluids[0])) {
                        existing = storage;
                        tank = i;
                        break;
                    }
                }
                if (existing != null) {
                    FluidStack output = fluids[0].copy();
                    output.setAmount(amount);
                    int filled = existing.fill(output, action);
                    if (filled > 0) {
                        visited[tank] = output.copy();
                        // shortcut for oldAmount + filled (wow what an idea)
                        visited[tank].setAmount(existing.getFluidAmount());
                    }
                    amount -= filled;

                    if (amount > 0) ingredient.setAmount(amount);
                    else it.remove();
                    // Continue to next ingredient regardless of if we filled this ingredient completely
                    continue;
                }
            }

            for (int tank = 0; tank < storages.length; ++tank) {
                FluidStack current = visited[tank] == null ? getFluidInTank(tank) : visited[tank];
                int count = current.getAmount();

                if (io == IO.IN) {
                    if (current.isEmpty()) continue;
                    if (ingredient.test(current)) {
                        var drained = storages[tank].drain(Math.min(count, amount), action);
                        if (!drained.isEmpty()) {
                            visited[tank] = drained.copy();
                            visited[tank].setAmount(count - drained.getAmount());
                        }
                        amount -= drained.getAmount();
                    }
                } else { // IO.OUT && allow same fluids
                    FluidStack output = fluids[0].copy();
                    output.setAmount(amount);
                    if (visited[tank] == null || visited[tank].isFluidEqual(output)) {
                        if (count < storages[tank].getCapacity()) {
                            int filled = storages[tank].fill(output, action);
                            if (filled > 0) {
                                visited[tank] = output.copy();
                                visited[tank].setAmount(count + filled);
                                amount -= filled;

                                if (!allowSameFluids) {
                                    if (amount <= 0) it.remove();
                                    break;
                                }
                            }
                        }
                    }
                }

                if (amount <= 0) {
                    it.remove();
                    break;
                }
            }
            // Modify ingredient if we didn't finish it off
            if (amount > 0) {
                ingredient.setAmount(amount);
            }
        }

        return left.isEmpty() ? null : left;
    }

    @Override
    public RecipeCapability<FluidIngredient> getCapability() {
        return FluidRecipeCapability.CAP;
    }

    public int getTanks() {
        return storages.length;
    }

    @Override
    public int getSize() {
        return getTanks();
    }

    @Override
    public @NotNull List<Object> getContents() {
        List<FluidStack> ingredients = new ArrayList<>();
        for (int i = 0; i < getTanks(); ++i) {
            FluidStack stack = getFluidInTank(i);
            if (!stack.isEmpty()) {
                ingredients.add(stack);
            }
        }
        return new ArrayList<>(ingredients);
    }

    public FluidStack getFluidInTank(int tank) {
        return storages[tank].getFluid();
    }

    @Override
    public double getTotalContentAmount() {
        long amount = 0;
        for (int i = 0; i < getTanks(); ++i) {
            FluidStack stack = getFluidInTank(i);
            if (!stack.isEmpty()) {
                amount += stack.getAmount();
            }
        }
        return amount;
    }

    public boolean isEmpty() {
        if (isEmpty == null) {
            isEmpty = true;
            for (CustomFluidTank storage : storages) {
                if (!storage.getFluid().isEmpty()) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }
}
