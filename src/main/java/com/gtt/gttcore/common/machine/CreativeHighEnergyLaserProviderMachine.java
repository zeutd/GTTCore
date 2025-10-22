package com.gtt.gttcore.common.machine;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.gregtechceu.gtceu.common.machine.storage.CreativeComputationProviderMachine;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.SwitchWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CreativeHighEnergyLaserProviderMachine extends MetaMachine implements IUIMachine, IHighEnergyLaserProvider {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(CreativeHighEnergyLaserProviderMachine.class, MetaMachine.MANAGED_FIELD_HOLDER);

    @Nullable
    private TickableSubscription laserSubs;

    @Getter
    @Persisted
    @DescSynced
    private boolean active;
    @Persisted
    @DescSynced
    private long maxLaserAmount;
    public CreativeHighEnergyLaserProviderMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public long getLaserAmount() {
        return maxLaserAmount;
    }

    @Override
    public void setLaserAmount(long amount) {

    }

    @Override
    public boolean isTransmitter() {
        return true;
    }

    protected void updateTick() {
        BlockPos pos = getPos().relative(getFrontFacing());
        BlockState state = getLevel().getBlockState(pos);
        if (state.hasBlockEntity()){
            BlockEntity entity = getLevel().getBlockEntity(pos);
            if (entity instanceof MetaMachineBlockEntity machineBlockEntity && machineBlockEntity.getMetaMachine() instanceof IHighEnergyLaserProvider other && !other.isTransmitter()){
                other.setLaserAmount(getLaserAmount());
            }
        }
    }

    protected void updateLaserSubscription() {
        if (active) {
            this.laserSubs = subscribeServerTick(this::updateTick);
        } else if (laserSubs != null) {
            laserSubs.unsubscribe();
        }
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(140, 95, this, entityPlayer)
                .background(GuiTextures.BACKGROUND)
                .widget(new LabelWidget(7, 7, Component.translatable("gttcore.machine.high_energy_laser_amount")))
                .widget(new TextFieldWidget(9, 20, 122, 16, () -> String.valueOf(maxLaserAmount), value -> maxLaserAmount = Integer.parseInt(value)).setNumbersOnly(0, Integer.MAX_VALUE))
                .widget(
                        new SwitchWidget(9, 66, 122, 20, (clickData, value) -> setActive(value))
                                .setSupplier(this::isActive)
                                .setTexture(new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("gtceu.creative.activity.off")), new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("gtceu.creative.activity.on"))))
                ;
    }
    public void setActive(boolean active){
        this.active = active;
        updateLaserSubscription();
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
