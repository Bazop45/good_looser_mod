package org.github.kasuroskie.registry;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.item.ModArmorMaterials;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GoodLooserMod.MODID);

    public static final DeferredItem<ArmorItem> CHESTPLATE = ITEMS.register("good_looser_chestplate", () -> new ArmorItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(5))));

    public static final DeferredItem<ArmorItem> LEGGINGS = ITEMS.register("good_looser_leggings", () -> new ArmorItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties()
                    .durability(ArmorItem.Type.LEGGINGS.getDurability(5))
                    .component(DataComponents.UNBREAKABLE, new Unbreakable(true))));

    public static final DeferredItem<ArmorItem> BOOTS = ITEMS.register("good_looser_boots", () -> new ArmorItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.BOOTS,
            new Item.Properties()
                    .durability(ArmorItem.Type.BOOTS.getDurability(5))
                    .component(DataComponents.UNBREAKABLE, new Unbreakable(true))));

    private ModItems() {}
}
