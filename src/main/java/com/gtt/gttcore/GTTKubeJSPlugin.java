package com.gtt.gttcore;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.bindings.event.WorldgenEvents;
import dev.latvian.mods.kubejs.level.gen.RemoveWorldgenEventJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

public class GTTKubeJSPlugin extends KubeJSPlugin {
    @Override
    public void initStartup() {
        super.initStartup();
        GTTCore.LOGGER.info("GTT KubeJS Plugin Startup Init");
        WorldgenEvents.REMOVE.listenJava(ScriptType.STARTUP, null, e -> {
            GTTCore.LOGGER.info("Removing Features");
            RemoveWorldgenEventJS removeWorldgenEvent = (RemoveWorldgenEventJS) e;
            removeWorldgenEvent.removeFeatureById(GenerationStep.Decoration.UNDERGROUND_ORES,
                    new ResourceLocation[]{
                            new ResourceLocation("ad_astra:moon_iron_ore"),
                            new ResourceLocation("ad_astra:moon_desh_ore"),
                            new ResourceLocation("ad_astra:moon_ice_shard_ore"),

                            new ResourceLocation("ad_astra:mars_diamond_ore"),
                            new ResourceLocation("ad_astra:mars_ice_shard_ore"),
                            new ResourceLocation("ad_astra:mars_iron_ore"),
                            new ResourceLocation("ad_astra:mars_ostrum_ore"),

                            new ResourceLocation("ad_astra:venus_calorite_ore"),
                            new ResourceLocation("ad_astra:venus_coal_ore"),
                            new ResourceLocation("ad_astra:venus_diamond_ore"),
                            new ResourceLocation("ad_astra:venus_gold_ore"),

                            new ResourceLocation("ad_astra:mercury_iron_ore"),

                            new ResourceLocation("ad_astra:glacio_coal_ore"),
                            new ResourceLocation("ad_astra:glacio_copper_ore"),
                            new ResourceLocation("ad_astra:glacio_ice_shard_ore"),
                            new ResourceLocation("ad_astra:glacio_iron_ore"),
                            new ResourceLocation("ad_astra:glacio_lapis_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_coal_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_copper_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_iron_ore"),
                            new ResourceLocation("ad_astra:glacio_deepslate_lapis_ore"),
                            new ResourceLocation("create:zinc_ore")
                    }
            );
            return null;
        });
    }
}
