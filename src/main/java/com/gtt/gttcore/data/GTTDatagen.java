package com.gtt.gttcore.data;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.data.tags.ItemTagLoader;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.gtt.gttcore.data.lang.GTTLangHandler;
import com.gtt.gttcore.data.recipe.GTTRecipeProvider;
import com.gtt.gttcore.data.recipe.GTTTags;
import com.simibubi.create.infrastructure.data.CreateRegistrateTags;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.gtt.gttcore.GTTCore.LOGGER;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class GTTDatagen {
    public static void gatherData(GatherDataEvent event){
        GTTRecipeTypes.gatherData();
        LOGGER.info("GTT Datagen");
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        //GTTRecipeProvider.registerAllProcessing(generator, output);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, GTTTags::addItemTags);
        REGISTRATE.addDataGenerator(ProviderType.LANG, GTTLangHandler::init);
    }
}
