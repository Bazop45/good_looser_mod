package org.github.kasuroskie.combat;

import org.github.kasuroskie.armor.ArmorSetEvents;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class CombatEvents {
    private CombatEvents() {}

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (isBlocked(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (event.getItem().is(Items.SHIELD) && NailCombatHelper.isCombatDisabled(player)) {
            event.setCanceled(true);
            return;
        }
        if (isBlocked(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (isBlocked(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (isBlocked(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (isBlocked(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (isBlocked(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker
                && NailCombatHelper.isCombatDisabled(attacker)) {
            event.setCanceled(true);
        }
    }

    private static boolean isBlocked(Player player) {
        if (player.getPersistentData().getBoolean(ArmorSetEvents.IN_STASIS)) {
            return true;
        }
        return NailCombatHelper.isCombatDisabled(player);
    }
}
