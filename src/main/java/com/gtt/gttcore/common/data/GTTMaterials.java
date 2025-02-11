package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gtt.gttcore.GTTCore;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTTMaterials {
    public static Material
        ElectronDegeneratium,
            UltraHighMolecularWeightPolyethylene, Fullerene,
            WetDibutylMagnesium, DibutylMagnesium, Bromobutane, SodiumBromide, Butanol, CultivateProducts, SodiumSulfate, Octanol, OctanolMagnesium, PhosphorusTrichloride, PhthaloylChlorine, DiisooctylPhthalate, MicrocrystalMagnesiumChloride, ZieglerNattaCatalyst,
            Zircon, ZirconiumCarbide, ZirconiumTetrachloride,
            DepletedUranium238, DepletedPlutonium239, DepletedThorium
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
    }
    public static void modify() {
        Zirconium.setMaterialARGB(0x7799a9);
        Zirconium.setMaterialSecondaryARGB(0x6489a9);
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());
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
    }
}
