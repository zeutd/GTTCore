package com.gtt.gttcore.mixin.gtmt;

import com.gtt.gttcore.api.IWirelessEnergyContainerMixin;
import com.gtt.gttcore.util.WirelessUtil;
import com.hepdd.gtmthings.api.misc.WirelessEnergyContainer;
import com.hepdd.gtmthings.common.item.WirelessEnergyBindingToolBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.math.BigInteger;
import java.util.Objects;

@Mixin(value = WirelessEnergyBindingToolBehavior.class, remap = false)
public class WirelessEnergyBindingToolBehaviorMixin {
    @Inject(method = "onItemUseFirst", at = @At("HEAD"))
    private void onItemUseFirstMixin(ItemStack stack, UseOnContext context, CallbackInfoReturnable<InteractionResult> cir){
        if (!context.getLevel().isClientSide()) {
            BlockPos pos = context.getClickedPos();
            WirelessEnergyContainer container = WirelessEnergyContainer.getOrCreateContainer(Objects.requireNonNull(context.getPlayer()).getUUID());
            BigInteger capacity = WirelessUtil.getCapacity(context.getLevel(), pos);
            if (capacity.compareTo(BigInteger.ZERO) > 0) {
                ((IWirelessEnergyContainerMixin) (Object) container).gTTCore$setCapacity(capacity);
            }
        }
    }
}
