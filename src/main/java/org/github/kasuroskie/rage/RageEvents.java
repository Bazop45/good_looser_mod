package org.github.kasuroskie.rage;

import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.effect.PinnedEffect;
import org.github.kasuroskie.entity.NailProjectile;
import org.github.kasuroskie.registry.ModEffects;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public final class RageEvents {
    private RageEvents() {}

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        if (event.getNewDamage() <= 0.0F) {
            return;
        }
        if (event.getSource().getDirectEntity() instanceof NailProjectile) {
            return;
        }
        float rageGain = event.getNewDamage() * ModConstants.RAGE_PER_DAMAGE;
        if (event.getEntity() instanceof Player victim) {
            RageHelper.addRage(victim, rageGain);
        }
        if (event.getSource().getEntity() instanceof Player attacker
                && attacker != event.getEntity()) {
            RageHelper.addRage(attacker, rageGain);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
            RageHelper.setRage(player, RageHelper.getRage(player));
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (!(event.getEntity() instanceof net.minecraft.world.entity.LivingEntity living)) {
            return;
        }
        if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().is(ModEffects.PINNED)) {
            PinnedEffect.clearPinData(living);
        }
    }
}
