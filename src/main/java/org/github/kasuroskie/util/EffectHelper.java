package org.github.kasuroskie.util;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public final class EffectHelper {
    private EffectHelper() {}

    public static void applyHidden(LivingEntity entity, Holder<MobEffect> effect, int durationTicks) {
        entity.addEffect(new MobEffectInstance(effect, durationTicks, 0, false, false, false));
    }

    public static void applyHidden(LivingEntity entity, Holder<MobEffect> effect, int durationTicks, int amplifier) {
        entity.addEffect(new MobEffectInstance(effect, durationTicks, amplifier, false, false, false));
    }
}
