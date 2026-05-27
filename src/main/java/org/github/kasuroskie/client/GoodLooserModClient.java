package org.github.kasuroskie.client;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.client.renderer.NailProjectileRenderer;
import org.github.kasuroskie.registry.ModEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = GoodLooserMod.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = GoodLooserMod.MODID, value = Dist.CLIENT)
public class GoodLooserModClient {
    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NAIL.get(), NailProjectileRenderer::new);
    }
}
