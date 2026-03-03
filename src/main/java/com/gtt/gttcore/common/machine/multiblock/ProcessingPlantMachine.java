package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Predicate;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class ProcessingPlantMachine extends SlotMultiblockMachine {
    @SaveField
    @SyncToClient
    public int itemTier;
    public static final GTRecipeType[] AVAILABLE_RECIPE_TYPES = new GTRecipeType[]{
            FURNACE_RECIPES,
            ALLOY_SMELTER_RECIPES,
            ARC_FURNACE_RECIPES,
            ASSEMBLER_RECIPES,
            AUTOCLAVE_RECIPES,
            BENDER_RECIPES,
            BREWING_RECIPES,
            CANNER_RECIPES,
            CENTRIFUGE_RECIPES,
            CHEMICAL_BATH_RECIPES,
            CHEMICAL_RECIPES,
            COMPRESSOR_RECIPES,
            CUTTER_RECIPES,
            DISTILLERY_RECIPES,
            ELECTROLYZER_RECIPES,
            EXTRACTOR_RECIPES,
            EXTRUDER_RECIPES,
            FERMENTING_RECIPES,
            FLUID_SOLIDFICATION_RECIPES,
            FLUID_HEATER_RECIPES,
            FORGE_HAMMER_RECIPES,
            FORMING_PRESS_RECIPES,
            LATHE_RECIPES,
            SCANNER_RECIPES,
            MIXER_RECIPES,
            ORE_WASHER_RECIPES,
            PACKER_RECIPES,
            POLARIZER_RECIPES,
            MACERATOR_RECIPES,
            CIRCUIT_ASSEMBLER_RECIPES,
            WIREMILL_RECIPES,
            THERMAL_CENTRIFUGE_RECIPES,
            SIFTER_RECIPES,
            LASER_ENGRAVER_RECIPES
    };
    public ProcessingPlantMachine(BlockEntityCreationInfo info) {
        super(info);
    }

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe){
        if (!(machine instanceof ProcessingPlantMachine)) {
            return RecipeModifier.nullWrongType(ProcessingPlantMachine.class, machine);
        }

        int parallelAmount = ParallelLogic.getParallelAmount(machine, recipe, 4);
        return ModifierFunction.builder()
                .inputModifier(ContentModifier.multiplier(parallelAmount))
                .outputModifier(ContentModifier.multiplier(parallelAmount))
                .parallels(parallelAmount)
                .build();
    }

    @Override
    public Predicate<ItemStack> getFilter() {
        return stack -> {
            Item item = stack.getItem();
            if (item instanceof MetaMachineItem machineItem){
                if (machineItem.getDefinition().getRecipeTypes().length == 0){
                    return false;
                }
                GTRecipeType itemRecipeType = machineItem.getDefinition().getRecipeTypes()[0];
                return Arrays.stream(AVAILABLE_RECIPE_TYPES).anyMatch(recipeType -> recipeType == itemRecipeType);
            }
            return false;
        };
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        return super.beforeWorking(recipe) && itemTier >= this.getMaxOverclockTier();
    }

    @Override
    public void onContentsChanged() {
        super.onContentsChanged();
        int result = -1;
        if (getInventory().getStackInSlot(0).getItem() instanceof MetaMachineItem machineItem) {
            GTRecipeType recipeType = machineItem.getDefinition().getRecipeTypes()[0];
            itemTier = machineItem.getDefinition().getTier();
            getSyncDataHolder().markClientSyncFieldDirty("itemTier");
            for (int i = 0; i < AVAILABLE_RECIPE_TYPES.length; i++) {
                if (AVAILABLE_RECIPE_TYPES[i] == recipeType){
                    result = i;
                    break;
                }
            }
        }
        setActiveRecipeType(result);
    }

    @Override
    public @NotNull GTRecipeType getRecipeType() {
        return getActiveRecipeType() == -1 ? GTRecipeTypes.DUMMY_RECIPES : AVAILABLE_RECIPE_TYPES[getActiveRecipeType()];
    }
}
