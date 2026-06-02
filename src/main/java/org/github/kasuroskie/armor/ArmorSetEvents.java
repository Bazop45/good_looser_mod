package org.github.kasuroskie.armor;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.effect.PinnedEffect;
import org.github.kasuroskie.util.EffectHelper;
import org.github.kasuroskie.registry.ModEffects;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public final class ArmorSetEvents {
    public static final String STASIS_UNTIL = GoodLooserMod.MODID + "_stasis_until";
    public static final String IN_STASIS = GoodLooserMod.MODID + "_in_stasis";

    private ArmorSetEvents() {}

    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (player.getPersistentData().getBoolean(IN_STASIS)) {
            event.setCanceled(true);
            return;
        }
        if (!ArmorSetHelper.hasFullSet(player)) {
            return;
        }
        float healthAfter = player.getHealth() - event.getAmount();
        if (healthAfter > 0.0F) {
            return;
        }
        event.setCanceled(true);
        player.setHealth(1.0F);
        long until = player.level().getGameTime() + ModConstants.STASIS_DURATION_TICKS;
        player.getPersistentData().putLong(STASIS_UNTIL, until);
        player.getPersistentData().putBoolean(IN_STASIS, true);
        player.setDeltaMovement(Vec3.ZERO);
        PinnedEffect.clearPinData(player);
        EffectHelper.applyHidden(player, ModEffects.PINNED, ModConstants.STASIS_DURATION_TICKS);
        EffectHelper.applyHidden(player, MobEffects.REGENERATION, ModConstants.STASIS_DURATION_TICKS, 4);
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().getPersistentData().getBoolean(IN_STASIS)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (!player.getPersistentData().getBoolean(IN_STASIS)) {
            return;
        }
        long until = player.getPersistentData().getLong(STASIS_UNTIL);
        player.setDeltaMovement(Vec3.ZERO);
        if (player.level().getGameTime() >= until) {
            endStasis(player);
        }
    }

    private static void endStasis(ServerPlayer player) {
        player.setHealth(player.getMaxHealth());
        player.getFoodData().setFoodLevel(20);
        player.getFoodData().setSaturation(20.0F);
        player.getPersistentData().remove(IN_STASIS);
        player.getPersistentData().remove(STASIS_UNTIL);
        player.removeEffect(ModEffects.PINNED);
        player.removeEffect(MobEffects.REGENERATION);
        PinnedEffect.clearPinData(player);
    }
}
