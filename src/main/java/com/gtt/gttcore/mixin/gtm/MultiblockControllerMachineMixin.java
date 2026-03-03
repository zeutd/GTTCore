package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gtt.gttcore.api.ICheckPattern;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.locks.Lock;

@Mixin(value = MultiblockControllerMachine.class, remap = false)
public abstract class MultiblockControllerMachineMixin implements ICheckPattern {
    @Shadow @NotNull public abstract MultiblockState getMultiblockState();
    @Shadow protected boolean isFormed;
    @Shadow @Final private Lock patternLock;

    @Unique
    private boolean gTTCore$checkStructureButtonToggled = true;


    /**
     * @author zeutd
     * @reason checkPattern
     */
    @Inject(method = "asyncCheckPattern", at = @At("HEAD"), cancellable = true)
    public void asyncCheckPatternMixin(long periodID, CallbackInfo ci) {
        if (!gTTCore$isCheckStructureButtonToggled()) ci.cancel();

        //MultiblockControllerMachine self = (MultiblockControllerMachine)(Object) this;
    }

    @Inject(method = "onStructureFormed", at = @At("TAIL"))
    public void onStructureFormedMixin(CallbackInfo ci) {
        gTTCore$setCheckStructureButtonToggled(false);
        //MultiblockControllerMachine self = (MultiblockControllerMachine)(Object) this;
    }

    @Override
    public boolean gTTCore$isCheckStructureButtonToggled() {
        return gTTCore$checkStructureButtonToggled;
    }

    @Override
    public void gTTCore$setCheckStructureButtonToggled(boolean newValue) {
        gTTCore$checkStructureButtonToggled = newValue;
    }
}
