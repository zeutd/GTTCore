package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.client.renderer.cover.ICoverRenderer;
import com.gregtechceu.gtceu.client.renderer.cover.IOCoverRenderer;
import com.gregtechceu.gtceu.client.renderer.cover.SimpleCoverRenderer;
import com.gregtechceu.gtceu.common.cover.ConveyorCover;
import com.gregtechceu.gtceu.common.cover.FluidRegulatorCover;
import com.gregtechceu.gtceu.common.cover.PumpCover;
import com.gregtechceu.gtceu.common.cover.RobotArmCover;
import com.gtt.gttcore.GTTCore;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.ULV;

public class GTTCovers {

    static{

    }
    public static CoverDefinition register(ResourceLocation id, CoverDefinition.CoverBehaviourProvider behaviorCreator,
                                           Supplier<Supplier<ICoverRenderer>> coverRenderer) {
        var definition = new CoverDefinition(id, behaviorCreator, coverRenderer);
        GTRegistries.COVERS.register(definition.getId(), definition);
        return definition;
    }
    private static CoverDefinition register(String id, CoverDefinition.CoverBehaviourProvider behaviorCreator) {
        return register(id, behaviorCreator, () -> () -> new SimpleCoverRenderer(GTTCore.id("block/cover/" + id)));
    }

    private static CoverDefinition register(String id, CoverDefinition.CoverBehaviourProvider behaviorCreator,
                                            Supplier<Supplier<ICoverRenderer>> coverRenderer) {
        return register(GTTCore.id(id), behaviorCreator, coverRenderer);
    }

    private static CoverDefinition registerULV(String id,
                                                    CoverDefinition.TieredCoverBehaviourProvider behaviorCreator,
                                                    Supplier<Int2ObjectFunction<ICoverRenderer>> coverRenderer
                                                 ) {
            var name = id + "." + GTValues.VN[ULV].toLowerCase(Locale.ROOT);
            return register(name, (def, coverable, side) -> behaviorCreator.create(def, coverable, side, ULV),
                    () -> () -> coverRenderer.get().apply(ULV));
    }

    public final static CoverDefinition CONVEYOR_ULV = registerULV("conveyor", ConveyorCover::new,
            () -> tier -> new IOCoverRenderer(
                    GTTCore.id("block/cover/conveyor"),
                    null,
                    GTTCore.id("block/cover/conveyor_emissive"),
                    GTTCore.id("block/cover/conveyor_inverted_emissive"))
            );

    public final static CoverDefinition ROBOT_ARM_ULV = registerULV("robot_arm", RobotArmCover::new,
            () -> tier -> new IOCoverRenderer(
                    GTTCore.id("block/cover/arm"),
                    null,
                    GTTCore.id("block/cover/arm_emissive"),
                    GTTCore.id("block/cover/arm_inverted_emissive"))
            );

    public final static CoverDefinition PUMP_ULV = registerULV("pump", PumpCover::new,
            () -> tier -> IOCoverRenderer.PUMP_LIKE_COVER_RENDERER);

    public final static CoverDefinition FLUID_REGULATOR_ULV = registerULV("fluid_regulator", FluidRegulatorCover::new,
            () -> tier -> IOCoverRenderer.PUMP_LIKE_COVER_RENDERER);


    public static void init(){

    }
}
