package com.gtt.gttcore.common.data;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.GTTMaterialBuilder;
import com.simibubi.create.AllItems;
import earth.terrarium.adastra.common.registry.ModItems;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gtt.gttcore.common.data.GTTElements.*;

public class GTTMaterials {

    public static Material[] andesiteAlloyIngredient = new Material[] {
            Pyrite,
            Magnetite,
            Limonite,
            Goethite,
            Hematite
    };
    public static Material MoonStone;
    public static Material MarsStone;
    public static Material MercuryStone;
    public static Material VenusStone;
    public static Material GlacioStone;
    public static Material InfernalSpire;

    public static Material FluixCrystal;
    public static Material RawBeer;
    public static Material Beer;
    public static Material AndesiteAlloy;
    public static Material Desh;
    public static Material Ostrum;
    public static Material Calorite;
    public static Material IceShard;
    public static Material UltraHighMolecularWeightPolyethylene;
    public static Material Fullerene;
    public static Material WetDibutylMagnesium;
    public static Material DibutylMagnesium;
    public static Material Bromobutane;
    public static Material SodiumBromide;
    public static Material Butanol;
    public static Material CultivateProducts;
    public static Material SodiumSulfate;
    public static Material Octanol;
    public static Material OctanolMagnesium;
    public static Material PhosphorusTrichloride;
    public static Material PhosphorusTrioxide;
    public static Material PhthaloylChlorine;
    public static Material DiisooctylPhthalate;
    public static Material MicrocrystalMagnesiumChloride;
    public static Material ZieglerNattaCatalyst;
    public static Material Zircon;
    public static Material ZirconiumCarbide;
    public static Material ZirconiumTetrachloride;

    public static Material DepletedUranium238;
    public static Material DepletedPlutonium239;
    public static Material DepletedThorium;

    public static Material SupercriticalSteam;
    public static Material AtomicSteel;

    public static Material PlatinumGroupSolution;
    public static Material PlatinumPalladiumSolution;
    public static Material AmmoniumHexachloroplatinate;
    public static Material PalladiumSolution;
    public static Material Diamminedichloropalladium;
    public static Material InertMetalSodiumBisulfate;
    public static Material RhodiumHydroxide;
    public static Material ChlororhodicAcid;
    public static Material AmmoniumHexachlororhodate;
    public static Material AmmoniumHexanitrorhodium;
    public static Material SodiumPeroxide;
    public static Material RutheniumOsmiumIridiumMixture;
    public static Material RutheniumOsmiumIridiumMixtureSodiumPeroxide;
    public static Material RutheniumOsmiumSaltSolution;
    public static Material IridiumOxide;
    public static Material AmmoniumHexachloroiridate;
    public static Material RutheniumOsmiumOxide;
    public static Material RutheniumOxide;
    public static Material RutheniumSolution;
    public static Material OsmiumSolution;
    public static Material AmmoniumHexachlororuthenate;
    public static Material Tetraamminedioxidoosmiumdichloride;


    public static Material HotBrine;
    public static Material HotChlorinatedBrominatedBrine;
    public static Material HotDebrominatedBrine;
    public static Material HotAlkalineDebrominatedBrine;
    public static Material AcidicBromineSolution;
    public static Material ConcentratedBromineSolution;
    public static Material HydrogenIodide;
    public static Material RawBrine;
    public static Material DebrominatedBrine;
    public static Material BrominatedChlorineVapor;
    public static Material AcidicBromineExhaust;

    public static Material PhenolicResin;

    public static Material Xenotime;
    public static Material OxalicAcid;
    public static Material EthyleneGlycol;
    public static Material EthyleneOxide;
    public static Material SodiumSulfite;
    public static Material AluminiumChloride;
    public static Material AluminiumFluoride;
    public static Material PotassiumManganate;
    public static Material PotassiumPermanganate;
    public static Material SodiumChlorate;
    public static Material RoastedRareEarth;
    public static Material LowCeriumContentRareEarthChlorideSolution;
    public static Material CeriumContainingResidue;
    public static Material CeriumSolution;
    public static Material DefluorinatedCeriumSolution;
    public static Material OxidizedCeriumSolution;
    public static Material RawCeriumOxide;
    public static Material CeriumOxide;
    public static Material CeriumWaste;
    public static Material PurifiedRareEarthChlorideSolution;
    public static Material HeavyMetalsRemovedRareEarthChlorideSolution;
    public static Material HeavyRareEarthSolution;
    public static Material MediumRareEarthSolution;
    public static Material LightRareEarthSolution;
    public static Material LanthanumSolution;
    public static Material LanthanumOxalateHydrate;
    public static Material NeodymiumSolution;
    public static Material NeodymiumOxalateHydrate;
    public static Material SamariumSolution;
    public static Material SamariumOxalateHydrate;
    public static Material EuropiumSolution;
    public static Material EuropiumOxalateHydrate;
    public static Material LutetiumSolution;
    public static Material LutetiumOxalateHydrate;
    public static Material YttriumSolution;
    public static Material YttriumOxalateHydrate;
    public static Material IronHydroxide;
    public static Material LeadHydroxide;
    public static Material ThoriumHydroxide;
    public static Material AluminiumHydroxide;
    public static Material CopperSulfide;
    public static Material LeadSulfide;
    public static Material P507;
    public static Material RawP507;
    public static Material P204;
    public static Material PhosphorusOxychloride;
    public static Material Di2EthylhexylPhosphorylChloride;
    public static Material Bis2EthylhexylPhosphite;
    public static Material Chlorooctane;

    public static Material Inconel718;

    public static Material VenusAir;
    public static Material LiquidVenusAir
                    ;
    public static void init() {
        MoonStone = new GTTMaterialBuilder(GTTCore.id("moon_stone"))
                .dust(2)
                .color(0x4F5C5D).secondaryColor(0x506869).iconSet(METALLIC)
                .flags(NO_SMASHING, NO_SMELTING)
                .chineseLangValue("月石")
                .buildAndRegister();
        MarsStone = new GTTMaterialBuilder(GTTCore.id("mars_stone"))
                .dust(2)
                .color(0xBF6F50).secondaryColor(0xCD9360).iconSet(METALLIC)
                .flags(NO_SMASHING, NO_SMELTING)
                .chineseLangValue("火星石")
                .buildAndRegister();
        MercuryStone = new GTTMaterialBuilder(GTTCore.id("mercury_stone"))
                .dust(2)
                .color(0x603948).secondaryColor(0x723E49).iconSet(METALLIC)
                .flags(NO_SMASHING, NO_SMELTING)
                .chineseLangValue("水星石")
                .buildAndRegister();
        VenusStone = new GTTMaterialBuilder(GTTCore.id("venus_stone"))
                .dust(2)
                .color(0xB88143).secondaryColor(0xDFB271).iconSet(METALLIC)
                .flags(NO_SMASHING, NO_SMELTING)
                .chineseLangValue("金星石")
                .buildAndRegister();
        GlacioStone = new GTTMaterialBuilder(GTTCore.id("glacio_stone"))
                .dust(2)
                .color(0x7C73A9).secondaryColor(0xB4B8D2).iconSet(METALLIC)
                .chineseLangValue("霜原石")
                .flags(NO_SMASHING, NO_SMELTING)
                .buildAndRegister();
        InfernalSpire = new GTTMaterialBuilder(GTTCore.id("infernal_spire"))
                .dust(2)
                .color(0x662F24).secondaryColor(0xD9863C).iconSet(METALLIC)
                .flags(NO_SMASHING, NO_SMELTING)
                .chineseLangValue("炎狱尖塔")
                .buildAndRegister();
        FluixCrystal = new GTTMaterialBuilder(GTTCore.id("fluix_crystal"))
                .gem()
                .dust()
                .color(0x995399).secondaryColor(0xb67eb7).iconSet(CERTUS)
                .appendFlags(EXT_METAL, NO_SMELTING, NO_SMASHING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(CertusQuartz, 1, Redstone, 1, NetherQuartz, 1)
                .chineseLangValue("福鲁伊克斯")
                .buildAndRegister();
        Desh = new GTTMaterialBuilder(GTTCore.id("desh"))
                .ingot()
                .dust()
                .liquid()
                .element(De)
                .ore()
                .chineseLangValue("戴斯")
                .color(0xDF5D07).secondaryColor(0xFF8D37).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_PLATE, GENERATE_ROD)
                .fluidPipeProperties(1900, 100, true, true, true, false)
                .buildAndRegister();
        Calorite = new GTTMaterialBuilder(GTTCore.id("calorite"))
                .ingot()
                .dust()
                .liquid()
                .element(Ct)
                .ore()
                .chineseLangValue("耐热金属")
                .color(0xD44267).secondaryColor(0xEE6688).iconSet(SHINY)
                .flags(MaterialFlags.GENERATE_PLATE, GENERATE_ROD)
                .fluidPipeProperties(2900, 200, true, true, true, true)
                .buildAndRegister();
        Ostrum = new GTTMaterialBuilder(GTTCore.id("ostrum"))
                .ingot()
                .dust()
                .liquid()
                .element(Om)
                .ore()
                .color(0xA95675).secondaryColor(0xCB7799).iconSet(METALLIC)
                .chineseLangValue("紫金")
                .flags(MaterialFlags.GENERATE_PLATE, GENERATE_ROD)
                .fluidPipeProperties(2400, 150, true, true, true, false)
                .buildAndRegister();
        IceShard = new GTTMaterialBuilder(GTTCore.id("ice_shard"))
                .gem()
                .components(Nitrogen, 1, Hydrogen, 4, Nitrogen, 1, Oxygen, 3)
                .color(0xE0DEFF)
                .chineseLangValue("寒冰碎片")
                .ore()
                .buildAndRegister();
        UltraHighMolecularWeightPolyethylene = new GTTMaterialBuilder(GTTCore.id("ultra_high_molecular_weight_polyethylene"))
                .polymer()
                .liquid(new FluidBuilder().temperature(408))
                .color(0x989898)
                .chineseLangValue("超高分子量聚乙烯")
                .flags(MaterialFlags.GENERATE_FOIL)
                .components(Carbon, 2, Hydrogen, 4)
                .buildAndRegister();
        Fullerene = new GTTMaterialBuilder(GTTCore.id("fullerene"))
                .polymer()
                .liquid(new FluidBuilder().temperature(408))
                .color(0x282828)
                .flags(MaterialFlags.GENERATE_FOIL)
                .chineseLangValue("富勒烯")
                .components(Carbon, 60)
                .buildAndRegister();
        //region Ziegler-Natta Catalyst
        WetDibutylMagnesium = new GTTMaterialBuilder(GTTCore.id("wet_dibutyl_magnesium"))
                .fluid()
                .color(0xbbbb71)
                .chineseLangValue("湿二丁基镁")
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 18, Magnesium, 1)
                .buildAndRegister();
        DibutylMagnesium = new GTTMaterialBuilder(GTTCore.id("dibutyl_magnesium"))
                .fluid()
                .color(0xdddd91)
                .chineseLangValue("二丁基镁")
                .components(Carbon, 8, Hydrogen, 18, Magnesium, 1)
                .buildAndRegister();
        Bromobutane = new GTTMaterialBuilder(GTTCore.id("bromobutane"))
                .fluid()
                .color(0xdddd91)
                .chineseLangValue("溴丁烷")
                .components(Carbon, 4, Hydrogen, 9, Bromine, 1)
                .buildAndRegister();
        SodiumBromide = new GTTMaterialBuilder(GTTCore.id("sodium_bromide"))
                .dust()
                .color(0xcbd2db)
                .chineseLangValue("溴化钠")
                .components(Sodium, 1, Bromine, 1)
                .buildAndRegister();
        Butanol = new GTTMaterialBuilder(GTTCore.id("butanol"))
                .fluid()
                .color(0x669999)
                .chineseLangValue("丁醇")
                .components(Carbon, 4, Hydrogen, 9, Oxygen, 1, Hydrogen, 1)
                .buildAndRegister();
        CultivateProducts = new GTTMaterialBuilder(GTTCore.id("cultivate_products"))
                .fluid()
                .color(0x775533)
                .chineseLangValue("培养产物")
                .buildAndRegister();
        SodiumSulfate = new GTTMaterialBuilder(GTTCore.id("sodium_sulfate"))
                .dust()
                .color(0xDDDDDD)
                .chineseLangValue("硫酸钠")
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .buildAndRegister();
        Octanol = new GTTMaterialBuilder(GTTCore.id("octanol"))
                .fluid()
                .color(0x997766)
                .chineseLangValue("辛醇")
                .components(Carbon, 8, Hydrogen, 18, Oxygen, 1)
                .buildAndRegister();
        OctanolMagnesium = new GTTMaterialBuilder(GTTCore.id("octanol_magnesium"))
                .dust()
                .color(0x993366)
                .chineseLangValue("辛醇镁")
                .components(Magnesium, 1, Octanol, 2)
                .buildAndRegister();
        PhosphorusTrichloride = new GTTMaterialBuilder(GTTCore.id("phosphorus_trichloride"))
                .fluid()
                .color(0xDDDD11)
                .chineseLangValue("三氯化磷")
                .components(Phosphorus, 1, Chlorine, 3)
                .buildAndRegister();
        PhosphorusTrioxide = new GTTMaterialBuilder(GTTCore.id("phosphorus_trioxide"))
                .dust()
                .color(0xdd895a)
                .chineseLangValue("三氧化二磷")
                .components(Phosphorus, 4, Oxygen, 6)
                .buildAndRegister();
        PhthaloylChlorine = new GTTMaterialBuilder(GTTCore.id("phthaloyl_chlorine"))
                .fluid()
                .color(0xDDDDDD)
                .chineseLangValue("邻苯二甲酰氯")
                .components(Carbon, 8, Hydrogen, 4, Chlorine, 2, Oxygen, 2)
                .buildAndRegister();
        DiisooctylPhthalate = new GTTMaterialBuilder(GTTCore.id("diisooctyl_phthalate"))
                .fluid()
                .color(0xDDDDDD)
                .chineseLangValue("邻苯二甲酸二异辛酯")
                .components(Carbon, 24, Hydrogen, 38, Oxygen, 4)
                .buildAndRegister();
        MicrocrystalMagnesiumChloride = new GTTMaterialBuilder(GTTCore.id("microcrystal_magnesium_chloride"))
                .dust()
                .color(0x442233)
                .chineseLangValue("微晶氯化镁")
                .components(Magnesium, 1, Chlorine, 2)
                .buildAndRegister();
        ZieglerNattaCatalyst = new GTTMaterialBuilder(GTTCore.id("ziegler_natta_catalyst"))
                .dust()
                .color(0x666666)
                .chineseLangValue("Ziegler-Natta 催化剂")
                .components(TitaniumTetrachloride, 1, MagnesiumChloride, 1)
                .buildAndRegister();
        //endregion
        Zircon = new GTTMaterialBuilder(GTTCore.id("zircon"))
                .gem()
                .color(0x93ff90)
                .chineseLangValue("锆石")
                .ore(2, 1)
                .components(Zirconium, 1, Oxygen, 2, SiliconDioxide, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
        ZirconiumCarbide = new GTTMaterialBuilder(GTTCore.id("zirconium_carbide"))
                .ingot()
                .color(0x2f1110)
                .chineseLangValue("碳化锆")
                .components(Zirconium, 1, Carbon, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
        ZirconiumTetrachloride = new GTTMaterialBuilder(GTTCore.id("zirconium_tetrachloride"))
                .dust()
                .color(0x229922)
                .chineseLangValue("四氯化锆")
                .components(Zirconium, 1, Chlorine, 4)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
        DepletedUranium238 = new GTTMaterialBuilder(GTTCore.id("depleted_uranium"))
                .ingot(2)
                .color(0x1d891d).secondaryColor(0x33342c).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_ROD)
                .element(GTElements.U238)
                .chineseLangValue("枯竭铀")
                .radioactiveHazard(1)
                .buildAndRegister();
        DepletedPlutonium239 = new GTTMaterialBuilder(GTTCore.id("depleted_plutonium"))
                .ingot(2)
                .color(0xba2727).secondaryColor(0x33342c).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_ROD)
                .element(GTElements.Pu239)
                .chineseLangValue("枯竭钚")
                .radioactiveHazard(1)
                .buildAndRegister();
        DepletedThorium = new GTTMaterialBuilder(GTTCore.id("depleted_thorium"))
                .ingot(2)
                .color(0x25411b).secondaryColor(0x051E05).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_ROD)
                .element(GTElements.Th)
                .chineseLangValue("枯竭钍")
                .radioactiveHazard(1)
                .buildAndRegister();
        SupercriticalSteam = new GTTMaterialBuilder(GTTCore.id("supercritical_steam"))
                .gas(new FluidBuilder()
                        .state(FluidState.GAS)
                        .temperature(10000))
                .color(0xEEEEEE)
                .chineseLangValue("超临界蒸汽")
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Oxygen, 1)
                .buildAndRegister();
        AtomicSteel = new GTTMaterialBuilder(GTTCore.id("atomic_steel"))
                .color(0x262560)
                .ingot()
                .chineseLangValue("原子钢")
                .components(Trinium, 5, Iron, 3, Zirconium, 2, Naquadah, 3)
                .flags(GENERATE_FRAME, GENERATE_PLATE)
                .blast(7200, BlastProperty.GasTier.HIGHER)
                .buildAndRegister();
        //region Platinum Group Line
        PlatinumGroupSolution = new GTTMaterialBuilder(GTTCore.id("platinum_group_solution"))
                .color(0x333354)
                .liquid()
                .chineseLangValue("铂系溶液")
                .buildAndRegister();
        AmmoniumHexachloroplatinate = new GTTMaterialBuilder(GTTCore.id("ammonium_hexachloroplatinate"))
                .color(0xd2d580)
                .dust()
                .components(Nitrogen, 2, Hydrogen, 8, Platinum, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .chineseLangValue("六氯铂酸铵")
                .buildAndRegister()
                .setFormula("(NH4)2PtCl6", true);
        PlatinumPalladiumSolution = new GTTMaterialBuilder(GTTCore.id("platinum_palladium_solution"))
                .color(0x545433)
                .liquid()
                .chineseLangValue("铂钯溶液")
                .buildAndRegister();
        PalladiumSolution = new GTTMaterialBuilder(GTTCore.id("palladium_solution"))
                .color(0x543333)
                .liquid()
                .chineseLangValue("钯溶液")
                .buildAndRegister();
        Diamminedichloropalladium = new GTTMaterialBuilder(GTTCore.id("diamminedichloropalladium"))
                .color(0x844533)
                .dust()
                .chineseLangValue("二氯二氨钯")
                .components(Chlorine, 2, Hydrogen, 6, Nitrogen, 2, Palladium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("PdCl2(NH3)2", true);
        InertMetalSodiumBisulfate = new GTTMaterialBuilder(GTTCore.id("inert_metal_sodium_bisulfate"))
                .color(0x756565)
                .langValue("Inert Metal Sodium Bisulfate Eutectic")
                .chineseLangValue("惰性金属硫酸氢钠共熔物")
                .dust()
                .buildAndRegister();
        RhodiumHydroxide = new GTTMaterialBuilder(GTTCore.id("rhodium_hydroxide"))
                .color(0x888899)
                .dust()
                .chineseLangValue("氢氧化铑")
                .components(Rhodium, 1, Oxygen, 3, Hydrogen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        ChlororhodicAcid = new GTTMaterialBuilder(GTTCore.id("chlororhodic_acid"))
                .color(0x700070)
                .liquid()
                .chineseLangValue("氯铑酸")
                .components(Hydrogen, 3, Rhodium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AmmoniumHexachlororhodate = new GTTMaterialBuilder(GTTCore.id("ammonium_hexachlororhodate"))
                .color(0xd42030)
                .dust()
                .chineseLangValue("氯铑酸铵")
                .components(Nitrogen, 3, Hydrogen, 12, Chlorine, 6, Rhodium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)3RhCl6", true);
        AmmoniumHexanitrorhodium = new GTTMaterialBuilder(GTTCore.id("ammonium_hexanitrorhodium"))
                .color(0xf0f0b0)
                .dust()
                .chineseLangValue("六硝基铑铵")
                .components(Nitrogen, 8 ,Hydrogen, 8, Rhodium, 1, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)2(Rh(NO2)6)");
        SodiumPeroxide = new GTTMaterialBuilder(GTTCore.id("sodium_peroxide"))
                .color(0xf0f0b0)
                .dust()
                .chineseLangValue("过氧化钠")
                .components(Sodium, 2, Oxygen, 2)
                .buildAndRegister();
        RutheniumOsmiumIridiumMixture = new GTTMaterialBuilder(GTTCore.id("ruthenium_osmium_iridium_mixture"))
                .color(0xa0a0b0)
                .dust()
                .chineseLangValue("钌锇铱混合物")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumOsmiumIridiumMixtureSodiumPeroxide = new GTTMaterialBuilder(GTTCore.id("ruthenium_osmium_iridium_mixture_sodium_peroxide"))
                .color(0xb0b080)
                .dust()
                .langValue("Ruthenium Osmium Iridium Sodium Peroxide Eutectic")
                .chineseLangValue("钌锇铱混合物过氧化钠共熔物")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumOsmiumSaltSolution = new GTTMaterialBuilder(GTTCore.id("ruthenium_osmium_salt_solution"))
                .color(0x202050)
                .liquid()
                .chineseLangValue("钌锇盐溶液")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        IridiumOxide = new GTTMaterialBuilder(GTTCore.id("iridium_oxide"))
                .color(0xa9a989)
                .dust()
                .chineseLangValue("二氧化铱")
                .components(Iridium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AmmoniumHexachloroiridate = new GTTMaterialBuilder(GTTCore.id("ammonium_hexachloroiridate"))
                .color(0x89a9a9)
                .dust()
                .chineseLangValue("氯铱酸铵")
                .components(Nitrogen, 3, Hydrogen, 12, Iridium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)3IrCl6");
        RutheniumOsmiumOxide = new GTTMaterialBuilder(GTTCore.id("ruthenium_osmium_oxide"))
                .color(0xa9b9c9)
                .dust()
                .chineseLangValue("钌锇氧化物")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumOxide = new GTTMaterialBuilder(GTTCore.id("ruthenium_oxide"))
                .color(0xb9c9a9)
                .dust()
                .chineseLangValue("二氧化钌")
                .flags(DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Oxygen, 2)
                .buildAndRegister();
        RutheniumSolution = new GTTMaterialBuilder(GTTCore.id("ruthenium_solution"))
                .color(0xa9c9f9)
                .liquid()
                .chineseLangValue("钌溶液")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        OsmiumSolution = new GTTMaterialBuilder(GTTCore.id("osmium_solution"))
                .color(0xa9f9e9)
                .liquid()
                .chineseLangValue("锇溶液")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AmmoniumHexachlororuthenate = new GTTMaterialBuilder(GTTCore.id("ammonium_hexachlororuthenate"))
                .color(0xf9c9e9)
                .dust()
                .chineseLangValue("氯钌酸铵")
                .components(Nitrogen, 3, Hydrogen, 12, Ruthenium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("(NH4)3RuCl6");
        Tetraamminedioxidoosmiumdichloride = new GTTMaterialBuilder(GTTCore.id("tetraamminedioxidoosmiumdichloride"))
                .color(0xc9f9e9)
                .dust()
                .chineseLangValue("二氯四氨锇")
                .components(Osmium, 1, Chlorine, 2, Oxygen, 2, Nitrogen, 4, Hydrogen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("OsCl2O2(NH3)4");
        //endregion
        AndesiteAlloy = new GTTMaterialBuilder(GTTCore.id("andesite_alloy"))
                .color(0xe0f0e9).secondaryColor(0x596059)
                .ingot()
                .dust()
                .liquid()
                .chineseLangValue("安山合金")
                .components(Iron, 1, Andesite, 1)
                .flags(GENERATE_PLATE, GENERATE_ROD)
                .buildAndRegister();
        RawBeer = new GTTMaterialBuilder(GTTCore.id("raw_beer"))
                .color(0xc9c979).secondaryColor(0xa9a969)
                .liquid()
                .chineseLangValue("粗啤酒")
                .buildAndRegister();
        Beer = new GTTMaterialBuilder(GTTCore.id("beer"))
                .color(0xe9e979).secondaryColor(0xa9a969)
                .chineseLangValue("啤酒")
                .liquid()
                .buildAndRegister();

        //region Brine Line
        AcidicBromineSolution = new GTTMaterialBuilder(GTTCore.id("acidic_bromine_solution"))
                .liquid()
                .color(0xc49b52)
                .chineseLangValue("酸性溴溶液")
                .components(Chlorine, 1, Bromine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        ConcentratedBromineSolution = new GTTMaterialBuilder(GTTCore.id("concentrated_bromine_solution"))
                .liquid()
                .color(0x91481e)
                .chineseLangValue("浓溴溶液")
                .components(Bromine, 2, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HydrogenIodide = new GTTMaterialBuilder(GTTCore.id("hydrogen_iodide"))
                .gas()
                .color(0x8187a6)
                .chineseLangValue("氢化碘")
                .components(Hydrogen, 1, Iodine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        HotBrine = new GTTMaterialBuilder(GTTCore.id("hot_brine"))
                .liquid(320)
                .color(0xbe6026)
                .chineseLangValue("热卤水")
                .buildAndRegister();

        HotChlorinatedBrominatedBrine = new GTTMaterialBuilder(GTTCore.id("hot_chlorinated_brominated_brine"))
                .liquid(320)
                .color(0xab765d)
                .chineseLangValue("热氯化溴卤水")
                .components(HotBrine, 1, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HotDebrominatedBrine = new GTTMaterialBuilder(GTTCore.id("hot_debrominated_brine"))
                .liquid(320)
                .color(0xab896d)
                .chineseLangValue("热脱溴卤水")
                .buildAndRegister();

        HotAlkalineDebrominatedBrine = new GTTMaterialBuilder(GTTCore.id("hot_alkaline_debrominated_brine"))
                .liquid(320)
                .color(0xbe8938)
                .chineseLangValue("热碱性脱溴卤水")
                .components(HotDebrominatedBrine, 2, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RawBrine = new GTTMaterialBuilder(GTTCore.id("raw_brine"))
                .liquid()
                .chineseLangValue("粗卤水")
                .color(0x9f6b26)
                .buildAndRegister();

        DebrominatedBrine = new GTTMaterialBuilder(GTTCore.id("debrominated_brine"))
                .liquid()
                .color(0xab8c6d)
                .chineseLangValue("脱溴卤水")
                .buildAndRegister();

        BrominatedChlorineVapor = new GTTMaterialBuilder(GTTCore.id("brominated_chlorine_vapor"))
                .gas()
                .color(0xbb9b72)
                .chineseLangValue("溴化氯蒸汽")
                .components(Chlorine, 1, Bromine, 1, Steam, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        AcidicBromineExhaust = new GTTMaterialBuilder(GTTCore.id("acidic_bromine_exhaust"))
                .gas()
                .color(0x8f681e)
                .chineseLangValue("酸性溴废气")
                .components(Steam, 3, Chlorine, 1)
                .buildAndRegister();
        //endregion brine

        PhenolicResin = new GTTMaterialBuilder(GTTCore.id("phenolic_resin"))
                .color(0x6f381e)
                .liquid().polymer()
                .chineseLangValue("酚醛树脂")
                .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
                .buildAndRegister();

        //region Lanthanide Group Line
        SodiumSulfite = new GTTMaterialBuilder(GTTCore.id("sodium_sulfite"))
                .dust()
                .chineseLangValue("亚硫酸钠")
                .components(Sodium, 2, Sulfur, 1, Oxygen, 3)
                .buildAndRegister();
        OxalicAcid = new GTTMaterialBuilder(GTTCore.id("oxalic_acid"))
                .color(0xa7ffd1)
                .liquid()
                .chineseLangValue("乙二酸")
                .components(Hydrogen, 2, Carbon, 2, Oxygen, 4)
                .buildAndRegister();
        EthyleneGlycol = new GTTMaterialBuilder(GTTCore.id("ethylene_glycol"))
                .color(0xffc3b3)
                .liquid()
                .chineseLangValue("乙二醇")
                .components(Hydrogen, 2, Carbon, 2, Oxygen, 4)
                .buildAndRegister();
        EthyleneOxide = new GTTMaterialBuilder(GTTCore.id("ethylene_oxide"))
                .color(0xc8d1ff)
                .liquid()
                .chineseLangValue("环氧乙烷")
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .buildAndRegister();
        RoastedRareEarth = new GTTMaterialBuilder(GTTCore.id("roasted_rare_earth"))
                .dust()
                .chineseLangValue("焙烧稀土")
                .color(0xc6ab67).secondaryColor(0xba775d)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .buildAndRegister();
        LowCeriumContentRareEarthChlorideSolution = new GTTMaterialBuilder(GTTCore.id("low_cerium_content_rare_earth_chloride_solution"))
                .color(0xa9da72)
                .chineseLangValue("低铈稀土氯化物溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Chlorine, 1)
                .buildAndRegister();
        CeriumSolution = new GTTMaterialBuilder(GTTCore.id("cerium_solution"))
                .color(0xd9484f)
                .chineseLangValue("铈溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        DefluorinatedCeriumSolution = new GTTMaterialBuilder(GTTCore.id("defluorinated_cerium_solution"))
                .color(0xd96d4f)
                .chineseLangValue("去氟铈溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        OxidizedCeriumSolution = new GTTMaterialBuilder(GTTCore.id("oxidized_cerium_solution"))
                .color(0xd98c52)
                .chineseLangValue("氧化铈溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RawCeriumOxide = new GTTMaterialBuilder(GTTCore.id("raw_cerium_oxide"))
                .color(0xb6932c)
                .dust()
                .chineseLangValue("粗氧化铈")
                .flags(DISABLE_DECOMPOSITION)
                .components(Cerium, 1, Oxygen, 2)
                .buildAndRegister();
        CeriumOxide = new GTTMaterialBuilder(GTTCore.id("cerium_oxide"))
                .color(0xe8dd3c)
                .chineseLangValue("氧化铈")
                .dust()
                .components(Cerium, 1, Oxygen, 2)
                .buildAndRegister();
        CeriumWaste = new GTTMaterialBuilder(GTTCore.id("cerium_waste"))
                .color(0x494d6e)
                .chineseLangValue("铈废液")
                .liquid()
                .components()
                .buildAndRegister();
        CeriumContainingResidue = new GTTMaterialBuilder(GTTCore.id("cerium_containing_residue"))
                .color(0x6e4047)
                .chineseLangValue("含铈残渣")
                .dust()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        PurifiedRareEarthChlorideSolution = new GTTMaterialBuilder(GTTCore.id("purified_rare_earth_chloride_solution"))
                .color(0x6fd983)
                .chineseLangValue("净化稀土溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        HeavyMetalsRemovedRareEarthChlorideSolution = new GTTMaterialBuilder(GTTCore.id("heavy_metals_removed_rare_earth_chloride_solution"))
                .color(0x87ff9b)
                .chineseLangValue("去重金属稀土溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AluminiumChloride = new GTTMaterialBuilder(GTTCore.id("aluminum_chloride"))
                .color(0x74caff)
                .chineseLangValue("三氯化铝")
                .dust()
                .components(Aluminium, 1, Chlorine, 3)
                .buildAndRegister();
        AluminiumFluoride = new GTTMaterialBuilder(GTTCore.id("aluminium_fluoride"))
                .color(0xd698ff)
                .chineseLangValue("三氟化铝")
                .dust()
                .components(Aluminium, 1, Fluorine, 3)
                .buildAndRegister();
        PotassiumManganate = new GTTMaterialBuilder(GTTCore.id("potassium_manganate"))
                .color(0xffb5b2)
                .chineseLangValue("锰酸钾")
                .dust()
                .components(Potassium, 2, Manganese, 1, Oxygen, 4)
                .buildAndRegister();
        PotassiumPermanganate = new GTTMaterialBuilder(GTTCore.id("potassium_permanganate"))
                .color(0xa442b3)
                .chineseLangValue("高锰酸钾")
                .dust()
                .components(Potassium, 1, Manganese, 1, Oxygen, 4)
                .buildAndRegister();
        SodiumChlorate = new GTTMaterialBuilder(GTTCore.id("sodium_chlorate"))
                .color(0xa0b300)
                .chineseLangValue("氯酸钠")
                .dust()
                .components(Sodium, 1, Chlorine, 1, Oxygen, 3)
                .buildAndRegister();
        IronHydroxide = new GTTMaterialBuilder(GTTCore.id("iron_hydroxide"))
                .color(0xcf7c64)
                .chineseLangValue("氢氧化铁")
                .dust()
                .components(Iron, 1, Hydrogen, 3, Oxygen, 3)
                .buildAndRegister()
                .setFormula("Fe(OH)3");
        LeadHydroxide = new GTTMaterialBuilder(GTTCore.id("lead_hydroxide"))
                .color(0x817ccf)
                .chineseLangValue("氢氧化铅")
                .dust()
                .components(Lead, 1, Hydrogen, 2, Oxygen, 2)
                .buildAndRegister()
                .setFormula("Pb(OH)2");
        ThoriumHydroxide = new GTTMaterialBuilder(GTTCore.id("thorium_hydroxide"))
                .color(0x578c74)
                .chineseLangValue("氢氧化钍")
                .dust()
                .components(Thorium, 1, Hydrogen, 4, Oxygen, 4)
                .buildAndRegister()
                .setFormula("Th(OH)4");
        AluminiumHydroxide = new GTTMaterialBuilder(GTTCore.id("aluminium_hydroxide"))
                .color(0xbbc353)
                .chineseLangValue("氢氧化铝")
                .dust()
                .components(Aluminium, 1, Hydrogen, 3, Oxygen, 3)
                .buildAndRegister()
                .setFormula("Th(OH)3");
        CopperSulfide = new GTTMaterialBuilder(GTTCore.id("copper_sulfide"))
                .color(0xc27500)
                .chineseLangValue("硫化铜")
                .dust()
                .components(Copper, 1, Sulfur, 1)
                .buildAndRegister();
        LeadSulfide = new GTTMaterialBuilder(GTTCore.id("lead_sulfide"))
                .color(0xc281bd)
                .chineseLangValue("硫化铅")
                .dust()
                .components(Lead, 1, Sulfur, 1)
                .buildAndRegister();
        RawP507 = new GTTMaterialBuilder(GTTCore.id("raw_p_507"))
                .color(0xaea34e)
                .chineseLangValue("粗P-507")
                .liquid()
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 3, Phosphorus, 1)
                .buildAndRegister();
        P507 = new GTTMaterialBuilder(GTTCore.id("p_507"))
                .color(0xd4c75f)
                .liquid()
                .langValue("2-Ethylhexylphosphoric Acid Mono-2-ethylhexyl Ester (P-507)")
                .chineseLangValue("2-乙基己基膦酸单2-乙基己基酯（P-507）")
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 3, Phosphorus, 1)
                .buildAndRegister();
        P204 = new GTTMaterialBuilder(GTTCore.id("p_204"))
                .color(0xb68cd4)
                .liquid()
                .langValue("2-Ethylhexylphosphoric Acid Mono-2-ethylhexyl Ester (P-507)")
                .chineseLangValue("二（2-乙基己基）磷酸酯（P-204）")
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
                .buildAndRegister();
        Bis2EthylhexylPhosphite = new GTTMaterialBuilder(GTTCore.id("bis_2_ethylhexyl_phosphite"))
                .color(0x600200)
                .liquid()
                .chineseLangValue("二（2-乙基己基）亚磷酸酯")
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 3, Phosphorus, 1)
                .buildAndRegister();
        Chlorooctane = new GTTMaterialBuilder(GTTCore.id("chlorooctane"))
                .color(0x485e00)
                .liquid()
                .chineseLangValue("氯辛烷")
                .components(Carbon, 8, Hydrogen, 17, Chlorine, 1)
                .buildAndRegister();
        PhosphorusOxychloride = new GTTMaterialBuilder(GTTCore.id("phosphorus_oxychloride"))
                .color(0x49c09e)
                .liquid()
                .chineseLangValue("三氯氧磷")
                .components(Phosphorus, 1, Oxygen, 1, Chlorine, 3)
                .buildAndRegister();
        Di2EthylhexylPhosphorylChloride = new GTTMaterialBuilder(GTTCore.id("di_2_ethylhexyl_phosphoryl_chloride"))
                .color(0x5c69bf)
                .liquid()
                .chineseLangValue("二（2-乙基己基）磷酰氯")
                .components(Carbon, 16, Hydrogen, 34, Oxygen, 3, Phosphorus, 1, Chlorine, 1)
                .buildAndRegister();
        HeavyRareEarthSolution = new GTTMaterialBuilder(GTTCore.id("heavy_rare_earth_solution"))
                .color(0xb4ff86)
                .chineseLangValue("重稀土溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        MediumRareEarthSolution = new GTTMaterialBuilder(GTTCore.id("medium_rare_earth_solution"))
                .color(0x74ffa6)
                .chineseLangValue("中稀土溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LightRareEarthSolution = new GTTMaterialBuilder(GTTCore.id("light_rare_earth_solution"))
                .color(0x87ffeb)
                .chineseLangValue("轻稀土溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();


        LanthanumSolution = new GTTMaterialBuilder(GTTCore.id("lanthanum_solution"))
                .color(0xd17d50)
                .liquid()
                .chineseLangValue("镧溶液")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LanthanumOxalateHydrate = new GTTMaterialBuilder(GTTCore.id("lanthanum_oxalate_hydrate"))
                .color(0xd17d50)
                .dust()
                .chineseLangValue("草酸镧")
                .components(Lanthanum, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        NeodymiumSolution = new GTTMaterialBuilder(GTTCore.id("neodymium_solution"))
                .color(0x6c5863)
                .liquid()
                .chineseLangValue("钕溶液")
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        NeodymiumOxalateHydrate = new GTTMaterialBuilder(GTTCore.id("neodymium_oxalate_hydrate"))
                .color(0x6c5863)
                .dust()
                .chineseLangValue("草酸钕")
                .components(Neodymium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        SamariumSolution = new GTTMaterialBuilder(GTTCore.id("samarium_solution"))
                .color(0xc2c289)
                .chineseLangValue("钐溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        SamariumOxalateHydrate = new GTTMaterialBuilder(GTTCore.id("samarium_oxalate_hydrate"))
                .color(0xc2c289)
                .chineseLangValue("草酸钐")
                .dust()
                .components(Samarium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        EuropiumSolution = new GTTMaterialBuilder(GTTCore.id("Europium_solution"))
                .color(0x20FFFF)
                .chineseLangValue("铕溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        EuropiumOxalateHydrate = new GTTMaterialBuilder(GTTCore.id("europium_oxalate_hydrate"))
                .color(0x20FFFF)
                .chineseLangValue("草酸铕")
                .dust()
                .components(Europium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        LutetiumSolution = new GTTMaterialBuilder(GTTCore.id("lutetium_solution"))
                .color(0x00ccff)
                .chineseLangValue("镥溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LutetiumOxalateHydrate = new GTTMaterialBuilder(GTTCore.id("lutetium_oxalate_hydrate"))
                .color(0x00ccff)
                .chineseLangValue("草酸镥")
                .dust()
                .components(Lutetium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        YttriumSolution = new GTTMaterialBuilder(GTTCore.id("yttrium_solution"))
                .color(0x7d8072)
                .chineseLangValue("钇溶液")
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        YttriumOxalateHydrate = new GTTMaterialBuilder(GTTCore.id("yttrium_oxalate_hydrate"))
                .color(0x7d8072)
                .chineseLangValue("草酸钇")
                .dust()
                .components(Yttrium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        Xenotime = new GTTMaterialBuilder(GTTCore.id("xenotime"))
                .dust().ore(2, 1)
                .chineseLangValue("磷钇矿")
                .color(0x444b2c).secondaryColor(0x867e43).iconSet(FINE)
                .components(RareEarth, 1, Phosphate, 1)
                .buildAndRegister();
        OreProperty oreProp = Xenotime.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Phosphate, RareEarth);
        oreProp.setSeparatedInto(RareEarth);



        //endregion

        VenusAir = new GTTMaterialBuilder(GTTCore.id("venus_air"))
                .gas()
                .chineseLangValue("金星空气")
                .flags(DISABLE_DECOMPOSITION)
                .color(0x737300)
                .components(SulfurTrioxide, 68, Argon, 10)
                .buildAndRegister();
        LiquidVenusAir = new GTTMaterialBuilder(GTTCore.id("liquid_venus_air"))
                .liquid(new FluidBuilder().temperature(58))
                .flags(DISABLE_DECOMPOSITION)
                .chineseLangValue("液态金星空气")
                .components(SulfurTrioxide, 78, Argon, 10, Methane, 7, Ethenone, 5, Tetrafluoroethylene, 5)
                .color(0xacac00)
                .buildAndRegister();

        Inconel718 = new GTTMaterialBuilder(GTTCore.id("inconel_718"))
                .langValue("Inconel 718")
                .ingot(5).fluid()
                .chineseLangValue("镍基合金-718")
                .color(0x3d3e4a).iconSet(METALLIC)
                .appendFlags(STD_METAL, GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Nickel, 25, Chromium, 10, Molybdenum, 1, Niobium, 2, Iron, 12)
                .blast(b -> b.temp(4600, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[EV], 800))
                .buildAndRegister();

        if (GTCEu.Mods.isCreateLoaded()) ingot.setIgnored(AndesiteAlloy, (Supplier<? extends ItemLike>) AllItems.ANDESITE_ALLOY);
        ingot.setIgnored(Desh, ModItems.DESH_INGOT);
        ingot.setIgnored(Ostrum, ModItems.OSTRUM_INGOT);
        ingot.setIgnored(Calorite, ModItems.CALORITE_INGOT);
        block.setIgnored(Desh, ModItems.DESH_BLOCK);
        block.setIgnored(Ostrum, ModItems.OSTRUM_BLOCK);
        block.setIgnored(Calorite, ModItems.CALORITE_BLOCK);
        nugget.setIgnored(Desh, ModItems.DESH_NUGGET);
        nugget.setIgnored(Ostrum, ModItems.OSTRUM_NUGGET);
        nugget.setIgnored(Calorite, ModItems.CALORITE_NUGGET);
        rawOreBlock.setIgnored(Desh, ModItems.RAW_DESH_BLOCK);
        rawOreBlock.setIgnored(Ostrum, ModItems.RAW_OSTRUM_BLOCK);
        rawOreBlock.setIgnored(Calorite, ModItems.RAW_CALORITE_BLOCK);
        rawOre.setIgnored(Desh, ModItems.RAW_DESH);
        rawOre.setIgnored(Ostrum, ModItems.RAW_OSTRUM);
        rawOre.setIgnored(Calorite, ModItems.RAW_CALORITE);
        gem.setIgnored(IceShard, ModItems.ICE_SHARD);
        gem.setIgnored(CertusQuartz, AEItems.CERTUS_QUARTZ_CRYSTAL);
        dust.setIgnored(CertusQuartz, AEItems.CERTUS_QUARTZ_DUST);
        block.setIgnored(CertusQuartz, AEBlocks.QUARTZ_BLOCK);
        gem.setIgnored(FluixCrystal, AEItems.FLUIX_CRYSTAL);
        dust.setIgnored(FluixCrystal, AEItems.FLUIX_DUST);
        block.setIgnored(FluixCrystal, AEBlocks.FLUIX_BLOCK);

        block.modifyMaterialAmount(FluixCrystal, 4);

        block.setIgnored(MoonStone, ModItems.MOON_STONE);
        block.setIgnored(VenusStone, ModItems.VENUS_STONE);
        block.setIgnored(MarsStone, ModItems.MARS_STONE);
        block.setIgnored(MercuryStone, ModItems.MERCURY_STONE);
        block.setIgnored(GlacioStone, ModItems.GLACIO_STONE);
        block.setIgnored(InfernalSpire, ModItems.INFERNAL_SPIRE_BLOCK);
    }
    public static void modify() {
        WroughtIron.addFlags(GENERATE_SMALL_GEAR);

        Netherite.addFlags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_BOLT_SCREW, GENERATE_GEAR);
        Plutonium239.addFlags(MaterialFlags.GENERATE_ROD);

        Zirconium.setMaterialARGB(0x7799a9);
        Zirconium.setMaterialSecondaryARGB(0x6489a9);
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Zirconium.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));
        Zirconium.addFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_FRAME);
        Zirconium.setProperty(PropertyKey.FLUID_PIPE, new FluidPipeProperties(2000, 800, true, true, true, false));
        Zirconium.setProperty(PropertyKey.BLAST, (new BlastProperty.Builder().temp(1941, BlastProperty.GasTier.MID)
                .blastStats(VA[EV], 1500)
                .vacuumStats(VA[EV])).build());
        NaquadahAlloy.setProperty(PropertyKey.FLUID_PIPE, new FluidPipeProperties(12000, 2300, true, true, true, true));
        Gallium.setProperty(PropertyKey.ORE, new OreProperty(1, 1));
        Tantalum.setProperty(PropertyKey.ORE, new OreProperty(1, 1));


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

        Neodymium.removeProperty(PropertyKey.ORE);
        Bastnasite.setComponents(new MaterialStack(Carbon, 1), new MaterialStack(Fluorine, 1), new MaterialStack(Oxygen, 3), new MaterialStack(RareEarth, 1));
        Bastnasite.setFormula("?CFO3");
        OreProperty oreProp = Bastnasite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Carbon, RareEarth);
        oreProp.setSeparatedInto(RareEarth);
        oreProp = Monazite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Thorium, Phosphate, RareEarth);
        oreProp.setSeparatedInto(RareEarth);

        Glowstone.setProperty(PropertyKey.ORE, new OreProperty(5, 1));
        Glowstone.setComponents(new MaterialStack(Boron, 8), new MaterialStack(Argon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Uranium238, 2));
        Glowstone.setFormula("B8F2ArU2");
    }
}
