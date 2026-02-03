package com.gtt.gttcore.common.data;

import com.gtt.gttcore.client.renderers.BlackholeRenderer;
import com.gtt.gttcore.common.entity.BlackholeBombEntity;
import com.gtt.gttcore.common.entity.BlackholeEntity;
import com.gtt.gttcore.common.entity.MushroomCloudEntity;
import com.gtt.gttcore.common.entity.NuclearBombEntity;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.MobCategory;

import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class GTTEntityTypes {
    public static final EntityEntry<BlackholeBombEntity> BLACKHOLE_BOMB = REGISTRATE
            .<BlackholeBombEntity>entity("blackhole_bomb", BlackholeBombEntity::new, MobCategory.MISC)
            .lang("Black Hole Bomb")
            .properties(builder -> builder.sized(0.98F, 0.98F).fireImmune().clientTrackingRange(10).updateInterval(10))
            .register();
    public static final EntityEntry<NuclearBombEntity> NUCLEAR_BOMB = REGISTRATE
            .<NuclearBombEntity>entity("nuclear_bomb", NuclearBombEntity::new, MobCategory.MISC)
            .lang("Nuclear Bomb")
            .properties(builder -> builder.sized(0.98F, 0.98F).fireImmune().clientTrackingRange(10).updateInterval(10))
            .register();
    public static final EntityEntry<BlackholeEntity> BLACKHOLE = REGISTRATE
            .<BlackholeEntity>entity("blackhole", BlackholeEntity::new, MobCategory.MISC)
            .lang("Black Hole")
            .properties(builder -> builder.sized(10F, 10F).fireImmune().clientTrackingRange(10).updateInterval(10))
            .renderer(() -> BlackholeRenderer::new)
            .register();
    public static final EntityEntry<MushroomCloudEntity> MUSHROOM_CLOUD = REGISTRATE
            .<MushroomCloudEntity>entity("mushroom_cloud", MushroomCloudEntity::new, MobCategory.MISC)
            .lang("Mushroom Cloud")
            .properties(builder -> builder.sized(15F, 20F).fireImmune().clientTrackingRange(10).updateInterval(10))
            .register();
    public static void init() {}
}
