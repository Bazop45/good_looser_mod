package org.github.kasuroskie.network;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.entity.NailProjectile;
import org.github.kasuroskie.rage.RageHelper;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record LaunchNailPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaunchNailPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "launch_nail"));

    public static final StreamCodec<FriendlyByteBuf, LaunchNailPayload> STREAM_CODEC =
            StreamCodec.unit(new LaunchNailPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(LaunchNailPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }
            long gameTime = player.level().getGameTime();
            long lastLaunch = player.getPersistentData().getLong(GoodLooserMod.MODID + "_last_launch");
            if (gameTime - lastLaunch < ModConstants.LAUNCH_COOLDOWN_TICKS) {
                return;
            }
            player.getPersistentData().putLong(GoodLooserMod.MODID + "_last_launch", gameTime);

            boolean empowered = RageHelper.isFull(player);
            if (empowered) {
                RageHelper.clearRage(player);
            }

            NailProjectile nail = new NailProjectile(player.level(), player, empowered);
            Vec3 look = player.getLookAngle();
            nail.setPos(
                    player.getX() + look.x * 0.5,
                    player.getEyeY() - 0.1,
                    player.getZ() + look.z * 0.5);
            nail.shoot(look, ModConstants.NAIL_SPEED);
            player.level().addFreshEntity(nail);
        });
    }
}
