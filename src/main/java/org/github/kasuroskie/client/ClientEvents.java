package org.github.kasuroskie.client;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.client.gui.RageHudOverlay;
import org.github.kasuroskie.network.LaunchNailPayload;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = GoodLooserMod.MODID, value = Dist.CLIENT)
public final class ClientEvents {
    public static final KeyMapping LAUNCH_NAIL = new KeyMapping(
            "key.good_looser.launch_nail",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.good_looser");

    private ClientEvents() {}

    @SubscribeEvent
    static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(LAUNCH_NAIL);
    }

    @SubscribeEvent
    static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAbove(
                VanillaGuiLayers.HOTBAR,
                ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "rage"),
                RageHudOverlay.INSTANCE);
    }

    @SubscribeEvent
    static void onClientTick(ClientTickEvent.Post event) {
        if (LAUNCH_NAIL.consumeClick()) {
            PacketDistributor.sendToServer(new LaunchNailPayload());
        }
    }
}
