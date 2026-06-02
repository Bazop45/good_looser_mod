package org.github.kasuroskie.client;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.client.gui.RageHudOverlay;
import org.github.kasuroskie.network.LaunchNailPayload;
import org.github.kasuroskie.registry.ModEffects;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
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
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            return;
        }

        if (LAUNCH_NAIL.consumeClick()) {
            PacketDistributor.sendToServer(new LaunchNailPayload());
        }

        if (mc.player.hasEffect(ModEffects.PINNED) && mc.player.tickCount % 10 == 0) {
            var random = mc.level.random;
            Vec3 pos = mc.player.position();
            for (int i = 0; i < 6; i++) {
                double angle = random.nextDouble() * Math.PI * 2;
                double radius = 1.0 + random.nextDouble() * 1.0;
                double x = pos.x + Math.cos(angle) * radius;
                double z = pos.z + Math.sin(angle) * radius;
                double y = pos.y + random.nextDouble() * 2.2;
                mc.level.addParticle(ParticleTypes.SQUID_INK, x, y, z, 0, 0.01, 0);
            }
        }
    }
}
