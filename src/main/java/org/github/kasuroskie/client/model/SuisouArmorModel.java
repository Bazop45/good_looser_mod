package org.github.kasuroskie.client.model;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.item.SuisouArmorItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SuisouArmorModel extends GeoModel<SuisouArmorItem> {
    @Override
    public ResourceLocation getModelResource(SuisouArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "geo/suisouuniform.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuisouArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "textures/models/armor/suisouuniformtexture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SuisouArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "anims/suisouuniform.animation.json");
    }
}
