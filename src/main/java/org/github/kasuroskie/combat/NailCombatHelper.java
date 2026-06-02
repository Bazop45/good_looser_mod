package org.github.kasuroskie.combat;

import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.registry.ModEffects;
import org.github.kasuroskie.util.EffectHelper;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class NailCombatHelper {
    private NailCombatHelper() {}

    public static void onNailHit(LivingEntity target, long gameTime) {
        chipRandomArmor(target);
        NailStackState.recordHit(target, gameTime);
        if (NailStackState.shouldDisableCombat(target, gameTime)) {
            EffectHelper.applyHidden(target, ModEffects.COMBAT_DISABLED, ModConstants.COMBAT_DISABLED_DURATION_TICKS);
        }
        EffectHelper.applyHidden(target, ModEffects.PINNED, ModConstants.EFFECT_DURATION_TICKS);
    }

    public static void chipRandomArmor(LivingEntity target) {
        List<EquipmentSlot> slots = new ArrayList<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.HUMANOID_ARMOR) {
                continue;
            }
            ItemStack stack = target.getItemBySlot(slot);
            if (stack.isEmpty() || !stack.isDamageableItem()) {
                continue;
            }
            if (stack.has(DataComponents.UNBREAKABLE)) {
                continue;
            }
            slots.add(slot);
        }
        if (slots.isEmpty()) {
            return;
        }
        EquipmentSlot chosen = slots.get(ThreadLocalRandom.current().nextInt(slots.size()));
        ItemStack armor = target.getItemBySlot(chosen);
        int max = armor.getMaxDamage();
        int loss = Math.max(1, (int) Math.ceil(max * ModConstants.ARMOR_CHIP_FRACTION));
        armor.hurtAndBreak(loss, target, chosen);
    }

    public static boolean isCombatDisabled(LivingEntity entity) {
        return entity.hasEffect(ModEffects.COMBAT_DISABLED);
    }
}
