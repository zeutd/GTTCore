package com.gtt.gttcore.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.IOpticalComputationHatch;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gtt.gttcore.api.capability.recipe.HighEnergyLaserRecipeCapability;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotifiableHighEnergyLaserContainer extends NotifiableRecipeHandlerTrait<Integer>{
    protected IO handlerIO;
    @Getter
    protected boolean transmitter;
    @Getter
    @Setter
    protected long laserAmount;

    public NotifiableHighEnergyLaserContainer(MetaMachine machine, IO handlerIO, boolean transmitter) {
        super(machine);
        this.handlerIO = handlerIO;
        this.transmitter = transmitter;
    }

    @Override
    public IO getHandlerIO() {
        return handlerIO;
    }

    @Override
    public List<Integer> handleRecipeInner(IO io, GTRecipe recipe, List<Integer> left, boolean simulate) {
        return List.of();
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(laserAmount);
    }

    @Override
    public double getTotalContentAmount() {
        return laserAmount;
    }

    @Override
    public RecipeCapability<Integer> getCapability() {
        return HighEnergyLaserRecipeCapability.CAP;
    }
}
