package org.github.kasuroskie.client;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.client.renderer.LoosersnailRenderer;
import org.github.kasuroskie.registry.ModEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.slf4j.Logger;
import org.github.kasuroskie.util.ModLogger;

@Mod(value = GoodLooserMod.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = GoodLooserMod.MODID, value = Dist.CLIENT)
public class GoodLooserModClient {
    private static final Logger LOGGER = ModLogger.getLogger(GoodLooserModClient.class);

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        LOGGER.debug("Registering entity renderers");
        event.registerEntityRenderer(ModEntities.NAIL.get(), LoosersnailRenderer::new);
        LOGGER.info("Registered Loosersnail projectile renderer (GeckoLib)");
    }
}
