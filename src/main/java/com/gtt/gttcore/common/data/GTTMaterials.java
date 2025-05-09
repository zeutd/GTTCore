package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gtt.gttcore.GTTCore;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTTMaterials {
    public static Material
        ElectronDegeneratium,
            UltraHighMolecularWeightPolyethylene, Fullerene,
            WetDibutylMagnesium, DibutylMagnesium, Bromobutane, SodiumBromide, Butanol, CultivateProducts, SodiumSulfate, Octanol, OctanolMagnesium, PhosphorusTrichloride, PhthaloylChlorine, DiisooctylPhthalate, MicrocrystalMagnesiumChloride, ZieglerNattaCatalyst,
            Zircon, ZirconiumCarbide, ZirconiumTetrachloride,
            DepletedUranium238, DepletedPlutonium239, DepletedThorium,
            SupercriticalSteam,
            AtomicSteel,
            PlatinumGroupSolution, PlatinumPalladiumSolution, AmmoniumHexachloroplatinate, PalladiumSolution, Diamminedichloropalladium, InertMetalSodiumBisulfate, RhodiumSulfateSolution, RhodiumHydroxide, ChlororhodicAcid, AmmoniumHexachlororhodate, AmmoniumHexanitrorhodium, SodiumPeroxide, RutheniumOsmiumIridiumMixture, RutheniumOsmiumIridiumMixtureSodiumPeroxide, RutheniumOsmiumSaltSolution, IridiumOxide, AmmoniumHexachloroiridate, RutheniumOsmiumOxide, RutheniumOxide, RutheniumSolution, OsmiumSolution, AmmoniumHexachlororuthenate, Tetraamminedioxidoosmiumdichloride
       ;
    public static void init() {
        ElectronDegeneratium = new Material.Builder(GTTCore.id("electron_degeneratium"))
                .ingot()
                .dust()
                .liquid()
                .color(0x55AA55).iconSet(METALLIC)
                .element(GTTElements.Ed)
                .flags(MaterialFlags.GENERATE_PLATE)
                .buildAndRegister();
        UltraHighMolecularWeightPolyethylene = new Material.Builder(GTTCore.id("ultra_high_molecular_weight_polyethylene"))
                .polymer()
                .liquid(new FluidBuilder().temperature(408))
                .color(0x989898)
                .flags(MaterialFlags.GENERATE_FOIL)
                .components(Carbon, 2, Hydrogen, 4)
                .buildAndRegister();
        Fullerene = new Material.Builder(GTTCore.id("fullerene"))
                .polymer()
                .liquid(new FluidBuilder().temperature(408))
                .color(0x282828)
                .flags(MaterialFlags.GENERATE_FOIL)
                .components(Carbon, 60)
                .buildAndRegister();
        WetDibutylMagnesium = new Material.Builder(GTTCore.id("wet_dibutyl_magnesium"))
                .fluid()
                .color(0xbbbb71)
                .components(Carbon, 8, Hydrogen, 18, Magnesium, 1)
                .buildAndRegister();
        DibutylMagnesium = new Material.Builder(GTTCore.id("dibutyl_magnesium"))
                .fluid()
                .color(0xdddd91)
                .components(Carbon, 8, Hydrogen, 18, Magnesium, 1)
                .buildAndRegister();
        Bromobutane = new Material.Builder(GTTCore.id("bromobutane"))
                .fluid()
                .color(0xdddd91)
                .components(Carbon, 4, Hydrogen, 9, Bromine, 1)
                .buildAndRegister();
        SodiumBromide = new Material.Builder(GTTCore.id("sodium_bromide"))
                .dust()
                .color(0xdddddd)
                .components(Sodium, 1, Bromine, 1)
                .buildAndRegister();
        Butanol = new Material.Builder(GTTCore.id("butanol"))
                .fluid()
                .color(0x669999)
                .components(Carbon, 4, Hydrogen, 9, Oxygen, 1, Hydrogen, 1)
                .buildAndRegister();
        CultivateProducts = new Material.Builder(GTTCore.id("cultivate_products"))
                .fluid()
                .color(0x775533)
                .buildAndRegister();
        SodiumSulfate = new Material.Builder(GTTCore.id("sodium_sulfate"))
                .dust()
                .color(0xDDDDDD)
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .buildAndRegister();
        Octanol = new Material.Builder(GTTCore.id("octanol"))
                .fluid()
                .color(0x997766)
                .components(Carbon, 8, Hydrogen, 18, Oxygen, 1)
                .buildAndRegister();
        OctanolMagnesium = new Material.Builder(GTTCore.id("octanol_magnesium"))
                .dust()
                .color(0x993366)
                .components(Magnesium, 1, Octanol, 2)
                .buildAndRegister();
        PhosphorusTrichloride = new Material.Builder(GTTCore.id("phosphorus_trichloride"))
                .fluid()
                .color(0xDDDD11)
                .components(Phosphorus, 4, Chlorine, 3)
                .buildAndRegister();
        PhthaloylChlorine = new Material.Builder(GTTCore.id("phthaloyl_chlorine"))
                .fluid()
                .color(0xDDDDDD)
                .components(Carbon, 8, Hydrogen, 4, Chlorine, 2, Oxygen, 2)
                .buildAndRegister();
        DiisooctylPhthalate = new Material.Builder(GTTCore.id("diisooctyl_phthalate"))
                .fluid()
                .color(0xDDDDDD)
                .components(Carbon, 24, Hydrogen, 38, Oxygen, 4)
                .buildAndRegister();
        MicrocrystalMagnesiumChloride = new Material.Builder(GTTCore.id("microcrystal_magnesium_chloride"))
                .dust()
                .color(0x442233)
                .components(Magnesium, 1, Chlorine, 2)
                .buildAndRegister();
        ZieglerNattaCatalyst = new Material.Builder(GTTCore.id("ziegler_natta_catalyst"))
                .dust()
                .color(0x666666)
                .components(TitaniumTetrachloride, 1, MagnesiumChloride, 1)
                .buildAndRegister();
        Zircon = new Material.Builder(GTTCore.id("zircon"))
                .gem()
                .color(0x93ff90)
                .ore(2, 1)
                .components(Zirconium, 1, Oxygen, 2, SiliconDioxide, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
        ZirconiumCarbide = new Material.Builder(GTTCore.id("zirconium_carbide"))
                .ingot()
                .color(0x2f1110)
                .components(Zirconium, 1, Carbon, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
        ZirconiumTetrachloride = new Material.Builder(GTTCore.id("zirconium_tetrachloride"))
                .dust()
                .color(0x229922)
                .components(Zirconium, 1, Chlorine, 4)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
        DepletedUranium238 = new Material.Builder(GTTCore.id("depleted_uranium"))
                .ingot(2)
                .color(0x1d891d).secondaryColor(0x33342c).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_ROD)
                .element(GTElements.U238)
                .radioactiveHazard(1)
                .buildAndRegister();
        DepletedPlutonium239 = new Material.Builder(GTTCore.id("depleted_plutonium"))
                .ingot(2)
                .color(0xba2727).secondaryColor(0x33342c).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_ROD)
                .element(GTElements.Pu239)
                .radioactiveHazard(1)
                .buildAndRegister();
        DepletedThorium = new Material.Builder(GTTCore.id("depleted_thorium"))
                .ingot(2)
                .color(0x25411b).secondaryColor(0x051E05).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_ROD)
                .element(GTElements.Th)
                .radioactiveHazard(1)
                .buildAndRegister();
        SupercriticalSteam = new Material.Builder(GTTCore.id("supercritical_steam"))
                .gas(new FluidBuilder()
                        .state(FluidState.GAS)
                        .temperature(10000))
                .color(0xEEEEEE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Oxygen, 1)
                .buildAndRegister();
        AtomicSteel = new Material.Builder(GTTCore.id("atomic_steel"))
                .color(0x122570)
                .ingot()
                .components(Chromium, 5, Iron, 3, Zirconium, 2, Naquadah, 3)
                .flags(GENERATE_FRAME, GENERATE_PLATE)
                .buildAndRegister();
        // Platinum Group Line
        PlatinumGroupSolution = new Material.Builder(GTTCore.id("platinum_group_solution"))
                .color(0x333354)
                .liquid()
                .buildAndRegister();
        AmmoniumHexachloroplatinate = new Material.Builder(GTTCore.id("ammonium_hexachloroplatinate"))
                .color(0xd2d580)
                .dust()
                .components(Nitrogen, 2, Hydrogen, 8, Platinum, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)2PtCl6", true);
        PlatinumPalladiumSolution = new Material.Builder(GTTCore.id("platinum_palladium_solution"))
                .color(0x545433)
                .liquid()
                .buildAndRegister();
        PalladiumSolution = new Material.Builder(GTTCore.id("palladium_solution"))
                .color(0x543333)
                .liquid()
                .buildAndRegister();
        Diamminedichloropalladium = new Material.Builder(GTTCore.id("diamminedichloropalladium"))
                .color(0x844533)
                .dust()
                .components(Chlorine, 2, Hydrogen, 6, Nitrogen, 2, Palladium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("PdCl2(NH3)2", true);
        InertMetalSodiumBisulfate = new Material.Builder(GTTCore.id("inert_metal_sodium_bisulfate"))
                .color(0x756565)
                .dust()
                .buildAndRegister();
        RhodiumSulfateSolution = new Material.Builder(GTTCore.id("rhodium_sulfate_solution"))
                .color(0x707000)
                .liquid()
                .buildAndRegister();
        RhodiumHydroxide = new Material.Builder(GTTCore.id("rhodium_hydroxide"))
                .color(0x888899)
                .dust()
                .components(Rhodium, 1, Oxygen, 3, Hydrogen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        ChlororhodicAcid = new Material.Builder(GTTCore.id("chlororhodic_acid"))
                .color(0x700070)
                .liquid()
                .components(Hydrogen, 3, Rhodium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AmmoniumHexachlororhodate = new Material.Builder(GTTCore.id("ammonium_hexachlororhodate"))
                .color(0xd42030)
                .dust()
                .components(Nitrogen, 3, Hydrogen, 12, Chlorine, 6, Rhodium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)3RhCl6", true);
        AmmoniumHexanitrorhodium = new Material.Builder(GTTCore.id("ammonium_hexanitrorhodium"))
                .color(0xf0f0b0)
                .dust()
                .components(Nitrogen, 8 ,Hydrogen, 8, Rhodium, 1, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)2(Rh(NO2)6)");
        SodiumPeroxide = new Material.Builder(GTTCore.id("sodium_peroxide"))
                .color(0xf0f0b0)
                .dust()
                .components(Sodium, 2, Oxygen, 2)
                .buildAndRegister();
        RutheniumOsmiumIridiumMixture = new Material.Builder(GTTCore.id("ruthenium_osmium_iridium_mixture"))
                .color(0xa0a0b0)
                .dust()
                .buildAndRegister();
        RutheniumOsmiumIridiumMixtureSodiumPeroxide = new Material.Builder(GTTCore.id("ruthenium_osmium_iridium_mixture_sodium_peroxide"))
                .color(0xb0b080)
                .dust()
                .buildAndRegister();
        RutheniumOsmiumSaltSolution = new Material.Builder(GTTCore.id("ruthenium_osmium_salt_solution"))
                .color(0x202050)
                .liquid()
                .buildAndRegister();
        IridiumOxide = new Material.Builder(GTTCore.id("iridium_oxide"))
                .color(0xa9a989)
                .dust()
                .components(Iridium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AmmoniumHexachloroiridate = new Material.Builder(GTTCore.id("ammonium_hexachloroiridate"))
                .color(0x89a9a9)
                .dust()
                .components(Nitrogen, 3, Hydrogen, 12, Iridium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)3IrCl6");
        RutheniumOsmiumOxide = new Material.Builder(GTTCore.id("ruthenium_osmium_oxide"))
                .color(0xa9b9c9)
                .dust()
                .buildAndRegister();
        RutheniumOxide = new Material.Builder(GTTCore.id("ruthenium_oxide"))
                .color(0xb9c9a9)
                .dust()
                .buildAndRegister();
        RutheniumSolution = new Material.Builder(GTTCore.id("ruthenium_solution"))
                .color(0xa9c9f9)
                .liquid()
                .buildAndRegister();
        OsmiumSolution = new Material.Builder(GTTCore.id("osmium_solution"))
                .color(0xa9f9e9)
                .liquid()
                .buildAndRegister();
        AmmoniumHexachlororuthenate = new Material.Builder(GTTCore.id("ammonium_hexachlororuthenate"))
                .color(0xf9c9e9)
                .dust()
                .components(Nitrogen, 3, Hydrogen, 12, Ruthenium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)3RuCl6");
        Tetraamminedioxidoosmiumdichloride = new Material.Builder(GTTCore.id("tetraamminedioxidoosmiumdichloride"))
                .color(0xc9f9e9)
                .dust()
                .components(Osmium, 1, Chlorine, 2, Oxygen, 2, Nitrogen, 4, Hydrogen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("OsCl2O2(NH3)4");
    }
    public static void modify() {
        Plutonium239.addFlags(MaterialFlags.GENERATE_ROD);

        Zirconium.setMaterialARGB(0x7799a9);
        Zirconium.setMaterialSecondaryARGB(0x6489a9);
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Zirconium.addFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_FRAME);
        Zirconium.setProperty(PropertyKey.FLUID_PIPE, new FluidPipeProperties(2000, 800, true, true, true, false));
        Zirconium.setProperty(PropertyKey.BLAST, (new BlastProperty.Builder().temp(1941, BlastProperty.GasTier.MID)
                .blastStats(VA[EV], 1500)
                .vacuumStats(VA[EV])).build());

        Polonium.setMaterialARGB(0x1519a9);
        Polonium.setMaterialSecondaryARGB(0x05051E);
        Polonium.setProperty(PropertyKey.INGOT, new IngotProperty());

        Radium.setMaterialARGB(0xa51919);
        Radium.setMaterialSecondaryARGB(0x1E0505);
        Radium.setProperty(PropertyKey.INGOT, new IngotProperty());

        Neptunium.setMaterialARGB(0x198595);
        Neptunium.setMaterialSecondaryARGB(0x051E1E);
        Neptunium.setProperty(PropertyKey.INGOT, new IngotProperty());

        InertMetalMixture.setComponents();
        InertMetalMixture.setFormula("");
    }
}
