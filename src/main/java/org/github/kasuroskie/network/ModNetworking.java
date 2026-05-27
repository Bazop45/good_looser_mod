package org.github.kasuroskie.network;

import org.github.kasuroskie.GoodLooserMod;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class ModNetworking {
    public static final String PROTOCOL_VERSION = "1";

    private ModNetworking() {}

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);
        registrar.playToServer(LaunchNailPayload.TYPE, LaunchNailPayload.STREAM_CODEC, LaunchNailPayload::handle);
        registrar.playToClient(SyncRagePayload.TYPE, SyncRagePayload.STREAM_CODEC, SyncRagePayload::handle);
    }
}
