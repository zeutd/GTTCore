package com.gtt.gttcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gtt.gttcore.api.machine.trait.NotifiableHighEnergyLaserContainer;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import lombok.Getter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public class HighEnergyLaserHatchPartMachine extends MultiblockPartMachine implements IHighEnergyLaserProvider {
    private final boolean transmitter;
    protected NotifiableHighEnergyLaserContainer highEnergyLaserContainer;

    public HighEnergyLaserHatchPartMachine(IMachineBlockEntity holder, boolean transmitter) {
        super(holder);
        this.transmitter = transmitter;
        this.highEnergyLaserContainer = new NotifiableHighEnergyLaserContainer(this, IO.IN, transmitter);
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return false;
    }

    @Override
    public boolean canShared() {
        return false;
    }

    @Override
    public long getLaserAmount() {
        return highEnergyLaserContainer.getLaserAmount();
    }

    @Override
    public void setLaserAmount(long amount) {
        highEnergyLaserContainer.setLaserAmount(amount);
    }

    @Override
    public boolean isTransmitter() {
        return transmitter;
    }
}
