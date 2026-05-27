package org.github.kasuroskie.network;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.client.gui.RageHudOverlay;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncRagePayload(float rage) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncRagePayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "sync_rage"));

    public static final StreamCodec<FriendlyByteBuf, SyncRagePayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.FLOAT,
                    SyncRagePayload::rage,
                    SyncRagePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SyncRagePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> RageHudOverlay.setClientRage(payload.rage()));
    }
}
