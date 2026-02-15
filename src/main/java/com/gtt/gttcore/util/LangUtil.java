package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.GTCEu;
import com.gtt.gttcore.data.lang.GTTChineseLanguageProvider;
import com.gtt.gttcore.data.lang.GTTLangHandler;

public class LangUtil {
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
    public static String createItemZhTranslation(String id, String chineseTranslation){
        if (!GTCEu.isDataGen()) return id;
        String translationId = "item.gttcore." + id;
        GTTChineseLanguageProvider.chineseNameMap.put(translationId, chineseTranslation);
        return id;
    }
}
