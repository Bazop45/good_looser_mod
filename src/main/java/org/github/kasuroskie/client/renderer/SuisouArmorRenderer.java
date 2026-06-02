package org.github.kasuroskie.client.renderer;

import org.github.kasuroskie.client.model.SuisouArmorModel;
import org.github.kasuroskie.item.SuisouArmorItem;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SuisouArmorRenderer extends GeoArmorRenderer<SuisouArmorItem> {
    public SuisouArmorRenderer() {
        super(new SuisouArmorModel());
    }
}
