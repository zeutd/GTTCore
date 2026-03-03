package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.item.behavior.CoverPlaceBehavior;
import com.gregtechceu.gtceu.common.item.behavior.TooltipBehavior;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gtt.gttcore.common.item.BeerItem;
import com.gtt.gttcore.util.LangUtil;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.GTValues.ULV;
import static com.gregtechceu.gtceu.api.GTValues.VCF;
import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;
import static net.minecraft.ChatFormatting.RESET;

@SuppressWarnings("unchecked")
public class GTTItems {
    public static ItemEntry<Item> PACKAGED_CIRCUIT_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_ulv", "封装ULV电路"), Item::new)
            .lang("Packaged ULV Circuit").tag(CustomTags.ULV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_LV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_lv", "封装LV电路"), Item::new)
            .lang("Packaged LV Circuit").tag(CustomTags.LV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_MV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_mv", "封装MV电路"), Item::new)
            .lang("Packaged MV Circuit").tag(CustomTags.MV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_HV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_hv", "封装HV电路"), Item::new)
            .lang("Packaged HV Circuit").tag(CustomTags.HV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_EV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_ev", "封装EV电路"), Item::new)
            .lang("Packaged EV Circuit").tag(CustomTags.EV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_IV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_iv", "封装IV电路"), Item::new)
            .lang("Packaged IV Circuit").tag(CustomTags.IV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_LuV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_luv", "封装LuV电路"), Item::new)
            .lang("Packaged LuV Circuit").tag(CustomTags.LuV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_ZPM = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_zpm", "封装ZPM电路"), Item::new)
            .lang("Packaged ZPM Circuit").tag(CustomTags.ZPM_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_uv", "封装UV电路"), Item::new)
            .lang("Packaged UV Circuit").tag(CustomTags.UV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UHV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_uhv", "封装UHV电路"), Item::new)
            .lang("Packaged UHV Circuit").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UEV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_uev", "封装UEV电路"), Item::new)
            .lang("Packaged UEV Circuit").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UIV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_uiv", "封装UIV电路"), Item::new)
            .lang("Packaged UIV Circuit").tag(CustomTags.UIV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UXV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_uxv", "封装UXV电路"), Item::new)
            .lang("Packaged UXV Circuit").tag(CustomTags.UXV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_OpV = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_opv", "封装OpV电路"), Item::new)
            .lang("Packaged OpV Circuit").tag(CustomTags.OpV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_MAX = REGISTRATE.item(LangUtil.createItemZhTranslation("packaged_circuit_max", "封装MAX电路"), Item::new)
            .lang("Packaged MAX Circuit").tag(CustomTags.MAX_CIRCUITS)
            .register();
    public static ItemEntry<Item> PARTICLE_CONTAINER = REGISTRATE.item(LangUtil.createItemZhTranslation("particle_container", "粒子容器"), Item::new)
            .lang("Particle Container")
            .register();
    public static ItemEntry<Item> MAGNETIC_CONFINEMENT_RING = REGISTRATE.item(LangUtil.createItemZhTranslation("magnetic_confinement_ring", "磁力约束环"), Item::new)
            .lang("Magnetic Confinement Ring")
            .register();
    public static ItemEntry<Item> GRAVITATIONAL_LENS = REGISTRATE.item(LangUtil.createItemZhTranslation("gravitational_lens", "引力透镜"), Item::new)
            .lang("Gravitational Lens")
            .register();
    public static ItemEntry<Item> NETHERITE_SCRAP_SEED = REGISTRATE.item(LangUtil.createItemZhTranslation("netherite_scrap_seed", "下界合金碎片种子"), Item::new)
            .lang("Netherite Scrap Seed")
            .register();


    public static ItemEntry<Item> UNAWAKENED_WETWARE_PROCESSOR_LuV = REGISTRATE.item(LangUtil.createItemZhTranslation("unawakened_wetware_processor", "未唤醒的湿件处理器"), Item::new)
            .lang("Unawakened Wetware Processor")
            .register();
    public static ItemEntry<Item> UNAWAKENED_WETWARE_PROCESSOR_ASSEMBLY_ZPM = REGISTRATE.item(LangUtil.createItemZhTranslation("unawakened_wetware_processor_assembly", "未唤醒的湿件处理器集群"), Item::new)
            .lang("Unawakened Wetware Processor Assembly")
            .register();
    public static ItemEntry<Item> UNAWAKENED_WETWARE_SUPER_COMPUTER_UV = REGISTRATE.item(LangUtil.createItemZhTranslation("unawakened_wetware_processor_computer", "未唤醒的湿件处理器超级计算机"), Item::new)
            .lang("Unawakened Wetware Processor Supercomputer")
            .register();
    public static ItemEntry<Item> UNAWAKENED_WETWARE_MAINFRAME_UHV = REGISTRATE.item(LangUtil.createItemZhTranslation("unawakened_wetware_processor_mainframe", "未唤醒的湿件处理器主机"), Item::new)
            .lang("Unawakened Wetware Processor Mainframe")
            .register();


    //T8: Optical

    public static ItemEntry<Item> OPTICAL_PROCESSOR_ZPM = REGISTRATE.item(LangUtil.createItemZhTranslation("optical_processor", "光学处理器"), Item::new)
            .lang("Optical Processor").tag(CustomTags.ZPM_CIRCUITS)
            .register();
    public static ItemEntry<Item> OPTICAL_PROCESSOR_ASSEMBLY_UV = REGISTRATE.item(LangUtil.createItemZhTranslation("optical_processor_assembly", "光学处理器集群"), Item::new)
            .lang("Optical Processor Assembly").tag(CustomTags.UV_CIRCUITS)
            .register();
    public static ItemEntry<Item> OPTICAL_SUPER_COMPUTER_UHV = REGISTRATE.item(LangUtil.createItemZhTranslation("optical_processor_computer", "光学处理器超级计算机"), Item::new)
            .lang("Optical Processor Supercomputer").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> OPTICAL_MAINFRAME_UEV = REGISTRATE.item(LangUtil.createItemZhTranslation("optical_processor_mainframe", "光学处理器主机"), Item::new)
            .lang("Optical Processor Mainframe").tag(CustomTags.UEV_CIRCUITS)
            .register();

    //T9: Entanglement
    public static ItemEntry<Item> ENTANGLEMENT_PROCESSOR_UV = REGISTRATE.item(LangUtil.createItemZhTranslation("entanglement_processor", "纠缠处理器"), Item::new)
            .lang("Entanglement Processor").tag(CustomTags.UV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ENTANGLEMENT_PROCESSOR_ASSEMBLY_UHV = REGISTRATE.item(LangUtil.createItemZhTranslation("entanglement_processor_assembly", "纠缠处理器集群"), Item::new)
            .lang("Entanglement Processor Assembly").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ENTANGLEMENT_SUPER_COMPUTER_UEV = REGISTRATE.item(LangUtil.createItemZhTranslation("entanglement_processor_computer", "纠缠处理器超级计算机"), Item::new)
            .lang("Entanglement Processor Supercomputer").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ENTANGLEMENT_MAINFRAME_UIV = REGISTRATE.item(LangUtil.createItemZhTranslation("entanglement_processor_mainframe", "纠缠处理器主机"), Item::new)
            .lang("Entanglement Processor Mainframe").tag(CustomTags.UIV_CIRCUITS)
            .register();

    //T10: Annihilation
    public static ItemEntry<Item> ANNIHILATION_PROCESSOR_UHV = REGISTRATE.item(LangUtil.createItemZhTranslation("annihilation_processor", "湮灭处理器"), Item::new)
            .lang("Annihilation Processor").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ANNIHILATION_PROCESSOR_ASSEMBLY_UEV = REGISTRATE.item(LangUtil.createItemZhTranslation("annihilation_processor_assembly", "湮灭处理器集群"), Item::new)
            .lang("Annihilation Processor Assembly").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ANNIHILATION_SUPER_COMPUTER_UIV = REGISTRATE.item(LangUtil.createItemZhTranslation("annihilation_processor_computer", "湮灭处理器超级计算机"), Item::new)
            .lang("Annihilation Processor Supercomputer").tag(CustomTags.UIV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ANNIHILATION_MAINFRAME_UXV = REGISTRATE.item(LangUtil.createItemZhTranslation("annihilation_processor_mainframe", "湮灭处理器主机"), Item::new)
            .lang("Annihilation Processor Mainframe").tag(CustomTags.UXV_CIRCUITS)
            .register();

    //T11 : Hypercausal
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_UEV = REGISTRATE.item(LangUtil.createItemZhTranslation("hypercausal_processor", "超因果处理器"), Item::new)
            .lang("Hypercausal Processor").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_UIV = REGISTRATE.item(LangUtil.createItemZhTranslation("hypercausal_processor_assembly", "超因果处理器集群"), Item::new)
            .lang("Hypercausal Processor Assembly").tag(CustomTags.UIV_CIRCUITS)
            .register();
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_UXV = REGISTRATE.item(LangUtil.createItemZhTranslation("hypercausal_processor_computer", "超因果处理器超级计算机"), Item::new)
            .lang("Hypercausal Processor Supercomputer").tag(CustomTags.UXV_CIRCUITS)
            .register();
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_OpV = REGISTRATE.item(LangUtil.createItemZhTranslation("hypercausal_processor_mainframe", "超因果处理器主机"), Item::new)
            .lang("Hypercausal Processor Mainframe").tag(CustomTags.OpV_CIRCUITS)
            .register();

    public static ItemEntry<Item> ELECTRIC_MOTOR_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("ulv_electric_motor", VCF[ULV] + "ULV" + RESET + "电动马达"), Item::new)
            .lang("ULV Electric Motor")
            .tag(CustomTags.ELECTRIC_MOTORS)
            .register();
    public static ItemEntry<ComponentItem> ELECTRIC_PUMP_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("ulv_electric_pump", VCF[ULV] + "ULV" + RESET + "电动泵"), ComponentItem::create)
            .lang("ULV Electric Pump")
            .onRegister(attach(new CoverPlaceBehavior(GTTCovers.PUMP_ULV)))
            .onRegister(attach(new TooltipBehavior(lines -> {
                lines.add(Component.translatable("item.gtceu.electric.pump.tooltip"));
                lines.add(Component.translatable("gtceu.universal.tooltip.fluid_transfer_rate",
                        FormattingUtil.formatNumbers(1280 / 4 / 20)));
            })))
            .tag(CustomTags.ELECTRIC_PUMPS)
            .register();
    public static ItemEntry<Item> ELECTRIC_PISTON_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("ulv_electric_piston", VCF[ULV] + "ULV" + RESET + "电动活塞"), Item::new)
            .lang("ULV Electric Piston")
            .tag(CustomTags.ELECTRIC_PISTONS)
            .register();
    public static ItemEntry<ComponentItem> FLUID_REGULATOR_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("ulv_fluid_regulator", VCF[ULV] + "ULV" + RESET + "流体校准器"), ComponentItem::create)
            .lang("ULV Fluid Regulator")
            .onRegister(attach(new CoverPlaceBehavior(GTTCovers.FLUID_REGULATOR_ULV)))
            .onRegister(attach(new TooltipBehavior(lines -> {
                lines.add(Component.translatable("item.gtceu.fluid.regulator.tooltip"));
                lines.add(Component.translatable("gtceu.universal.tooltip.fluid_transfer_rate", 1280 / 4 / 20));
            })))
            .tag(CustomTags.FLUID_REGULATORS)
            .register();
    public static ItemEntry<ComponentItem> ROBOT_ARM_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("ulv_robot_arm", VCF[ULV] + "ULV" + RESET + "机械臂"), ComponentItem::create)
            .lang("ULV Robot Arm")
            .onRegister(attach(new CoverPlaceBehavior(GTTCovers.ROBOT_ARM_ULV)))
            .onRegister(attach(new TooltipBehavior(lines -> {
                lines.add(Component.translatable("item.gtceu.robot.arm.tooltip"));
                lines.add(Component.translatable("gtceu.universal.tooltip.item_transfer_rate", 8 / 4));
            })))
            .tag(CustomTags.ROBOT_ARMS)
            .register();

    public static ItemEntry<ComponentItem> CONVEYOR_MODULE_ULV = REGISTRATE.item(LangUtil.createItemZhTranslation("ulv_conveyor_module", VCF[ULV] + "ULV" + RESET + "传送带"), ComponentItem::create)
            .lang("ULV Conveyor Module")
            .onRegister(attach(new CoverPlaceBehavior(GTTCovers.CONVEYOR_ULV)))
            .onRegister(attach(new TooltipBehavior(lines -> {
                lines.add(Component.translatable("item.gtceu.conveyor.module.tooltip"));
                lines.add(Component.translatable("gtceu.universal.tooltip.item_transfer_rate", 8 / 4));
            })))
            .tag(CustomTags.CONVEYOR_MODULES)
            .register();

    public static ItemEntry<BeerItem> BEER_BOTTLE = REGISTRATE.item(LangUtil.createItemZhTranslation("beer_bottle", "啤酒瓶"),BeerItem::new)
		.properties(p -> p
            .stacksTo(16)
            .food(new FoodProperties.Builder()
				.nutrition(1)
				.saturationMod(.6F)
				.alwaysEat()
				.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3 * 60 * 20, 0, false, false, false), 1F)
            .build()
			)
                    )
                    .lang("Beer Bottle")
		.register();
    public static final ItemEntry<Item>[] PACKAGED_CIRCUITS_ARRAY = new ItemEntry[]{
            PACKAGED_CIRCUIT_ULV,
            PACKAGED_CIRCUIT_LV,
            PACKAGED_CIRCUIT_MV,
            PACKAGED_CIRCUIT_HV,
            PACKAGED_CIRCUIT_EV,
            PACKAGED_CIRCUIT_IV,
            PACKAGED_CIRCUIT_LuV,
            PACKAGED_CIRCUIT_ZPM,
            PACKAGED_CIRCUIT_UV,
            PACKAGED_CIRCUIT_UHV,
            PACKAGED_CIRCUIT_UEV,
            PACKAGED_CIRCUIT_UIV,
            PACKAGED_CIRCUIT_UXV,
            PACKAGED_CIRCUIT_OpV,
            PACKAGED_CIRCUIT_MAX
    };
    public static void init(){

    }
}
