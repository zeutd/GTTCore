package com.gtt.gttcore.mixin.adastra;

import earth.terrarium.adastra.common.systems.OxygenApiImpl;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = OxygenApiImpl.class, remap = false)
public class OxygenApiImplMixin {

    @Inject(method = "hasOxygen(Lnet/minecraft/resources/ResourceKey;)Z", at = @At("RETURN"), cancellable = true)
    private void hasOxygenMixin(ResourceKey<Level> level, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(cir.getReturnValueZ() && !(level == Level.NETHER || level == Level.END));
    }
}
