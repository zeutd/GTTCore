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
import com.simibubi.create.AllItems;
import earth.terrarium.adastra.common.registry.ModItems;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gtt.gttcore.common.data.GTTElements.*;

public class GTTMaterials {
    public static Material[] andesiteAlloyIngredient = new Material[] {
            Pyrite,
            Magnetite,
            YellowLimonite,
            Goethite,
            Hematite
    };
    public static Material
            FluixCrystal,
            RawBeer, Beer,
            AndesiteAlloy,
            Desh, Ostrum, Calorite, IceShard,
            UltraHighMolecularWeightPolyethylene, Fullerene,
            WetDibutylMagnesium, DibutylMagnesium, Bromobutane, SodiumBromide, Butanol, CultivateProducts, SodiumSulfate, Octanol, OctanolMagnesium, PhosphorusTrichloride, PhthaloylChlorine, DiisooctylPhthalate, MicrocrystalMagnesiumChloride, ZieglerNattaCatalyst,
            Zircon, ZirconiumCarbide, ZirconiumTetrachloride,

            DepletedUranium238, DepletedPlutonium239, DepletedThorium,

            SupercriticalSteam,
            AtomicSteel,

            PlatinumGroupSolution, PlatinumPalladiumSolution, AmmoniumHexachloroplatinate, PalladiumSolution, Diamminedichloropalladium, InertMetalSodiumBisulfate, RhodiumSulfateSolution, RhodiumHydroxide, ChlororhodicAcid, AmmoniumHexachlororhodate, AmmoniumHexanitrorhodium, SodiumPeroxide, RutheniumOsmiumIridiumMixture, RutheniumOsmiumIridiumMixtureSodiumPeroxide, RutheniumOsmiumSaltSolution, IridiumOxide, AmmoniumHexachloroiridate, RutheniumOsmiumOxide, RutheniumOxide, RutheniumSolution, OsmiumSolution, AmmoniumHexachlororuthenate, Tetraamminedioxidoosmiumdichloride,


            HotBrine, HotChlorinatedBrominatedBrine, HotDebrominatedBrine, HotAlkalineDebrominatedBrine, AcidicBromineSolution, ConcentratedBromineSolution, HydrogenIodide, RawBrine, DebrominatedBrine, BrominatedChlorineVapor, AcidicBromineExhaust,

            PhenolicResin,

            Xenotime,
            OxalicAcid, EthyleneGlycol, EthyleneOxide, SodiumSulfite, AluminiumChloride, AluminiumFluoride, PotassiumManganate, PotassiumPermanganate, SodiumChlorate,
            RoastedRareEarth, LowCeriumContentRareEarthChlorideSolution, CeriumContainingResidue, CeriumSolution, DefluorinatedCeriumSolution, OxidizedCeriumSolution, RawCeriumOxide, WashedCeriumOxide, CeriumOxide, CeriumWaste, PurifiedRareEarthChlorideSolution, HeavyMetalsRemovedRareEarthChlorideSolution, HeavyRareEarthSolution, MediumRareEarthSolution, LightRareEarthSolution,
            LanthanumSolution, LanthanumOxalateHydrate,
            NeodymiumSolution, NeodymiumOxalateHydrate,
            SamariumSolution, SamariumOxalateHydrate,
            EuropiumSolution, EuropiumOxalateHydrate,
            LutetiumSolution, LutetiumOxalateHydrate,
            YttriumSolution, YttriumOxalateHydrate,
            IronHydroxide, LeadHydroxide, ThoriumHydroxide, AluminiumHydroxide, CopperSulfide, LeadSulfide,
            P507, RawP507, P204, PhosphorusOxychloride, Di2EthylhexylPhosphorylChloride, Bis2EthylhexylPhosphite, Chlorooctane,


            VenusAir, LiquidVenusAir
                    ;
    public static void init() {
        FluixCrystal = new Material.Builder(GTTCore.id("fluix_crystal"))
                .gem()
                .dust()
                .color(0x995399).secondaryColor(0xb67eb7).iconSet(CERTUS)
                .appendFlags(EXT_METAL, NO_SMELTING, NO_SMASHING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(CertusQuartz, 1, Redstone, 1, NetherQuartz, 1)
                .buildAndRegister();
        Desh = new Material.Builder(GTTCore.id("desh"))
                .ingot()
                .dust()
                .liquid()
                .element(De)
                .ore()
                .color(0xDF5D07).secondaryColor(0xFF8D37).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_PLATE, GENERATE_ROD)
                .fluidPipeProperties(1900, 100, true, true, true, false)
                .buildAndRegister();
        Calorite = new Material.Builder(GTTCore.id("calorite"))
                .ingot()
                .dust()
                .liquid()
                .element(Ct)
                .ore()
                .color(0xD44267).secondaryColor(0xEE6688).iconSet(SHINY)
                .flags(MaterialFlags.GENERATE_PLATE, GENERATE_ROD)
                .fluidPipeProperties(2900, 200, true, true, true, true)
                .buildAndRegister();
        Ostrum = new Material.Builder(GTTCore.id("ostrum"))
                .ingot()
                .dust()
                .liquid()
                .element(Om)
                .ore()
                .color(0xA95675).secondaryColor(0xCB7799).iconSet(METALLIC)
                .flags(MaterialFlags.GENERATE_PLATE, GENERATE_ROD)
                .fluidPipeProperties(2400, 150, true, true, true, false)
                .buildAndRegister();
        IceShard = new Material.Builder(GTTCore.id("ice_shard"))
                .gem()
                .components(Nitrogen, 1, Hydrogen, 4, Nitrogen, 1, Oxygen, 3)
                .color(0xE0DEFF)
                .ore()
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
        //region Ziegler-Natta Catalyst
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
        //endregion
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
                .color(0x262560)
                .ingot()
                .components(Trinium, 5, Iron, 3, Zirconium, 2, Naquadah, 3)
                .flags(GENERATE_FRAME, GENERATE_PLATE)
                .blast(7200, BlastProperty.GasTier.HIGHER)
                .buildAndRegister();
        //region Platinum Group Line
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
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumOsmiumIridiumMixtureSodiumPeroxide = new Material.Builder(GTTCore.id("ruthenium_osmium_iridium_mixture_sodium_peroxide"))
                .color(0xb0b080)
                .dust()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumOsmiumSaltSolution = new Material.Builder(GTTCore.id("ruthenium_osmium_salt_solution"))
                .color(0x202050)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
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
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumOxide = new Material.Builder(GTTCore.id("ruthenium_oxide"))
                .color(0xb9c9a9)
                .dust()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RutheniumSolution = new Material.Builder(GTTCore.id("ruthenium_solution"))
                .color(0xa9c9f9)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        OsmiumSolution = new Material.Builder(GTTCore.id("osmium_solution"))
                .color(0xa9f9e9)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
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
        //endregion
        AndesiteAlloy = new Material.Builder(GTTCore.id("andesite_alloy"))
                .color(0xe0f0e9).secondaryColor(0x596059)
                .ingot()
                .dust()
                .liquid()
                .components(Iron, 1, Andesite, 1)
                .flags(GENERATE_PLATE, GENERATE_ROD)
                .buildAndRegister();
        RawBeer = new Material.Builder(GTTCore.id("raw_beer"))
                .color(0xc9c979).secondaryColor(0xa9a969)
                .liquid()
                .buildAndRegister();
        Beer = new Material.Builder(GTTCore.id("beer"))
                .color(0xe9e979).secondaryColor(0xa9a969)
                .liquid()
                .buildAndRegister();

        //region Brine Line
        AcidicBromineSolution = new Material.Builder(GTTCore.id("acidic_bromine_solution"))
                .liquid()
                .color(0xc49b52)
                .components(Chlorine, 1, Bromine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        ConcentratedBromineSolution = new Material.Builder(GTTCore.id("concentrated_bromine_solution"))
                .liquid()
                .color(0x91481e)
                .components(Bromine, 2, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HydrogenIodide = new Material.Builder(GTTCore.id("hydrogen_iodide"))
                .gas()
                .color(0x8187a6)
                .components(Hydrogen, 1, Iodine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        HotBrine = new Material.Builder(GTTCore.id("hot_brine"))
                .liquid(320)
                .color(0xbe6026)
                .buildAndRegister();

        HotChlorinatedBrominatedBrine = new Material.Builder(GTTCore.id("hot_chlorinated_brominated_brine"))
                .liquid(320)
                .color(0xab765d)
                .components(HotBrine, 1, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HotDebrominatedBrine = new Material.Builder(GTTCore.id("hot_debrominated_brine"))
                .liquid(320)
                .color(0xab896d)
                .buildAndRegister();

        HotAlkalineDebrominatedBrine = new Material.Builder(GTTCore.id("hot_alkaline_debrominated_brine"))
                .liquid(320)
                .color(0xbe8938)
                .components(HotDebrominatedBrine, 2, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RawBrine = new Material.Builder(GTTCore.id("raw_brine"))
                .liquid()
                .color(0x9f6b26)
                .buildAndRegister();

        DebrominatedBrine = new Material.Builder(GTTCore.id("debrominated_brine"))
                .liquid()
                .color(0xab8c6d)
                .buildAndRegister();

        BrominatedChlorineVapor = new Material.Builder(GTTCore.id("brominated_chlorine_vapor"))
                .gas()
                .color(0xbb9b72)
                .components(Chlorine, 1, Bromine, 1, Steam, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        AcidicBromineExhaust = new Material.Builder(GTTCore.id("acidic_bromine_exhaust"))
                .gas()
                .color(0x8f681e)
                .components(Steam, 3, Chlorine, 1)
                .buildAndRegister();
        //endregion brine

        PhenolicResin = new Material.Builder(GTTCore.id("phenolic_resin"))
                .color(0x6f381e)
                .liquid().polymer()
                .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
                .buildAndRegister();

        //region Lanthanide Group Line
        SodiumSulfite = new Material.Builder(GTTCore.id("sodium_sulfite"))
                .dust()
                .components(Sodium, 2, Sulfur, 1, Oxygen, 3)
                .buildAndRegister();
        OxalicAcid = new Material.Builder(GTTCore.id("oxalic_acid"))
                .color(0xa7ffd1)
                .liquid()
                .components(Hydrogen, 2, Carbon, 2, Oxygen, 4)
                .buildAndRegister();
        EthyleneGlycol = new Material.Builder(GTTCore.id("ethylene_glycol"))
                .color(0xffc3b3)
                .liquid()
                .components(Hydrogen, 2, Carbon, 2, Oxygen, 4)
                .buildAndRegister();
        EthyleneOxide = new Material.Builder(GTTCore.id("ethylene_oxide"))
                .color(0xc8d1ff)
                .liquid()
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .buildAndRegister();
        RoastedRareEarth = new Material.Builder(GTTCore.id("roasted_rare_earth"))
                .dust()
                .color(0xc6ab67).secondaryColor(0xba775d)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .buildAndRegister();
        LowCeriumContentRareEarthChlorideSolution = new Material.Builder(GTTCore.id("low_cerium_content_rare_earth_chloride_solution"))
                .color(0xa9da72)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Chlorine, 1)
                .buildAndRegister();
        CeriumSolution = new Material.Builder(GTTCore.id("cerium_solution"))
                .color(0xd9484f)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        DefluorinatedCeriumSolution = new Material.Builder(GTTCore.id("defluorinated_cerium_solution"))
                .color(0xd96d4f)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        OxidizedCeriumSolution = new Material.Builder(GTTCore.id("oxidized_cerium_solution"))
                .color(0xd98c52)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RawCeriumOxide = new Material.Builder(GTTCore.id("raw_cerium_oxide"))
                .color(0xb6932c)
                .dust()
                .flags(DISABLE_DECOMPOSITION)
                .components(Cerium, 1, Oxygen, 2)
                .buildAndRegister();
        CeriumOxide = new Material.Builder(GTTCore.id("cerium_oxide"))
                .color(0xe8dd3c)
                .dust()
                .components(Cerium, 1, Oxygen, 2)
                .buildAndRegister();
        CeriumWaste = new Material.Builder(GTTCore.id("cerium_waste"))
                .color(0x494d6e)
                .liquid()
                .components()
                .buildAndRegister();
        CeriumContainingResidue = new Material.Builder(GTTCore.id("cerium_containing_residue"))
                .color(0x6e4047)
                .dust()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        PurifiedRareEarthChlorideSolution = new Material.Builder(GTTCore.id("purified_rare_earth_chloride_solution"))
                .color(0x6fd983)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        HeavyMetalsRemovedRareEarthChlorideSolution = new Material.Builder(GTTCore.id("heavy_metals_removed_rare_earth_chloride_solution"))
                .color(0x87ff9b)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AluminiumChloride = new Material.Builder(GTTCore.id("aluminum_chloride"))
                .color(0x74caff)
                .dust()
                .components(Aluminium, 1, Chlorine, 3)
                .buildAndRegister();
        AluminiumFluoride = new Material.Builder(GTTCore.id("aluminium_fluoride"))
                .color(0xd698ff)
                .dust()
                .components(Aluminium, 1, Fluorine, 3)
                .buildAndRegister();
        PotassiumManganate = new Material.Builder(GTTCore.id("potassium_manganate"))
                .color(0xffb5b2)
                .dust()
                .components(Potassium, 2, Manganese, 1, Oxygen, 4)
                .buildAndRegister();
        PotassiumPermanganate = new Material.Builder(GTTCore.id("potassium_permanganate"))
                .color(0xa442b3)
                .dust()
                .components(Potassium, 1, Manganese, 1, Oxygen, 4)
                .buildAndRegister();
        SodiumChlorate = new Material.Builder(GTTCore.id("sodium_chlorate"))
                .color(0xa0b300)
                .dust()
                .components(Sodium, 1, Chlorine, 1, Oxygen, 3)
                .buildAndRegister();
        IronHydroxide = new Material.Builder(GTTCore.id("iron_hydroxide"))
                .color(0xcf7c64)
                .dust()
                .components(Iron, 1, Hydrogen, 3, Oxygen, 3)
                .buildAndRegister()
                .setFormula("Fe(OH)3");
        LeadHydroxide = new Material.Builder(GTTCore.id("lead_hydroxide"))
                .color(0x817ccf)
                .dust()
                .components(Lead, 1, Hydrogen, 2, Oxygen, 2)
                .buildAndRegister()
                .setFormula("Pb(OH)2");
        ThoriumHydroxide = new Material.Builder(GTTCore.id("thorium_hydroxide"))
                .color(0x578c74)
                .dust()
                .components(Thorium, 1, Hydrogen, 4, Oxygen, 4)
                .buildAndRegister()
                .setFormula("Th(OH)4");
        AluminiumHydroxide = new Material.Builder(GTTCore.id("aluminium_hydroxide"))
                .color(0xbbc353)
                .dust()
                .components(Aluminium, 1, Hydrogen, 3, Oxygen, 3)
                .buildAndRegister()
                .setFormula("Th(OH)3");
        CopperSulfide = new Material.Builder(GTTCore.id("copper_sulfide"))
                .color(0xc27500)
                .dust()
                .components(Copper, 1, Sulfur, 1)
                .buildAndRegister();
        LeadSulfide = new Material.Builder(GTTCore.id("lead_sulfide"))
                .color(0xc281bd)
                .dust()
                .components(Lead, 1, Sulfur, 1)
                .buildAndRegister();
        RawP507 = new Material.Builder(GTTCore.id("raw_p_507"))
                .color(0xaea34e)
                .liquid()
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 3, Phosphorus, 1)
                .buildAndRegister();
        P507 = new Material.Builder(GTTCore.id("p_507"))
                .color(0xd4c75f)
                .liquid()
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 3, Phosphorus, 1)
                .buildAndRegister();
        P204 = new Material.Builder(GTTCore.id("p_204"))
                .color(0xb68cd4)
                .liquid()
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
                .buildAndRegister();
        Bis2EthylhexylPhosphite = new Material.Builder(GTTCore.id("bis_2_ethylhexyl_phosphite"))
                .color(0x600200)
                .liquid()
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 3, Phosphorus, 1)
                .buildAndRegister();
        Chlorooctane = new Material.Builder(GTTCore.id("chlorooctane"))
                .color(0x485e00)
                .liquid()
                .components(Carbon, 8, Hydrogen, 17, Chlorine, 1)
                .buildAndRegister();
        PhosphorusOxychloride = new Material.Builder(GTTCore.id("phosphorus_oxychloride"))
                .color(0x49c09e)
                .liquid()
                .components(Phosphorus, 1, Oxygen, 1, Chlorine, 3)
                .buildAndRegister();
        Di2EthylhexylPhosphorylChloride = new Material.Builder(GTTCore.id("di_2_ethylhexyl_phosphoryl_chloride"))
                .color(0x5c69bf)
                .liquid()
                .components(Carbon, 16, Hydrogen, 34, Oxygen, 3, Phosphorus, 1, Chlorine, 1)
                .buildAndRegister();
        HeavyRareEarthSolution = new Material.Builder(GTTCore.id("heavy_rare_earth_solution"))
                .color(0xb4ff86)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        MediumRareEarthSolution = new Material.Builder(GTTCore.id("medium_rare_earth_solution"))
                .color(0x74ffa6)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LightRareEarthSolution = new Material.Builder(GTTCore.id("light_rare_earth_solution"))
                .color(0x87ffeb)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();


        LanthanumSolution = new Material.Builder(GTTCore.id("lanthanum_solution"))
               .color(0xd17d50)
               .liquid()
               .flags(DISABLE_DECOMPOSITION)
               .buildAndRegister();
        LanthanumOxalateHydrate = new Material.Builder(GTTCore.id("lanthanum_oxalate_hydrate"))
               .color(0xd17d50)
               .dust()
               .components(Lanthanum, 2, Carbon, 6, Oxygen, 12)
               .buildAndRegister();
        NeodymiumSolution = new Material.Builder(GTTCore.id("neodymium_solution"))
               .color(0x6c5863)
               .liquid()
               .flags(DISABLE_DECOMPOSITION)
               .buildAndRegister();
        NeodymiumOxalateHydrate = new Material.Builder(GTTCore.id("neodymium_oxalate_hydrate"))
               .color(0x6c5863)
               .dust()
               .components(Neodymium, 2, Carbon, 6, Oxygen, 12)
               .buildAndRegister();
        SamariumSolution = new Material.Builder(GTTCore.id("samarium_solution"))
               .color(0xc2c289)
               .liquid()
               .flags(DISABLE_DECOMPOSITION)
               .buildAndRegister();
        SamariumOxalateHydrate = new Material.Builder(GTTCore.id("samarium_oxalate_hydrate"))
                .color(0xc2c289)
                .dust()
                .components(Samarium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        EuropiumSolution = new Material.Builder(GTTCore.id("Europium_solution"))
                .color(0x20FFFF)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        EuropiumOxalateHydrate = new Material.Builder(GTTCore.id("europium_oxalate_hydrate"))
                .color(0x20FFFF)
                .dust()
                .components(Europium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        LutetiumSolution = new Material.Builder(GTTCore.id("lutetium_solution"))
                .color(0x00ccff)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        LutetiumOxalateHydrate = new Material.Builder(GTTCore.id("lutetium_oxalate_hydrate"))
                .color(0x00ccff)
                .dust()
                .components(Lutetium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        YttriumSolution = new Material.Builder(GTTCore.id("yttrium_solution"))
                .color(0x7d8072)
                .liquid()
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        YttriumOxalateHydrate = new Material.Builder(GTTCore.id("yttrium_oxalate_hydrate"))
                .color(0x7d8072)
                .dust()
                .components(Yttrium, 2, Carbon, 6, Oxygen, 12)
                .buildAndRegister();
        Xenotime = new Material.Builder(GTTCore.id("xenotime"))
                .dust().ore(2, 1)
                .color(0x444b2c).secondaryColor(0x867e43).iconSet(FINE)
                .components(RareEarth, 1, Phosphate, 1)
                .buildAndRegister();
        OreProperty oreProp = Xenotime.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Phosphate, RareEarth);
        oreProp.setSeparatedInto(RareEarth);



        //endregion

        VenusAir = new Material.Builder(GTTCore.id("venus_air"))
                .gas()
                .flags(DISABLE_DECOMPOSITION)
                .color(0x737300)
                .components(SulfurTrioxide, 68, Argon, 10)
                .buildAndRegister();
        LiquidVenusAir = new Material.Builder(GTTCore.id("liquid_venus_air"))
                .liquid(new FluidBuilder().temperature(58))
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfurTrioxide, 78, Argon, 10, Methane, 7, Ethenone, 5, Tetrafluoroethylene, 5)
                .color(0xacac00)
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
