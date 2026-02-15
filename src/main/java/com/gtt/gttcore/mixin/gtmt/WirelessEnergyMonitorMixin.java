package com.gtt.gttcore.mixin.gtmt;

import com.gtt.gttcore.util.WirelessUtil;
import com.hepdd.gtmthings.common.block.machine.electric.WirelessEnergyMonitor;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = WirelessEnergyMonitor.class, remap = false)
public class WirelessEnergyMonitorMixin {
    @Redirect(method = "addDisplayText", at = @At(value = "INVOKE", target = "Lcom/hepdd/gtmthings/common/block/machine/electric/WirelessEnergyMonitor;getDisplayText(ZI)Ljava/util/List;"))
    private List<Component> getDisplayTextMixin(WirelessEnergyMonitor instance, boolean all, int displayTextWidth){
        return WirelessUtil.getDisplayText(instance, all, displayTextWidth);
    }
}
