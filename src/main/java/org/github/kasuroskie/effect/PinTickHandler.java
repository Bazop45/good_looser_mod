package org.github.kasuroskie.effect;

import org.github.kasuroskie.registry.ModEffects;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public final class PinTickHandler {
    private PinTickHandler() {}

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
            return;
        }
        if (!living.hasEffect(ModEffects.PINNED)) {
            return;
        }
        if (!PinnedEffect.isPinLocked(living)) {
            return;
        }
        var data = living.getPersistentData();
        PinnedEffect.lockPosition(living, data);
    }
}
