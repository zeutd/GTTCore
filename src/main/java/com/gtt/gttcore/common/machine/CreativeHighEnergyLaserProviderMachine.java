package com.gtt.gttcore.common.machine;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import com.gtt.gttcore.api.machine.trait.InfiniteHighEnergyLaserContainer;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.SwitchWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import lombok.Getter;
import net.minecraft.world.entity.player.Player;

public class CreativeHighEnergyLaserProviderMachine extends MetaMachine implements IUIMachine {
    private final InfiniteHighEnergyLaserContainer laserContainer;
    @Getter
    @SaveField
    @SyncToClient
    private boolean active;
    public CreativeHighEnergyLaserProviderMachine(BlockEntityCreationInfo info) {
        super(info);
        laserContainer = new InfiniteHighEnergyLaserContainer(this, 0);
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(140, 95, this, entityPlayer)
                .background(GuiTextures.BACKGROUND)
                .widget(new TextFieldWidget(9, 20, 122, 16, () -> String.valueOf(laserContainer.getMaxLaser()), value -> laserContainer.setMaxLaser(Integer.parseInt(value))).setNumbersOnly(0, Integer.MAX_VALUE))
                .widget(
                        new SwitchWidget(9, 66, 122, 20, (clickData, value) -> setActive(value))
                                .setSupplier(this::isActive)
                                .setTexture(new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("gtceu.creative.activity.off")), new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("gtceu.creative.activity.on"))))
                ;
    }

    public void setActive(boolean active){
        this.active = active;
        getSyncDataHolder().markClientSyncFieldDirty("active");
    }
}
