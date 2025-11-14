package com.gtt.gttcore;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gtt.gttcore.client.ClientProxy;
import com.gtt.gttcore.common.CommonProxy;
import com.gtt.gttcore.data.GTTDatagen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

@Mod(GTTCore.MOD_ID)
public class GTTCore {
    public static final String MOD_ID = "gttcore";
    public static final String NAME = "GTT Core";
    public static final Logger LOGGER = LogManager.getLogger();

    public GTTCore() {
        GTTCore.LOGGER.info("GTT Core");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        modEventBus.addListener(EventPriority.LOWEST, GTTDatagen::gatherData);
    }

    @SubscribeEvent
    public static void onPortalIgnition(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, FormattingUtil.toLowerCaseUnderscore(path));
    }
}
