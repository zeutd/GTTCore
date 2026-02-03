package com.gtt.gttcore.data.lang;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;

public class GTTChineseLanguageProvider extends LanguageProvider {
    public static Map<String, String> chineseNameMap = new HashMap<>();
    public GTTChineseLanguageProvider(PackOutput output) {
        super(output, GTTCore.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        chineseNameMap.forEach(this::add);
    }
}
