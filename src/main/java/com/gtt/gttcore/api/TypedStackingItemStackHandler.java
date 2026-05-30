package com.gtt.gttcore.api;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class TypedStackingItemStackHandler extends ItemStackHandler {
    public TypedStackingItemStackHandler() {
        super();
    }

    public TypedStackingItemStackHandler(int size) {
        super(size);
    }

    public TypedStackingItemStackHandler(ItemStack itemStack) {
        this(NonNullList.of(ItemStack.EMPTY, itemStack));
    }

    public TypedStackingItemStackHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        int firstFreeSlot = -1;

        for (int i = 0; i < getSlots(); i++) {
            // Only insert if no other slot already has a stack of this item
            if (i != slot && ItemHandlerHelper.canItemStacksStack(stack, getStackInSlot(i)))
                return stack;
            if (getStackInSlot(i)
                    .isEmpty() && firstFreeSlot == -1)
                firstFreeSlot = i;
        }

        // Only insert if this is the first empty slot, prevents overfilling in the
        // simulation pass
        if (getStackInSlot(slot)
                .isEmpty() && firstFreeSlot != slot)
            return stack;
        return super.insertItem(slot, stack, simulate);
    }
}
