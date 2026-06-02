package org.github.kasuroskie.client.renderer;

import org.github.kasuroskie.client.model.LoosersnailArmorModel;
import org.github.kasuroskie.item.LoosersnailItem;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class LoosersnailArmorRenderer extends GeoArmorRenderer<LoosersnailItem> {
    public LoosersnailArmorRenderer() {
        super(new LoosersnailArmorModel());
    }
}
