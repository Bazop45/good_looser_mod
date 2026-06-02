package org.github.kasuroskie.combat;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.ModConstants;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;

public final class NailStackState {
    private static final String HIT_TIMES = GoodLooserMod.MODID + "_nail_hit_times";

    private NailStackState() {}

    public static void recordHit(LivingEntity entity, long gameTime) {
        ListTag list = getHitList(entity);
        list.add(LongTag.valueOf(gameTime));
        pruneOld(list, gameTime);
        entity.getPersistentData().put(HIT_TIMES, list);
    }

    public static int countHitsInWindow(LivingEntity entity, long gameTime) {
        ListTag list = getHitList(entity);
        pruneOld(list, gameTime);
        entity.getPersistentData().put(HIT_TIMES, list);
        return list.size();
    }

    public static boolean shouldDisableCombat(LivingEntity entity, long gameTime) {
        return countHitsInWindow(entity, gameTime) >= ModConstants.NAIL_STACK_THRESHOLD;
    }

    private static ListTag getHitList(LivingEntity entity) {
        CompoundTag data = entity.getPersistentData();
        if (data.contains(HIT_TIMES, ListTag.TAG_LIST)) {
            return data.getList(HIT_TIMES, LongTag.TAG_LONG).copy();
        }
        return new ListTag();
    }

    private static void pruneOld(ListTag list, long gameTime) {
        long cutoff = gameTime - ModConstants.NAIL_STACK_WINDOW_TICKS;
        for (int i = list.size() - 1; i >= 0; i--) {
            Tag entry = list.get(i);
            if (entry instanceof LongTag longTag && longTag.getAsLong() < cutoff) {
                list.remove(i);
            }
        }
    }
}
