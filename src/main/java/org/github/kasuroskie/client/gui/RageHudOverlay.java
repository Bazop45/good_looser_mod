package org.github.kasuroskie.client.gui;

import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.registry.ModAttachments;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;

public final class RageHudOverlay implements LayeredDraw.Layer {
    public static final RageHudOverlay INSTANCE = new RageHudOverlay();
    private static float clientRage;

    private RageHudOverlay() {}

    public static void setClientRage(float rage) {
        clientRage = rage;
    }

    @Override
    public void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.options.hideGui) {
            return;
        }

        float rage = clientRage;
        if (minecraft.player.hasData(ModAttachments.RAGE.get())) {
            rage = minecraft.player.getData(ModAttachments.RAGE.get());
        }

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();
        int barWidth = 80;
        int barHeight = 6;
        int x = screenWidth / 2 - barWidth / 2;
        int y = screenHeight - 50;

        graphics.fill(x - 1, y - 1, x + barWidth + 1, y + barHeight + 1, 0xFF000000);
        graphics.fill(x, y, x + barWidth, y + barHeight, 0xFF333333);

        int fillWidth = (int) (barWidth * (rage / ModConstants.MAX_RAGE));
        int color = rage >= ModConstants.MAX_RAGE ? 0xFFFF4444 : 0xFFCC6622;
        graphics.fill(x, y, x + fillWidth, y + barHeight, color);
    }
}
