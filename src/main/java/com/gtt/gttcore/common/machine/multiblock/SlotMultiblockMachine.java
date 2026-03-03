package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

@Getter
public abstract class SlotMultiblockMachine extends WorkableElectricMultiblockMachine {
    @SaveField
    @SyncToClient
    private final NotifiableItemStackHandler inventory;
    public SlotMultiblockMachine(BlockEntityCreationInfo info) {
        super(info);
        inventory = createInventory();
    }

    protected NotifiableItemStackHandler createInventory() {
        return new NotifiableItemStackHandler(this, 1, IO.NONE, IO.BOTH, size -> new CustomItemStackHandler(size) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                SlotMultiblockMachine.this.onContentsChanged();
                getSyncDataHolder().markClientSyncFieldDirty("inventory");
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

    public abstract Predicate<ItemStack> getFilter();
}
