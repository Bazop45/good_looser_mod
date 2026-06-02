package org.github.kasuroskie.effect;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.ModConstants;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class PinnedEffect extends MobEffect {
    private static final String PIN_LOCKED = GoodLooserMod.MODID + "_pin_locked";
    private static final String PIN_TICKS = GoodLooserMod.MODID + "_pin_ticks";
    private static final String PIN_X = GoodLooserMod.MODID + "_pin_x";
    private static final String PIN_Y = GoodLooserMod.MODID + "_pin_y";
    private static final String PIN_Z = GoodLooserMod.MODID + "_pin_z";

    public PinnedEffect() {
        super(MobEffectCategory.HARMFUL, 0x4A4A4A);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        var data = entity.getPersistentData();
        boolean locked = data.getBoolean(PIN_LOCKED);

        if (!locked) {
            int ticks = data.getInt(PIN_TICKS);
            data.putInt(PIN_TICKS, ticks + 1);
            entity.setDeltaMovement(0, -ModConstants.PIN_FALL_SPEED, 0);
            entity.fallDistance = 0.0F;
            entity.hurtMarked = true;
            if (entity.onGround() || ticks > 20) {
                data.putBoolean(PIN_LOCKED, true);
                data.putDouble(PIN_X, entity.getX());
                data.putDouble(PIN_Y, entity.getY());
                data.putDouble(PIN_Z, entity.getZ());
                entity.setDeltaMovement(Vec3.ZERO);
                locked = true;
            }
        }

        if (locked) {
            lockPosition(entity, data);
        }

        return true;
    }

    public static void lockPosition(LivingEntity entity, net.minecraft.nbt.CompoundTag data) {
        if (entity instanceof Player) {
            entity.setDeltaMovement(Vec3.ZERO);
        } else {
            entity.setPos(data.getDouble(PIN_X), data.getDouble(PIN_Y), data.getDouble(PIN_Z));
            entity.setDeltaMovement(Vec3.ZERO);
            entity.hurtMarked = true;
        }
        entity.fallDistance = 0.0F;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    public static void clearPinData(LivingEntity entity) {
        var data = entity.getPersistentData();
        data.remove(PIN_LOCKED);
        data.remove(PIN_TICKS);
        data.remove(PIN_X);
        data.remove(PIN_Y);
        data.remove(PIN_Z);
    }

    public static boolean isPinLocked(LivingEntity entity) {
        return entity.getPersistentData().getBoolean(PIN_LOCKED);
    }
}
