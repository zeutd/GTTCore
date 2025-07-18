package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

@SuppressWarnings("unchecked")
public class GTTItems {
    public static ItemEntry<Item> PACKAGED_CIRCUIT_ULV = REGISTRATE.item("packaged_circuit_ulv", Item::new)
            .lang("Packaged ULV Circuit").tag(CustomTags.ULV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_LV = REGISTRATE.item("packaged_circuit_lv", Item::new)
            .lang("Packaged LV Circuit").tag(CustomTags.LV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_MV = REGISTRATE.item("packaged_circuit_mv", Item::new)
            .lang("Packaged MV Circuit").tag(CustomTags.MV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_HV = REGISTRATE.item("packaged_circuit_hv", Item::new)
            .lang("Packaged HV Circuit").tag(CustomTags.HV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_EV = REGISTRATE.item("packaged_circuit_ev", Item::new)
            .lang("Packaged EV Circuit").tag(CustomTags.EV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_IV = REGISTRATE.item("packaged_circuit_iv", Item::new)
            .lang("Packaged IV Circuit").tag(CustomTags.IV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_LuV = REGISTRATE.item("packaged_circuit_luv", Item::new)
            .lang("Packaged LuV Circuit").tag(CustomTags.LuV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_ZPM = REGISTRATE.item("packaged_circuit_zpm", Item::new)
            .lang("Packaged ZPM Circuit").tag(CustomTags.ZPM_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UV = REGISTRATE.item("packaged_circuit_uv", Item::new)
            .lang("Packaged UV Circuit").tag(CustomTags.UV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UHV = REGISTRATE.item("packaged_circuit_uhv", Item::new)
            .lang("Packaged UHV Circuit").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UEV = REGISTRATE.item("packaged_circuit_uev", Item::new)
            .lang("Packaged UEV Circuit").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UIV = REGISTRATE.item("packaged_circuit_uiv", Item::new)
            .lang("Packaged UIV Circuit").tag(CustomTags.UIV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_UXV = REGISTRATE.item("packaged_circuit_uxv", Item::new)
            .lang("Packaged UXV Circuit").tag(CustomTags.UXV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_OpV = REGISTRATE.item("packaged_circuit_opv", Item::new)
            .lang("Packaged OpV Circuit").tag(CustomTags.OpV_CIRCUITS)
            .register();
    public static ItemEntry<Item> PACKAGED_CIRCUIT_MAX = REGISTRATE.item("packaged_circuit_max", Item::new)
            .lang("Packaged MAX Circuit").tag(CustomTags.MAX_CIRCUITS)
            .register();
    public static ItemEntry<Item> PARTICLE_CONTAINER = REGISTRATE.item("particle_container", Item::new)
            .lang("Particle Container")
            .register();
    public static ItemEntry<Item> MAGNETIC_CONFINEMENT_RING = REGISTRATE.item("magnetic_confinement_ring", Item::new)
            .lang("Magnetic Confinement Ring")
            .register();
    public static ItemEntry<Item> GRAVITATIONAL_LENS = REGISTRATE.item("gravitational_lens", Item::new)
            .lang("Gravitational Lens")
            .register();


    //T8: Optical

    public static ItemEntry<Item> OPTICAL_PROCESSOR_ZPM = REGISTRATE.item("optical_processor", Item::new)
            .lang("Optical Processor").tag(CustomTags.ZPM_CIRCUITS)
            .register();
    public static ItemEntry<Item> OPTICAL_PROCESSOR_ASSEMBLY_UV = REGISTRATE.item("optical_processor_assembly", Item::new)
            .lang("Optical Processor Assembly").tag(CustomTags.UV_CIRCUITS)
            .register();
    public static ItemEntry<Item> OPTICAL_SUPER_COMPUTER_UHV = REGISTRATE.item("optical_processor_computer", Item::new)
            .lang("Optical Processor Supercomputer").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> OPTICAL_MAINFRAME_UEV = REGISTRATE.item("optical_processor_mainframe", Item::new)
            .lang("Optical Processor Mainframe").tag(CustomTags.UEV_CIRCUITS)
            .register();

    //T9: Entanglement
    public static ItemEntry<Item> ENTANGLEMENT_PROCESSOR_UV = REGISTRATE.item("entanglement_processor", Item::new)
            .lang("Entanglement Processor").tag(CustomTags.UV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ENTANGLEMENT_PROCESSOR_ASSEMBLY_UHV = REGISTRATE.item("entanglement_processor_assembly", Item::new)
            .lang("Entanglement Processor Assembly").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ENTANGLEMENT_SUPER_COMPUTER_UEV = REGISTRATE.item("entanglement_processor_computer", Item::new)
            .lang("Entanglement Processor Supercomputer").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ENTANGLEMENT_MAINFRAME_UIV = REGISTRATE.item("entanglement_processor_mainframe", Item::new)
            .lang("Entanglement Processor Mainframe").tag(CustomTags.UIV_CIRCUITS)
            .register();

    //T10: Annihilation
    public static ItemEntry<Item> ANNIHILATION_PROCESSOR_UHV = REGISTRATE.item("annihilation_processor", Item::new)
            .lang("Annihilation Processor").tag(CustomTags.UHV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ANNIHILATION_PROCESSOR_ASSEMBLY_UEV = REGISTRATE.item("annihilation_processor_assembly", Item::new)
            .lang("Annihilation Processor Assembly").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ANNIHILATION_SUPER_COMPUTER_UIV = REGISTRATE.item("annihilation_processor_computer", Item::new)
            .lang("Annihilation Processor Supercomputer").tag(CustomTags.UIV_CIRCUITS)
            .register();
    public static ItemEntry<Item> ANNIHILATION_MAINFRAME_UXV = REGISTRATE.item("annihilation_processor_mainframe", Item::new)
            .lang("Annihilation Processor Mainframe").tag(CustomTags.UXV_CIRCUITS)
            .register();

    //T11 : Hypercausal
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_UEV = REGISTRATE.item("hypercausal_processor", Item::new)
            .lang("Hypercausal Processor").tag(CustomTags.UEV_CIRCUITS)
            .register();
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_UIV = REGISTRATE.item("hypercausal_processor_assembly", Item::new)
            .lang("Hypercausal Processor Assembly").tag(CustomTags.UIV_CIRCUITS)
            .register();
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_UXV = REGISTRATE.item("hypercausal_processor_computer", Item::new)
            .lang("Hypercausal Processor Supercomputer").tag(CustomTags.UXV_CIRCUITS)
            .register();
    public static ItemEntry<Item> HYPERCAUSAL_PROCESSOR_OpV = REGISTRATE.item("hypercausal_processor_mainframe", Item::new)
            .lang("Hypercausal Processor Mainframe").tag(CustomTags.OpV_CIRCUITS)
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
