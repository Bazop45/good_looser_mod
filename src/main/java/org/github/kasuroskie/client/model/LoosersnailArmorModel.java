package org.github.kasuroskie.client.model;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.item.LoosersnailItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LoosersnailArmorModel extends GeoModel<LoosersnailItem> {
    @Override
    public ResourceLocation getModelResource(LoosersnailItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "geo/suisouuniform.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LoosersnailItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "textures/models/armor/suisouuniformtexture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LoosersnailItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(GoodLooserMod.MODID, "anims/suisouuniform.animation.json");
    }
}
