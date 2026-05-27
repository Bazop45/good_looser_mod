package org.github.kasuroskie.effect;

import org.github.kasuroskie.GoodLooserMod;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class PinnedEffect extends MobEffect {
    private static final String PIN_X = GoodLooserMod.MODID + "_pin_x";
    private static final String PIN_Y = GoodLooserMod.MODID + "_pin_y";
    private static final String PIN_Z = GoodLooserMod.MODID + "_pin_z";

    public PinnedEffect() {
        super(MobEffectCategory.HARMFUL, 0x4A4A4A);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        var data = entity.getPersistentData();
        if (!data.contains(PIN_X)) {
            data.putDouble(PIN_X, entity.getX());
            data.putDouble(PIN_Y, entity.getY());
            data.putDouble(PIN_Z, entity.getZ());
        }
        Vec3 frozen = new Vec3(data.getDouble(PIN_X), data.getDouble(PIN_Y), data.getDouble(PIN_Z));
        entity.setPos(frozen);
        entity.setDeltaMovement(Vec3.ZERO);
        entity.hurtMarked = true;
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    public static void clearPinData(LivingEntity entity) {
        var data = entity.getPersistentData();
        data.remove(PIN_X);
        data.remove(PIN_Y);
        data.remove(PIN_Z);
    }
}
