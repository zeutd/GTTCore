package com.gtt.gttcore.data;

import com.gtt.gttcore.data.recipe.GTTRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class GTTDatagen {
    public static void gatherData(GatherDataEvent event){
        LOGGER.info("GTT Datagen");
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        GTTRecipeProvider.registerAllProcessing(generator, output);
    }
}
