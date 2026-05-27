package org.github.kasuroskie.rage;

import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.network.SyncRagePayload;
import org.github.kasuroskie.registry.ModAttachments;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public final class RageHelper {
    private RageHelper() {}

    public static float getRage(Player player) {
        return player.getData(ModAttachments.RAGE.get());
    }

    public static void setRage(Player player, float rage) {
        float clamped = Math.clamp(rage, 0.0F, ModConstants.MAX_RAGE);
        player.setData(ModAttachments.RAGE.get(), clamped);
        if (player instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new SyncRagePayload(clamped));
        }
    }

    public static void addRage(Player player, float amount) {
        if (amount <= 0.0F) {
            return;
        }
        setRage(player, getRage(player) + amount);
    }

    public static boolean isFull(Player player) {
        return getRage(player) >= ModConstants.MAX_RAGE;
    }

    public static void clearRage(Player player) {
        setRage(player, 0.0F);
    }
}
