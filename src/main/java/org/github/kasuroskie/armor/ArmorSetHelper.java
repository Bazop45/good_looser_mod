package org.github.kasuroskie.armor;

import org.github.kasuroskie.registry.ModItems;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public final class ArmorSetHelper {
    private ArmorSetHelper() {}

    public static boolean hasFullSet(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.CHESTPLATE.get())
                && player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.LEGGINGS.get())
                && player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.BOOTS.get());
    }
}
