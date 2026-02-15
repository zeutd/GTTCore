package com.gtt.gttcore.mixin.gtmt;

import com.gtt.gttcore.util.WirelessUtil;
import com.hepdd.gtmthings.common.item.WirelessEnergyTerminalBehavior;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = WirelessEnergyTerminalBehavior.class, remap = false)
public class WirelessEnergyTerminalBehaviorMixin {
    @Redirect(method = "addDisplayText", at = @At(value = "INVOKE", target = "Lcom/hepdd/gtmthings/common/item/WirelessEnergyTerminalBehavior$WirelessMonitor;getDisplayText(ZI)Ljava/util/List;"))
    private static List<Component> getDisplayTextMixin(WirelessEnergyTerminalBehavior.WirelessMonitor instance, boolean all, int displayTextWidth){
        return WirelessUtil.getDisplayText(instance, all, displayTextWidth);
    }
}
