package com.gtt.gttcore.mixin;

import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Function;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.air;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.CASING_WATERTIGHT;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.MOLYBDENUM_DISILICIDE_COIL_BLOCK;
import static com.gregtechceu.gtceu.common.data.GTMaterials.WatertightSteel;
@Mixin(value = GCYMMachines.class, remap = false)
public class GCYMMachinesMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/api/registry/registrate/MultiblockMachineBuilder;pattern(Ljava/util/function/Function;)Lcom/gregtechceu/gtceu/api/registry/registrate/MultiblockMachineBuilder;", ordinal = 13))
    private static Function<MultiblockMachineDefinition, BlockPattern> largeAutoclaveMixin(Function<MultiblockMachineDefinition, BlockPattern> pattern){
        return definition -> FactoryBlockPattern.start()
                .aisle("A BBB A","ABCCCBA","BCCCCCB","BCCCCCB","BCCCCCB"," BCCCB ","  BBB  ")
                .aisle("  BBB  "," B   B ","B     B","B     B","B     B"," B   B ","  BBB  ")
                .aisle("  BBB  "," B   B ","B     B","B     B","B     B"," B   B ","  BBB  ")
                .aisle("  BBB  "," B D B ","B  D  B","BDDDDDB","B  D  B"," B D B ","  BBB  ")
                .aisle("  BBB  "," B   B ","B     B","B     B","B     B"," B   B ","  BBB  ")
                .aisle("  BBB  "," B   B ","B     B","B     B","B     B"," B   B ","  BBB  ")
                .aisle("A B~B A","ABCCCBA","BCCCCCB","BCCCCCB","BCCCCCB"," BCCCB ","  BBB  ")
                .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:watertight_steel_frame"))))
                .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:watertight_casing"))))
                .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:laminated_glass"))))
                .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:titanium_pipe_casing"))))
                .where(" ", any())
                .build();
    }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/api/registry/registrate/MultiblockMachineBuilder;pattern(Ljava/util/function/Function;)Lcom/gregtechceu/gtceu/api/registry/registrate/MultiblockMachineBuilder;", ordinal = 18))
    private static Function<MultiblockMachineDefinition, BlockPattern> largeExtractorMixin(Function<MultiblockMachineDefinition, BlockPattern> pattern){
        return definition -> FactoryBlockPattern.start()
                .aisle("XXXXX", "F   F", "FFFFF", "F   F", "XXXXX", "     ", "     ", "     ", "     ")
                .aisle("XXXXX", " CCC ", "FCCCF", " CCC ", "XXCXX", " FCF ", " FCF ", " FCF ", " XXX ")
                .aisle("XXXXX", " C C ", "FC CF", " C C ", "XC CX", " C C ", " C C ", " C C ", " XXX ")
                .aisle("XXXXX", " CCC ", "FCCCF", " CCC ", "XXCXX", " FCF ", " FCF ", " FCF ", " XXX ")
                .aisle("XX~XX", "F   F", "FFFFF", "F   F", "XXXXX", "     ", "     ", "     ", "     ")
                .where('~', controller(blocks(definition.get())))
                .where('X', blocks(CASING_WATERTIGHT.get()).setMinGlobalLimited(25)
                        .or(autoAbilities(definition.getRecipeTypes()))
                        .or(autoAbilities(true, false, true)))
                .where('C', blocks(MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                .where('F', frames(WatertightSteel))
                .where(' ', air())
                .build();
    }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/api/registry/registrate/MultiblockMachineBuilder;pattern(Ljava/util/function/Function;)Lcom/gregtechceu/gtceu/api/registry/registrate/MultiblockMachineBuilder;", ordinal = 20))
    private static Function<MultiblockMachineDefinition, BlockPattern> largeSolidifierMixin(Function<MultiblockMachineDefinition, BlockPattern> pattern){
        return definition -> FactoryBlockPattern.start()
                .aisle("   AAAAA   ","   AAAAA   ","   BBBBB   ","   BBBBB   ","   AAAAA   ")
                .aisle("AAAAAAAAAAA","AAAAAAAAAAA","ABBB   BBBA","ABBB   BBBA","AAAAAAAAAAA")
                .aisle("AAAAAAAAAAA","AC C C C CA","A         A","AD D D D DA","AAAAAAAAAAA")
                .aisle("AAAAAAAAAAA","AAAAAAAAAAA","ABBB   BBBA","ABBB   BBBA","AAAAAAAAAAA")
                .aisle("   AA~AA   ","   AAAAA   ","   BBBBB   ","   BBBBB   ","   AAAAA   ")
                .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:watertight_casing"))))
                .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:laminated_glass"))))
                .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:titanium_pipe_casing"))))
                .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:steel_pipe_casing"))))
                .where(" ", any())
                .build();
    }
}
