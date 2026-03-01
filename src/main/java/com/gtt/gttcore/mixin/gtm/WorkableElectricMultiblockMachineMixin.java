package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gtt.gttcore.api.GTTGuiTextures;
import com.gtt.gttcore.api.ICheckPattern;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = WorkableElectricMultiblockMachine.class, remap = false)
public class WorkableElectricMultiblockMachineMixin {
    @Inject(method = "attachConfigurators", at = @At("HEAD"))
    private void attachConfiguratorsMixin(ConfiguratorPanel configuratorPanel, CallbackInfo ci){
        if (this instanceof ICheckPattern checkPattern) {
            configuratorPanel.attachConfigurators(new IFancyConfiguratorButton.Toggle(
                    GTTGuiTextures.BUTTON_CHECK_STRUCTURE.getSubTexture(0, 0, 1, 0.5),
                    GTTGuiTextures.BUTTON_CHECK_STRUCTURE.getSubTexture(0, 0.5, 1, 0.5),
                    checkPattern::gTTCore$isCheckStructureButtonToggled,
                    (cd, p) -> checkPattern.gTTCore$setCheckStructureButtonToggled(p))
                    .setTooltipsSupplier(
                            p -> List.of(
                                    Component.translatable("gttcore.machine.check_structure")))
            );
        }
    }
}
