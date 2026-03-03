package com.gtt.gttcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.gui.widget.TankWidget;
import com.gregtechceu.gtceu.api.gui.widget.ToggleButtonWidget;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class SteamFluidHatchPartMachine extends FluidHatchPartMachine {
    private final String autoTooltipKey;

    public SteamFluidHatchPartMachine(BlockEntityCreationInfo info, IO io) {
        super(info, 1, io, 1000, 1);
        autoTooltipKey = io == IO.IN ? "gtceu.gui.item_auto_input.tooltip" : "gtceu.gui.item_auto_output.tooltip";
    }

    @NotNull
    @Override
    public ModularUI createUI(@NotNull Player entityPlayer) {
        int rowSize = 1;
        var modular = new ModularUI(176,
                18 + 18 * rowSize + 105, this, entityPlayer)
                .background(GuiTextures.BACKGROUND_STEAM.get(ConfigHolder.INSTANCE.machines.steelSteamMultiblocks))
                .widget(new LabelWidget(10, 5, getBlockState().getBlock().getDescriptionId()))
                .widget(new ToggleButtonWidget(7, 18 + 18 * rowSize, 18, 18,
                        GuiTextures.BUTTON_ITEM_OUTPUT, this::isWorkingEnabled, this::setWorkingEnabled)
                        .setShouldUseBaseBackground() // TODO: Steamify background
                        .setTooltipText(autoTooltipKey))
                .widget(UITemplate.bindPlayerInventory(entityPlayer.getInventory(),
                        GuiTextures.SLOT_STEAM.get(ConfigHolder.INSTANCE.machines.steelSteamMultiblocks),
                        7, 18 + 18 * rowSize + 24, true));
        modular.widget(new TankWidget(tank.getStorages()[0], 0,
                (88 - rowSize * 9), 18 + 6, true, io.support(IO.IN))
                .setBackground(
                        GuiTextures.SLOT_STEAM.get(ConfigHolder.INSTANCE.machines.steelSteamMultiblocks)));
        return modular;
    }
}
