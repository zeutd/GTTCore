package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import com.gtt.gttcore.common.machine.multiblock.part.HighEnergyLaserHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class HighEnergyLaserMachine extends WorkableElectricMultiblockMachine {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            HighEnergyLaserMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
    public Set<HighEnergyLaserHatchPartMachine> laserInputHatchMachines;
    public Set<HighEnergyLaserHatchPartMachine> laserOutputHatchMachines;
    protected ConditionalSubscriptionHandler updateSubscription;
    protected long laserAmount;
    public HighEnergyLaserMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        updateSubscription = new ConditionalSubscriptionHandler(this, this::updateTick,
                this::isFormed);
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        long recipeLaserOutput = 0;
        if (isWorkingEnabled() && recipeLogic.getLastRecipe() != null) {
            if(recipeLogic.getLastRecipe().outputs.containsKey(HighEnergyLaserRecipeCapability.CAP)) recipeLaserOutput = recipeLogic.getLastRecipe().outputs.get(HighEnergyLaserRecipeCapability.CAP).stream().map(c -> (Integer) c.content).reduce(0, Integer::sum);
        }
        if(recipeLaserOutput > 0) {
            textList.add(Component.translatable("gttcore.machine.high_energy_laser_amount", Math.abs(recipeLaserOutput)));
        } else {
            textList.add(Component.translatable("gttcore.machine.high_energy_laser_amount", Math.abs(this.laserAmount)));
        }
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        boolean value = super.beforeWorking(recipe);
        if (recipe != null && recipe.inputs.containsKey(HighEnergyLaserRecipeCapability.CAP)) {
            int recipeLaserAmount = recipe.inputs.get(HighEnergyLaserRecipeCapability.CAP).stream().map(c -> (Integer) c.content).reduce(0, Integer::sum);
            if (recipeLaserAmount <= 0) return value;
            if (this.laserAmount < recipeLaserAmount) {
                getRecipeLogic().interruptRecipe();
                recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
                return false;
            }
        }
        return value;
    }

    @Override
    public boolean onWorking() {
        GTRecipe recipe = recipeLogic.getLastRecipe();
        boolean value = super.onWorking();
        if (recipe != null && recipe.inputs.containsKey(HighEnergyLaserRecipeCapability.CAP)) {
            int recipeLaserAmount = recipe.inputs.get(HighEnergyLaserRecipeCapability.CAP).stream().map(c -> (Integer) c.content).reduce(0, Integer::sum);
            if (recipeLaserAmount > 0 && this.laserAmount < recipeLaserAmount) {
                getRecipeLogic().interruptRecipe();
                recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
                return false;
            }
        }
        return value;
    }

    public void updateTick() {
        this.laserAmount = 0;
        int recipeLaserOutput = 0;
        if (recipeLogic.getLastRecipe() != null) {
            if(recipeLogic.getLastRecipe().outputs.containsKey(HighEnergyLaserRecipeCapability.CAP)) recipeLaserOutput = recipeLogic.getLastRecipe().outputs.get(HighEnergyLaserRecipeCapability.CAP).stream().map(c -> (Integer) c.content).reduce(0, Integer::sum);
        }
        boolean output = recipeLaserOutput < 0;
        for (HighEnergyLaserHatchPartMachine laserHatch : laserInputHatchMachines){
            this.laserAmount += laserHatch.getLaserAmount();
            laserHatch.setLaserAmount(0);
        }
        if (output) {
            for (HighEnergyLaserHatchPartMachine laserHatch : laserOutputHatchMachines) {
                if (recipeLogic.isWorking()) {
                    laserHatch.setLaserAmount(-recipeLaserOutput / laserOutputHatchMachines.size());
                } else {
                    laserHatch.setLaserAmount(0);
                }
                BlockPos pos = laserHatch.getPos().relative(laserHatch.getFrontFacing());
                BlockState state = getLevel().getBlockState(pos);
                if (state.hasBlockEntity()) {
                    BlockEntity entity = getLevel().getBlockEntity(pos);
                    if (entity instanceof MetaMachineBlockEntity machineBlockEntity && machineBlockEntity.getMetaMachine() instanceof IHighEnergyLaserProvider other && !other.isTransmitter()) {
                        other.setLaserAmount(laserHatch.getLaserAmount());
                    }
                }
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
                if(laserHatchMachine.isTransmitter()){
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
        for (HighEnergyLaserHatchPartMachine laserHatch : laserInputHatchMachines){
            laserHatch.setLaserAmount(0);
        }
        laserInputHatchMachines = null;
        for (HighEnergyLaserHatchPartMachine laserHatch : laserOutputHatchMachines){
            laserHatch.setLaserAmount(0);
        }
        laserOutputHatchMachines = null;
        updateSubscription.unsubscribe();
    }
}
