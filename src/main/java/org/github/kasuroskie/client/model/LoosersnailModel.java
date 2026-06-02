package org.github.kasuroskie.client.model;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.entity.NailProjectile;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LoosersnailModel extends GeoModel<NailProjectile> {
    @Override
    public ResourceLocation getModelResource(NailProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "geo/loosersnail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NailProjectile animatable) {
        if (animatable.isEmpowered()) {
            return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "textures/entity/projectile/nailtexturelaunchtexture.png");
        }
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "textures/entity/projectile/nailtexturelaunchtexture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NailProjectile animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "anims/loosersnail.animation.json");
    }
}
