package com.gtt.gttcore.api;

import com.google.common.collect.ImmutableList;
import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.medicalcondition.MedicalCondition;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.integration.kjs.helpers.MaterialStackWrapper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.UnaryOperator;

import static com.gtt.gttcore.data.lang.GTTChineseLanguageProvider.chineseNameMap;

public class GTTMaterialBuilder extends Material.Builder {

    /**
     * Constructs a {@link Material}. This Builder replaces the old constructors, and
     * no longer uses a class hierarchy, instead using a {@link MaterialProperties} system.
     *
     * @param resourceLocation The Name of this material. Will be formatted as
     *                         "material.<name>" for the Translation Key.
     * @since GTCEu 2.0.0
     */
    public GTTMaterialBuilder(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public GTTMaterialBuilder langValue(String name) {
        return (GTTMaterialBuilder) super.langValue(name);
    }

    @Override
    public GTTMaterialBuilder fluid() {
        return (GTTMaterialBuilder) super.fluid();
    }

    @Override
    public GTTMaterialBuilder fluid(@NotNull FluidStorageKey key, @NotNull FluidState state) {
        return (GTTMaterialBuilder) super.fluid(key, state);
    }

    @Override
    public GTTMaterialBuilder fluid(@NotNull FluidStorageKey key, @NotNull FluidBuilder builder) {
        return (GTTMaterialBuilder) super.fluid(key, builder);
    }

    @Override
    public GTTMaterialBuilder liquid() {
        return (GTTMaterialBuilder) super.liquid();
    }

    @Override
    public GTTMaterialBuilder liquid(@NotNull FluidBuilder builder) {
        return (GTTMaterialBuilder) super.liquid(builder);
    }

    @Override
    public GTTMaterialBuilder liquid(int temp) {
        return (GTTMaterialBuilder) super.liquid(temp);
    }

    @Override
    public GTTMaterialBuilder plasma() {
        return (GTTMaterialBuilder) super.plasma();
    }

    @Override
    public GTTMaterialBuilder plasma(@NotNull FluidBuilder builder) {
        return (GTTMaterialBuilder) super.plasma(builder);
    }

    @Override
    public GTTMaterialBuilder plasma(int temp) {
        return (GTTMaterialBuilder) super.plasma(temp);
    }

    @Override
    public GTTMaterialBuilder gas() {
        return (GTTMaterialBuilder) super.gas();
    }

    @Override
    public GTTMaterialBuilder gas(@NotNull FluidBuilder builder) {
        return (GTTMaterialBuilder) super.gas(builder);
    }

    @Override
    public GTTMaterialBuilder gas(int temp) {
        return (GTTMaterialBuilder) super.gas(temp);
    }

    @Override
    public GTTMaterialBuilder dust() {
        return (GTTMaterialBuilder) super.dust();
    }

    @Override
    public GTTMaterialBuilder dust(int harvestLevel) {
        return (GTTMaterialBuilder) super.dust(harvestLevel);
    }

    @Override
    public GTTMaterialBuilder dust(int harvestLevel, int burnTime) {
        return (GTTMaterialBuilder) super.dust(harvestLevel, burnTime);
    }

    @Override
    public GTTMaterialBuilder wood() {
        return (GTTMaterialBuilder) super.wood();
    }

    @Override
    public GTTMaterialBuilder wood(int harvestLevel) {
        return (GTTMaterialBuilder) super.wood(harvestLevel);
    }

    @Override
    public GTTMaterialBuilder wood(int harvestLevel, int burnTime) {
        return (GTTMaterialBuilder) super.wood(harvestLevel, burnTime);
    }

    @Override
    public GTTMaterialBuilder ingot() {
        return (GTTMaterialBuilder) super.ingot();
    }

    @Override
    public GTTMaterialBuilder ingot(int harvestLevel) {
        return (GTTMaterialBuilder) super.ingot(harvestLevel);
    }

    @Override
    public GTTMaterialBuilder ingot(int harvestLevel, int burnTime) {
        return (GTTMaterialBuilder) super.ingot(harvestLevel, burnTime);
    }

    @Override
    public GTTMaterialBuilder gem() {
        return (GTTMaterialBuilder) super.gem();
    }

    @Override
    public GTTMaterialBuilder gem(int harvestLevel) {
        return (GTTMaterialBuilder) super.gem(harvestLevel);
    }

    @Override
    public GTTMaterialBuilder gem(int harvestLevel, int burnTime) {
        return (GTTMaterialBuilder) super.gem(harvestLevel, burnTime);
    }

    @Override
    public GTTMaterialBuilder polymer() {
        return (GTTMaterialBuilder) super.polymer();
    }

    @Override
    public GTTMaterialBuilder polymer(int harvestLevel) {
        return (GTTMaterialBuilder) super.polymer(harvestLevel);
    }

    @Override
    public GTTMaterialBuilder polymer(int harvestLevel, int burnTime) {
        return (GTTMaterialBuilder) super.polymer(harvestLevel, burnTime);
    }

    @Override
    public GTTMaterialBuilder burnTime(int burnTime) {
        return (GTTMaterialBuilder) super.burnTime(burnTime);
    }

    @Override
    public GTTMaterialBuilder color(int color) {
        return (GTTMaterialBuilder) super.color(color);
    }

    @Override
    public GTTMaterialBuilder color(int color, boolean hasFluidColor) {
        return (GTTMaterialBuilder) super.color(color, hasFluidColor);
    }

    @Override
    public GTTMaterialBuilder secondaryColor(int color) {
        return (GTTMaterialBuilder) super.secondaryColor(color);
    }

    @Override
    public GTTMaterialBuilder colorAverage() {
        return (GTTMaterialBuilder) super.colorAverage();
    }

    @Override
    public GTTMaterialBuilder iconSet(MaterialIconSet iconSet) {
        return (GTTMaterialBuilder) super.iconSet(iconSet);
    }

    @Override
    public GTTMaterialBuilder components(Object... components) {
        return (GTTMaterialBuilder) super.components(components);
    }

    @Override
    public GTTMaterialBuilder componentStacks(MaterialStack... components) {
        return (GTTMaterialBuilder) super.componentStacks(components);
    }

    @Override
    public GTTMaterialBuilder componentStacks(ImmutableList<MaterialStack> components) {
        return (GTTMaterialBuilder) super.componentStacks(components);
    }

    @Override
    public GTTMaterialBuilder kjs$components(MaterialStackWrapper... components) {
        return (GTTMaterialBuilder) super.kjs$components(components);
    }

    @Override
    public GTTMaterialBuilder kjs$components(ImmutableList<MaterialStackWrapper> components) {
        return (GTTMaterialBuilder) super.kjs$components(components);
    }

    @Override
    public GTTMaterialBuilder flags(MaterialFlag... flags) {
        return (GTTMaterialBuilder) super.flags(flags);
    }

    @Override
    public GTTMaterialBuilder appendFlags(Collection<MaterialFlag> f1, MaterialFlag... f2) {
        return (GTTMaterialBuilder) super.appendFlags(f1, f2);
    }

    @Override
    public GTTMaterialBuilder ignoredTagPrefixes(TagPrefix... prefixes) {
        return (GTTMaterialBuilder) super.ignoredTagPrefixes(prefixes);
    }

    @Override
    public GTTMaterialBuilder customTags(TagKey<Item> key) {
        return (GTTMaterialBuilder) super.customTags(key);
    }

    @Override
    public GTTMaterialBuilder element(Element element) {
        return (GTTMaterialBuilder) super.element(element);
    }

    @Override
    public GTTMaterialBuilder formula(String formula) {
        return (GTTMaterialBuilder) super.formula(formula);
    }

    @Override
    public GTTMaterialBuilder formula(String formula, boolean withFormatting) {
        return (GTTMaterialBuilder) super.formula(formula, withFormatting);
    }

    @Override
    public GTTMaterialBuilder toolStats(ToolProperty toolProperty) {
        return (GTTMaterialBuilder) super.toolStats(toolProperty);
    }

    @Override
    public GTTMaterialBuilder armorStats(ArmorProperty armorProperty) {
        return (GTTMaterialBuilder) super.armorStats(armorProperty);
    }

    @Override
    public GTTMaterialBuilder rotorStats(int power, int efficiency, float damage, int durability) {
        return (GTTMaterialBuilder) super.rotorStats(power, efficiency, damage, durability);
    }

    @Override
    public GTTMaterialBuilder blastTemp(int temp) {
        return (GTTMaterialBuilder) super.blastTemp(temp);
    }

    @Override
    public GTTMaterialBuilder blastTemp(int temp, BlastProperty.GasTier gasTier) {
        return (GTTMaterialBuilder) super.blastTemp(temp, gasTier);
    }

    @Override
    public GTTMaterialBuilder blastTemp(int temp, BlastProperty.GasTier gasTier, int eutOverride) {
        return (GTTMaterialBuilder) super.blastTemp(temp, gasTier, eutOverride);
    }

    @Override
    public GTTMaterialBuilder blastTemp(int temp, BlastProperty.GasTier gasTier, int eutOverride, int durationOverride) {
        return (GTTMaterialBuilder) super.blastTemp(temp, gasTier, eutOverride, durationOverride);
    }

    @Override
    public GTTMaterialBuilder blast(int temp) {
        return (GTTMaterialBuilder) super.blast(temp);
    }

    @Override
    public GTTMaterialBuilder blast(int temp, BlastProperty.GasTier gasTier) {
        return (GTTMaterialBuilder) super.blast(temp, gasTier);
    }

    @Override
    public GTTMaterialBuilder blast(UnaryOperator<BlastProperty.Builder> b) {
        return (GTTMaterialBuilder) super.blast(b);
    }

    @Override
    public GTTMaterialBuilder removeHazard() {
        return (GTTMaterialBuilder) super.removeHazard();
    }

    @Override
    public GTTMaterialBuilder radioactiveHazard(float multiplier) {
        return (GTTMaterialBuilder) super.radioactiveHazard(multiplier);
    }

    @Override
    public GTTMaterialBuilder hazard(HazardProperty.HazardTrigger trigger, MedicalCondition condition) {
        return (GTTMaterialBuilder) super.hazard(trigger, condition);
    }

    @Override
    public GTTMaterialBuilder hazard(HazardProperty.HazardTrigger trigger, MedicalCondition condition, float progressionMultiplier) {
        return (GTTMaterialBuilder) super.hazard(trigger, condition, progressionMultiplier);
    }

    @Override
    public GTTMaterialBuilder hazard(HazardProperty.HazardTrigger trigger, MedicalCondition condition, float progressionMultiplier, boolean applyToDerivatives) {
        return (GTTMaterialBuilder) super.hazard(trigger, condition, progressionMultiplier, applyToDerivatives);
    }

    @Override
    public GTTMaterialBuilder hazard(HazardProperty.HazardTrigger trigger, MedicalCondition condition, boolean applyToDerivatives) {
        return (GTTMaterialBuilder) super.hazard(trigger, condition, applyToDerivatives);
    }

    @Override
    public GTTMaterialBuilder ore() {
        return (GTTMaterialBuilder) super.ore();
    }

    @Override
    public GTTMaterialBuilder ore(boolean emissive) {
        return (GTTMaterialBuilder) super.ore(emissive);
    }

    @Override
    public GTTMaterialBuilder ore(int oreMultiplier, int byproductMultiplier) {
        return (GTTMaterialBuilder) super.ore(oreMultiplier, byproductMultiplier);
    }

    @Override
    public GTTMaterialBuilder ore(int oreMultiplier, int byproductMultiplier, boolean emissive) {
        return (GTTMaterialBuilder) super.ore(oreMultiplier, byproductMultiplier, emissive);
    }

    @Override
    public GTTMaterialBuilder washedIn(Material m) {
        return (GTTMaterialBuilder) super.washedIn(m);
    }

    @Override
    public GTTMaterialBuilder washedIn(Material m, int washedAmount) {
        return (GTTMaterialBuilder) super.washedIn(m, washedAmount);
    }

    @Override
    public GTTMaterialBuilder separatedInto(Material... m) {
        return (GTTMaterialBuilder) super.separatedInto(m);
    }

    @Override
    public GTTMaterialBuilder oreSmeltInto(Material m) {
        return (GTTMaterialBuilder) super.oreSmeltInto(m);
    }

    @Override
    public GTTMaterialBuilder polarizesInto(Material m) {
        return (GTTMaterialBuilder) super.polarizesInto(m);
    }

    @Override
    public GTTMaterialBuilder arcSmeltInto(Material m) {
        return (GTTMaterialBuilder) super.arcSmeltInto(m);
    }

    @Override
    public GTTMaterialBuilder macerateInto(Material m) {
        return (GTTMaterialBuilder) super.macerateInto(m);
    }

    @Override
    public GTTMaterialBuilder ingotSmeltInto(Material m) {
        return (GTTMaterialBuilder) super.ingotSmeltInto(m);
    }

    @Override
    public GTTMaterialBuilder addOreByproducts(Material... byproducts) {
        return (GTTMaterialBuilder) super.addOreByproducts(byproducts);
    }

    @Override
    public GTTMaterialBuilder cableProperties(long voltage, int amperage, int loss) {
        return (GTTMaterialBuilder) super.cableProperties(voltage, amperage, loss);
    }

    @Override
    public GTTMaterialBuilder cableProperties(long voltage, int amperage, int loss, boolean isSuperCon) {
        return (GTTMaterialBuilder) super.cableProperties(voltage, amperage, loss, isSuperCon);
    }

    @Override
    public GTTMaterialBuilder cableProperties(long voltage, int amperage, int loss, boolean isSuperCon, int criticalTemperature) {
        return (GTTMaterialBuilder) super.cableProperties(voltage, amperage, loss, isSuperCon, criticalTemperature);
    }

    @Override
    public GTTMaterialBuilder fluidPipeProperties(int maxTemp, int throughput, boolean gasProof) {
        return (GTTMaterialBuilder) super.fluidPipeProperties(maxTemp, throughput, gasProof);
    }

    @Override
    public GTTMaterialBuilder fluidPipeProperties(int maxTemp, int throughput, boolean gasProof, boolean acidProof, boolean cryoProof, boolean plasmaProof) {
        return (GTTMaterialBuilder) super.fluidPipeProperties(maxTemp, throughput, gasProof, acidProof, cryoProof, plasmaProof);
    }

    @Override
    public GTTMaterialBuilder itemPipeProperties(int priority, float stacksPerSec) {
        return (GTTMaterialBuilder) super.itemPipeProperties(priority, stacksPerSec);
    }

    public GTTMaterialBuilder chineseLangValue(String chineseName) {
        chineseNameMap.put(id.toLanguageKey("material"), chineseName);
        return this;
    }
}
