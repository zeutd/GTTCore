package com.gtt.gttcore.common.data.recipes;

import appeng.core.definitions.AEBlocks;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gtt.gttcore.common.data.GTTItems;
import com.gtt.gttcore.common.data.recipes.lines.BrineLine;
import com.gtt.gttcore.common.data.recipes.lines.PlatinumGroupLine;
import com.gtt.gttcore.common.data.recipes.lines.ZieglerNattaLine;
import com.gtt.gttcore.common.data.recipes.lines.ZirconiumLine;
import com.gtt.gttcore.common.data.recipes.remove.AE2RecipeRemoval;
import com.gtt.gttcore.common.data.recipes.remove.AdAstraRecipeRemoval;
import com.gtt.gttcore.common.data.recipes.remove.CreateRecipeRemoval;
import com.gtt.gttcore.common.data.recipes.remove.GregTechRecipeRemoval;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import com.tterrag.registrate.util.entry.ItemEntry;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.filter.IDFilter;
import dev.latvian.mods.kubejs.recipe.filter.RecipeFilter;
import earth.terrarium.adastra.common.registry.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gtt.gttcore.common.data.GTTMaterials.Calorite;
import static com.gtt.gttcore.common.data.GTTMaterials.FluixCrystal;

public class GTTRecipes {
    public static Map<ReplacementMatch, OutputReplacement>
            replaceOutputMap = new HashMap<>();
    public static Map<ReplacementMatch, InputReplacement>
            replaceInputMap = new HashMap<>();

    public static void replaceOutput(Supplier<? extends ItemLike> toReplace, Supplier<? extends ItemLike> replacement){
        replaceOutputMap.put(new SingleItemMatch(new ItemStack(toReplace.get())), OutputItem.of(new ItemStack(replacement.get()), 1));
    }
    public static void replaceOutput(Supplier<? extends ItemLike> toReplace, ItemStack replacement){
        replaceOutputMap.put(new SingleItemMatch(new ItemStack(toReplace.get())), OutputItem.of(replacement, 1));
    }
    public static void replaceOutput(Item toReplace, Supplier<? extends ItemLike> replacement){
        replaceOutputMap.put(new SingleItemMatch(new ItemStack(toReplace)), OutputItem.of(new ItemStack(replacement.get()), 1));
    }
    public static void replaceOutput(Item toReplace, ItemStack replacement){
        replaceOutputMap.put(new SingleItemMatch(new ItemStack(toReplace)), OutputItem.of(replacement, 1));
    }
    public static void replaceInput(Supplier<? extends ItemLike> toReplace, ItemStack replacement){
        replaceInputMap.put(new SingleItemMatch(new ItemStack(toReplace.get())), InputItem.of(Ingredient.of(replacement), 1));
    }
    public static void replaceInput(Supplier<? extends ItemLike> toReplace, Supplier<? extends ItemLike> replacement){
        replaceInputMap.put(new SingleItemMatch(new ItemStack(toReplace.get())), InputItem.of(Ingredient.of(replacement.get()), 1));
    }
    public static void replaceInput(Item toReplace, ItemStack replacement){
        replaceInputMap.put(new SingleItemMatch(new ItemStack(toReplace)), InputItem.of(Ingredient.of(replacement), 1));
    }
    public static void replaceInput(Item toReplace, Supplier<? extends ItemLike> replacement){
        replaceInputMap.put(new SingleItemMatch(new ItemStack(toReplace)), InputItem.of(Ingredient.of(replacement.get()), 1));
    }
    public static void replace(Supplier<? extends ItemLike> toReplace, ItemStack replacement){
        replaceInput(toReplace, replacement);
        replaceOutput(toReplace, replacement);
    }
    public static void replace(Supplier<? extends ItemLike> toReplace, Supplier<? extends ItemLike> replacement){
        replaceInput(toReplace, replacement);
        replaceOutput(toReplace, replacement);
    }
    public static void replace(Item toReplace, ItemStack replacement){
        replaceInput(toReplace, replacement);
        replaceOutput(toReplace, replacement);
    }
    public static void replace(Item toReplace, Supplier<? extends ItemLike> replacement){
        replaceInput(toReplace, replacement);
        replaceOutput(toReplace, replacement);
    }
    static {
        replaceInput(ModItems.FAN, ChemicalHelper.get(TagPrefix.rotor, Steel));
        replaceInput(AllItems.PROPELLER, ChemicalHelper.get(TagPrefix.rotor, Iron));
        replaceOutput(GTItems.WETWARE_PROCESSOR_LuV, GTTItems.UNAWAKENED_WETWARE_PROCESSOR_LuV);
        replaceOutput(GTItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM, GTTItems.UNAWAKENED_WETWARE_PROCESSOR_ASSEMBLY_ZPM);
        replaceOutput(GTItems.WETWARE_SUPER_COMPUTER_UV, GTTItems.UNAWAKENED_WETWARE_SUPER_COMPUTER_UV);
        replaceOutput(GTItems.WETWARE_MAINFRAME_UHV, GTTItems.UNAWAKENED_WETWARE_MAINFRAME_UHV);
        replaceInput(AllItems.ELECTRON_TUBE, GTItems.VACUUM_TUBE);
        replace(AEBlocks.QUARTZ_BLOCK.asItem(), ChemicalHelper.get(TagPrefix.block, CertusQuartz));
        replace(AEBlocks.FLUIX_BLOCK.asItem(), ChemicalHelper.get(TagPrefix.block, FluixCrystal));
        replaceInput(AllItems.WHISK, ChemicalHelper.get(TagPrefix.rotor, Iron));
    }
    public static void init(Consumer<FinishedRecipe> provider) {
        MiscRecipes.init(provider);
        MachineRecipes.init(provider);
        ZieglerNattaLine.init(provider);
        ZirconiumLine.init(provider);
        BrineLine.init(provider);
        PlatinumGroupLine.init(provider);
        MixerRecipes.init(provider);
        RocketRecipes.init(provider);
        CircuitRecipes.init(provider);
    }

    public static void remove(Consumer<RecipeFilter> provider) {
        CreateRecipeRemoval.init(provider);
        AdAstraRecipeRemoval.init(provider);
        AE2RecipeRemoval.init(provider);
        GregTechRecipeRemoval.init(provider);
        provider.accept(new IDFilter(new ResourceLocation("minecraft:ender_eye")));
    }
}
