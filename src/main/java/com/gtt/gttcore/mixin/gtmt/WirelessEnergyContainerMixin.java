package com.gtt.gttcore.mixin.gtmt;

import com.gtt.gttcore.api.IWirelessEnergyContainerMixin;
import com.hepdd.gtmthings.api.misc.WirelessEnergyContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.math.BigInteger;

@Mixin(value = WirelessEnergyContainer.class, remap = false)
public abstract class WirelessEnergyContainerMixin implements IWirelessEnergyContainerMixin {
    @Shadow public abstract BigInteger getCapacity();

    @Shadow public abstract BigInteger getStorage();

    @Unique
    public BigInteger gTTCore$capacity = BigInteger.ZERO;
    @Unique
    @Override
    public void gTTCore$setCapacity(BigInteger capacity){
        this.gTTCore$capacity = capacity;
    }
    @Inject(method = "getCapacity", at = @At("HEAD"), cancellable = true)
    private void getCapacityMixin(CallbackInfoReturnable<BigInteger> cir){
        cir.setReturnValue(gTTCore$capacity);
    }

    @ModifyVariable(method = "addEnergy", at = @At(value = "STORE"), ordinal = 1)
    private long modifyChange(long change){
        return getCapacity().subtract(getStorage()).min(BigInteger.valueOf(change)).longValue();
    }
}
