package org.github.kasuroskie.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CombatDisabledEffect extends MobEffect {
    public CombatDisabledEffect() {
        super(MobEffectCategory.HARMFUL, 0x3A3A3A);
    }
}
