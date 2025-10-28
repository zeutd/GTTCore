package com.gtt.gttcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IWorkableMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gtt.gttcore.api.machine.trait.NotifiableHighEnergyLaserContainer;
import com.gtt.gttcore.common.machine.IHighEnergyLaserHatch;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import lombok.Getter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.gtt.gttcore.GTTCore.LOGGER;


@ParametersAreNonnullByDefault
public class HighEnergyLaserHatchPartMachine extends MultiblockPartMachine implements IHighEnergyLaserProvider, IHighEnergyLaserHatch {
    protected NotifiableHighEnergyLaserContainer highEnergyLaserContainer;

    public HighEnergyLaserHatchPartMachine(IMachineBlockEntity holder, boolean transmitter) {
        super(holder);
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
