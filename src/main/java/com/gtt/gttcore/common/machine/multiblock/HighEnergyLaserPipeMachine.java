package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import com.gtt.gttcore.common.machine.multiblock.part.HighEnergyLaserHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class HighEnergyLaserPipeMachine extends WorkableElectricMultiblockMachine {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            HighEnergyLaserPipeMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
    public Set<HighEnergyLaserHatchPartMachine> laserOutputHatchMachines;
    public Set<HighEnergyLaserHatchPartMachine> laserInputHatchMachines;
    protected ConditionalSubscriptionHandler updateSubscription;
    protected int laserAmount;
    public HighEnergyLaserPipeMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        updateSubscription = new ConditionalSubscriptionHandler(this, this::updateTick,
                this::isFormed);
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(Component.translatable("gttcore.machine.high_energy_laser_amount", laserAmount));
    }

    public void updateTick() {
        this.laserAmount = 0;
        for (HighEnergyLaserHatchPartMachine laserHatch : laserInputHatchMachines){
            this.laserAmount += laserHatch.getLaserAmount();
            laserHatch.setLaserAmount(0);
        }
        for (HighEnergyLaserHatchPartMachine laserHatch : laserOutputHatchMachines){
            if (recipeLogic.isWorking()) {
                laserHatch.setLaserAmount(laserAmount / laserOutputHatchMachines.size());
            } else {
                laserHatch.setLaserAmount(0);
            }
        }

    }



    @Override
    public void onStructureFormed(){
        super.onStructureFormed();
        for (IMultiPart part : getParts()) {
            if (part instanceof HighEnergyLaserHatchPartMachine laserHatchMachine) {
                laserInputHatchMachines = Objects.requireNonNullElseGet(laserInputHatchMachines, HashSet::new);
                laserOutputHatchMachines = Objects.requireNonNullElseGet(laserOutputHatchMachines, HashSet::new);
                if (laserHatchMachine.isTransmitter()){
                    laserOutputHatchMachines.add(laserHatchMachine);
                }
                else{
                    laserInputHatchMachines.add(laserHatchMachine);
                }
            }
        }

        updateSubscription.initialize(getLevel());
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        for (HighEnergyLaserHatchPartMachine laserHatch : laserOutputHatchMachines){
            laserHatch.setLaserAmount(0);
        }
        for (HighEnergyLaserHatchPartMachine laserHatch : laserInputHatchMachines){
            laserHatch.setLaserAmount(0);
        }
        laserInputHatchMachines = null;
        laserOutputHatchMachines = null;
        updateSubscription.unsubscribe();
    }
}
