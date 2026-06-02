package org.github.kasuroskie.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.model.HumanoidModel;
import org.github.kasuroskie.client.renderer.LoosersnailArmorRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class LoosersnailItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public LoosersnailItem(Holder<ArmorMaterial> material, Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<LoosersnailItem> state) {
        state.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private LoosersnailArmorRenderer renderer;

            @Override
            @SuppressWarnings("removal")
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(T livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<T> original) {
                if (this.renderer == null) {
                    this.renderer = new LoosersnailArmorRenderer();
                }
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }
}
