package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine;
import com.gregtechceu.gtceu.common.machine.trait.BedrockOreMinerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/** Just a temporary fix*/
@Mixin(value = BedrockOreMinerLogic.class, remap = false)
public class Fix1 {
    @Inject(method = "validMachineClasses", at = @At("HEAD"), cancellable = true)
    private void fix1(CallbackInfoReturnable<List<Class<?>>> cir) {
        cir.setReturnValue(List.of(BedrockOreMinerMachine.class));
    }
}
