package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import static com.gregtechceu.gtceu.api.GTValues.ULV;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Lead;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gtt.gttcore.common.data.GTTItems.*;

public class GTTCraftingComponents {
    public static void init(){
        ROBOT_ARM.add(ULV, ROBOT_ARM_ULV.asStack());
        PUMP.add(ULV, ELECTRIC_PUMP_ULV.asStack());
        PISTON.add(ULV, ELECTRIC_PISTON_ULV.asStack());
        CONVEYOR.add(ULV, CONVEYOR_MODULE_ULV.asStack());
        MOTOR.add(ULV, ELECTRIC_MOTOR_ULV.asStack());

        ROTOR.add(ULV, ChemicalHelper.get(TagPrefix.rotor, Lead));
    }
}
