package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.api.TypedStackingItemStackHandler;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TypedStackingDummyItemHandler implements IRecipeHandler<Ingredient> {
    public TypedStackingItemStackHandler storage;

    public TypedStackingDummyItemHandler(int slots) {
        storage = new TypedStackingItemStackHandler(slots);
    }
    @Override
    public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
        if (io != IO.OUT) return left;

        // Store the ItemStack in each slot after an operation
        // Necessary for simulation since we don't actually modify the slot's contents
        // Doesn't hurt for execution, and definitely cheaper than copying the entire storage
        ItemStack[] visited = new ItemStack[storage.getSlots()];
        for (var it = left.listIterator(); it.hasNext();) {
            var ingredient = it.next();
            if (ingredient.isEmpty()) {
                it.remove();
                continue;
            }

            ItemStack[] items;
            int amount;
            if (ingredient instanceof IntProviderIngredient provider) {
                provider.setItemStacks(null);
                provider.setSampledCount(-1);

                ItemStack output;
                if (simulate) {
                    output = provider.getMaxSizeStack();
                    items = new ItemStack[] { output };
                } else {
                    items = provider.getItems();
                    if (items.length == 0 || items[0].isEmpty()) {
                        it.remove();
                        continue;
                    }
                    output = items[0];
                }
                amount = output.getCount();
            } else {
                items = ingredient.getItems();
                if (items.length == 0 || items[0].isEmpty()) {
                    it.remove();
                    continue;
                }
                if (ingredient instanceof SizedIngredient si) amount = si.getAmount();
                else amount = items[0].getCount();
            }

            for (int slot = 0; slot < storage.getSlots(); ++slot) {
                ItemStack current = visited[slot] == null ? storage.getStackInSlot(slot) : visited[slot];
                int count = current.getCount();

                // IO.OUT
                ItemStack output = items[0].copyWithCount(amount);
                // Only try this slot if not visited or if visited with the same type of item
                if (visited[slot] == null || GTUtil.isSameItemSameTags(visited[slot], output)) {
                    if (count < output.getMaxStackSize() && count < storage.getSlotLimit(slot)) {
                        var remainder = storage.insertItem(slot, output, simulate);
                        if (remainder.getCount() < amount) {
                            visited[slot] = output.copyWithCount(count + amount - remainder.getCount());
                        }
                        amount = remainder.getCount();
                    }
                }

                if (amount <= 0) {
                    it.remove();
                    break;
                }
            }
            // Modify ingredient if we didn't finish it off
            if (amount > 0) {
                if (ingredient instanceof SizedIngredient si) {
                    si.setAmount(amount);
                } else {
                    items[0].setCount(amount);
                }
            }
        }

        return left.isEmpty() ? null : left;
    }

    @Override
    public @NotNull List<Object> getContents() {
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < storage.getSlots(); ++i) {
            ItemStack stack = storage.getStackInSlot(i);
            if (!stack.isEmpty()) {
                stacks.add(stack);
            }
        }
        return new ArrayList<>(stacks);
    }

    @Override
    public double getTotalContentAmount() {
        long amount = 0;
        for (int i = 0; i < storage.getSlots(); ++i) {
            ItemStack stack = storage.getStackInSlot(i);
            if (!stack.isEmpty()) {
                amount += stack.getCount();
            }
        }
        return amount;
    }

    @Override
    public RecipeCapability<Ingredient> getCapability() {
        return ItemRecipeCapability.CAP;
    }
}
