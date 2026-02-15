package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

@Getter
public abstract class SlotMultiblockMachine extends WorkableElectricMultiblockMachine implements IMachineModifyDrops {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            SlotMultiblockMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Persisted
    private final NotifiableItemStackHandler inventory;
    public SlotMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        inventory = createInventory(args);
    }

    protected NotifiableItemStackHandler createInventory(Object... args) {
        return new NotifiableItemStackHandler(this, 1, IO.NONE, IO.BOTH, size -> new CustomItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                SlotMultiblockMachine.this.onContentsChanged();
            }
        }).setFilter(getFilter());
    }

    public void onContentsChanged(){}

    @Override
    public @NotNull Widget createUIWidget() {
        var widget = super.createUIWidget();
        if (widget instanceof WidgetGroup group) {
            var size = group.getSize();
            group.addWidget(
                    new SlotWidget(inventory.storage, 0, 10, size.height - 10 - 16, true, true)
                            .setBackground(GuiTextures.SLOT));
        }
        return widget;
    }

    @Override
    public void onDrops(List<ItemStack> drops) {
        clearInventory(inventory);
    }

    public abstract Predicate<ItemStack> getFilter();
}
