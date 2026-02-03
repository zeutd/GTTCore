package com.gtt.gttcore.mixin;

import com.gregtechceu.gtceu.common.machine.multiblock.part.ParallelHatchPartMachine;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ParallelHatchPartMachine.class, remap = false)
public class ParallelHatchPartMachineMixin {
    @Mutable
    @Final
    @Shadow
    private int maxParallel;
    @Redirect(method = "<init>", at = @At(value = "FIELD", target = "Lcom/gregtechceu/gtceu/common/machine/multiblock/part/ParallelHatchPartMachine;maxParallel:I", opcode = Opcodes.PUTFIELD))
    private void injected(ParallelHatchPartMachine instance, int value) {
        maxParallel = value * 4;
    }
}
