package com.gtt.gttcore.data.lang;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;

public class GTTLangHandler {
    private static LanguageProvider enProvider;
    private static LanguageProvider zhProvider;
    public static Map<String, String> englishNameMap = new HashMap<>();
    public static void init(LanguageProvider enProvider, LanguageProvider zhProvider) {
        GTTLangHandler.enProvider = enProvider;
        GTTLangHandler.zhProvider = zhProvider;
        englishNameMap.forEach(enProvider::add);

        add("gttcore.machine.high_energy_laser_amount", "Laser Amount: %s", "激光能量：%s");
        add("gttcore.machine.high_energy_laser_amount_input", "Laser Amount Input: %s", "输入激光能量：%s");
        add("gttcore.machine.high_energy_laser_amount_output", "Laser Amount Output: %s", "输出激光能量：%s");
        add("gttcore.go_to_nether", "Go to nether", "前往下界");
        add("gttcore.back_to_overworld", "Back to overworld", "返回主世界");
        add("tagprefix.moon", TagPrefix.get("moon").langValue(), "月球%s矿石");
        add("tagprefix.mars", TagPrefix.get("mars").langValue(), "火星%s矿石");
        add("tagprefix.venus", TagPrefix.get("venus").langValue(), "金星%s矿石");
        add("tagprefix.mercury", TagPrefix.get("mercury").langValue(), "水星%s矿石");
        add("tagprefix.glacio", TagPrefix.get("glacio").langValue(), "霜原星%s矿石");



        add("config.jade.plugin_gttcore.high_energy_laser_container_provider",  "[GTTCore] High Energy Laser", "[GTTCore] 高能激光");

        add("gtceu.machine.parallel_hatch_mk5.tooltip", "Allows to run up to 16 recipes in parallel.", "允许同时处理至多16个配方。");
        add("gtceu.machine.parallel_hatch_mk6.tooltip", "Allows to run up to 64 recipes in parallel.", "允许同时处理至多64个配方。");
        add("gtceu.machine.parallel_hatch_mk7.tooltip", "Allows to run up to 256 recipes in parallel.", "允许同时处理至多256个配方。");
        add("gtceu.machine.parallel_hatch_mk8.tooltip", "Allows to run up to 1024 recipes in parallel.", "允许同时处理至多1024个配方。");

        add("gttcore.machine.wireless_energy_monitor.tooltip.0", "Energy Capacity: %s EU", "能源容量：%s EU");
    }
    
    public static void add(String key, String enValue, String zhValue){
        enProvider.add(key, enValue);
        zhProvider.add(key, zhValue);
    }
}
