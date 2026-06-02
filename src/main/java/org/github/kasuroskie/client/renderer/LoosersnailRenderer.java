package org.github.kasuroskie.client.renderer;

import org.github.kasuroskie.client.model.LoosersnailModel;
import org.github.kasuroskie.entity.NailProjectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LoosersnailRenderer extends GeoEntityRenderer<NailProjectile> {
    public LoosersnailRenderer(EntityRendererProvider.Context context) {
        super(context, new LoosersnailModel());
    }

    @Override
    public void render(NailProjectile entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw - 90.0F, partialTick, poseStack, bufferSource, packedLight);
    }
}
