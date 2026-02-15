package com.gtt.gttcore.mixin.adastra;

import earth.terrarium.adastra.common.systems.GravityApiImpl;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GravityApiImpl.class, remap = false)
public class GravityApiImplMixin {
    @Inject(method = "getGravity(Lnet/minecraft/resources/ResourceKey;)F", at = @At("RETURN"), cancellable = true)
    private void getGravityMixin(ResourceKey<Level> level, CallbackInfoReturnable<Float> cir){
        if (level == Level.END) cir.setReturnValue(0.1f);
        else cir.setReturnValue(cir.getReturnValueF());
    }
}
