package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.GTCEu;
import com.gtt.gttcore.data.lang.GTTChineseLanguageProvider;
import com.gtt.gttcore.data.lang.GTTLangHandler;

import static net.minecraft.ChatFormatting.*;

public class LangUtil {
    public static final String[] VLVH_CHINESE = new String[] {
            "原始", // not doing the gray color for these first two because it looks weird
            "基础",
            AQUA + "进阶",
            GOLD + "进阶",
            DARK_PURPLE + "进阶",
            BLUE + "精英",
            LIGHT_PURPLE + "精英",
            RED + "精英",
            DARK_AQUA + "终极",
            DARK_RED + "史诗",
            GREEN + "史诗",
            DARK_GREEN + "史诗",
            YELLOW + "史诗",
            BLUE.toString() + BOLD + "传奇",
            RED.toString() + BOLD + "MAX"
    };
    public static String createZhTranslation(String id, String translation){
        if (!GTCEu.isDataGen()) return id;
        GTTChineseLanguageProvider.chineseNameMap.put(id, translation);
        return id;
    }
    public static String createEnTranslation(String id, String translation){
        if (!GTCEu.isDataGen()) return id;
        GTTLangHandler.englishNameMap.put(id, translation);
        return id;
    }
    public static String createEnZhTranslation(String id, String englishTranslation, String chineseTranslation){
        if (!GTCEu.isDataGen()) return id;
        GTTChineseLanguageProvider.chineseNameMap.put(id, chineseTranslation);
        GTTLangHandler.englishNameMap.put(id, englishTranslation);
        return id;
    }
    public static String createBlockZhTranslation(String id, String chineseTranslation){
        if (!GTCEu.isDataGen()) return id;
        String translationId = "block.gttcore." + id;
        GTTChineseLanguageProvider.chineseNameMap.put(translationId, chineseTranslation);
        return id;
    }
    public static String createEntityZhTranslation(String id, String chineseTranslation){
        if (!GTCEu.isDataGen()) return id;
        String translationId = "entity.gttcore." + id;
        GTTChineseLanguageProvider.chineseNameMap.put(translationId, chineseTranslation);
        return id;
    }
    public static String createItemZhTranslation(String id, String chineseTranslation){
        if (!GTCEu.isDataGen()) return id;
        String translationId = "item.gttcore." + id;
        GTTChineseLanguageProvider.chineseNameMap.put(translationId, chineseTranslation);
        return id;
    }
}
