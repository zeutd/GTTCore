package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.item.behavior.TurbineRotorBehaviour;
import com.gregtechceu.gtceu.common.machine.multiblock.part.RotorHolderPartMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.*;

@Mixin(RotorHolderPartMachine.class)
public abstract class RotorHolderPartMachineMixin{

    /**
     * @author zeutd
     * @reason To make it not to set render state value when it isn't exists
     */
    @Overwrite(remap = false)
    private void onRotorInventoryChanged() {
        var self = ((RotorHolderPartMachine)(Object)this);
        var stack = self.getRotorStack();
        var rotorBehaviour = TurbineRotorBehaviour.getBehaviour(stack);
        if (rotorBehaviour != null) {
            self.rotorMaterial = rotorBehaviour.getPartMaterial(stack);

            boolean emissive = self.rotorMaterial.hasProperty(PropertyKey.ORE) &&
                    self.rotorMaterial.getProperty(PropertyKey.ORE).isEmissive();
            if (self.getRenderState().hasProperty(HAS_ROTOR) && self.getRenderState().hasProperty(IS_EMISSIVE_ROTOR))
                self.setRenderState(self.getRenderState()
                    .setValue(HAS_ROTOR, true)
                    .setValue(IS_EMISSIVE_ROTOR, emissive));
        } else {
            self.rotorMaterial = GTMaterials.NULL;
            if (self.getRenderState().hasProperty(HAS_ROTOR) && self.getRenderState().hasProperty(IS_EMISSIVE_ROTOR))
                self.setRenderState(self.getRenderState()
                    .setValue(HAS_ROTOR, false)
                    .setValue(IS_EMISSIVE_ROTOR, false));
        }
    }

    /**
     * @author zeutd
     * @reason To make it not to set render state value when it isn't exists
     */
    @Overwrite(remap = false)
    public void setRotorSpeed(int rotorSpeed) {
        var self = ((RotorHolderPartMachine)(Object)this);
        if (((self.rotorSpeed > 0 && rotorSpeed <= 0) || (self.rotorSpeed <= 0 && rotorSpeed > 0)) && self.getRenderState().hasProperty(IS_ROTOR_SPINNING)) {
            self.setRenderState(self.getRenderState().setValue(IS_ROTOR_SPINNING, rotorSpeed > 0));
        }
        self.rotorSpeed = rotorSpeed;
    }
}
