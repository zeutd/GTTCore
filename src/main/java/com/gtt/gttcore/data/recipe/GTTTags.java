package com.gtt.gttcore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gtt.gttcore.GTTCore;
import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagUtil.optionalTag;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTTTags {
    public static final TagKey<Item> andesiteAlloyable = createModItemTag("andesite_alloyable");
    public static <T> TagKey<T> createModTag(Registry<T> registry, String path) {
        return optionalTag(registry, GTTCore.id(path));
    }

    public static TagKey<Item> createModItemTag(String path) {
        return createModTag(BuiltInRegistries.ITEM, path);
    }

    public static void addItemTags(RegistrateItemTagsProvider prov){
        prov.addTag(GTTTags.andesiteAlloyable)
                .add(ChemicalHelper.get(dust, Pyrite).getItem())
                .add(ChemicalHelper.get(dust, Magnetite).getItem())
                .add(ChemicalHelper.get(dust, Limonite).getItem())
                .add(ChemicalHelper.get(dust, Goethite).getItem())
                .add(ChemicalHelper.get(dust, Hematite).getItem())
                .add(ChemicalHelper.get(dust, Iron).getItem());



    }
}
