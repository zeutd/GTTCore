package com.gtt.gttcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gtt.gttcore.api.machine.trait.NotifiableHighEnergyLaserContainer;
import com.gtt.gttcore.common.machine.IHighEnergyLaserHatch;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public class HighEnergyLaserHatchPartMachine extends MultiblockPartMachine implements IHighEnergyLaserProvider, IHighEnergyLaserHatch {
    protected NotifiableHighEnergyLaserContainer highEnergyLaserContainer;

    public HighEnergyLaserHatchPartMachine(BlockEntityCreationInfo info, boolean transmitter) {
        super(info);
        this.highEnergyLaserContainer = new NotifiableHighEnergyLaserContainer(this, transmitter ? IO.OUT : IO.IN, transmitter);
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
    public int getLaserAmount() {
        return highEnergyLaserContainer.getLaserAmount();
    }

    @Override
    public void setLaserAmount(int amount) {
        highEnergyLaserContainer.setLaserAmount(amount);
    }

    @Override
    public boolean isTransmitter() {
        return highEnergyLaserContainer.isTransmitter();
    }
}
