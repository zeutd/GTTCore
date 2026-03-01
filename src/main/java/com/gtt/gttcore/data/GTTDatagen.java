package com.gtt.gttcore.data;

import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.gtt.gttcore.data.lang.GTTChineseLanguageProvider;
import com.gtt.gttcore.data.lang.GTTLangHandler;
import com.gtt.gttcore.data.molecule.GTTMoleculeProvider;
import com.gtt.gttcore.data.recipe.GTTRecipeProvider;
import com.gtt.gttcore.data.recipe.GTTTags;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import static com.gtt.gttcore.GTTCore.LOGGER;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class GTTDatagen {
    public static void gatherData(GatherDataEvent event){
        GTTRecipeTypes.gatherData();
        LOGGER.info("GTT Datagen");
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        GTTRecipeProvider.registerAllProcessing(generator, output);
        generator.addProvider(true, new GTTPlanetProvider(output));
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, GTTTags::addItemTags);
        LanguageProvider chineseProvider = new GTTChineseLanguageProvider(output);
        REGISTRATE.addDataGenerator(ProviderType.LANG, englishProvider -> GTTLangHandler.init(englishProvider, chineseProvider));
        generator.addProvider(true, chineseProvider);
        new GTTMoleculeProvider().gatherData(event);
    }
    public static void gatherDataHighPriority(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(true, new GTTTextureProvider(output, event.getExistingFileHelper()));
    }
}
