package org.github.kasuroskie.registry;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.item.ModArmorMaterials;
import org.github.kasuroskie.item.LoosersnailItem;
import org.github.kasuroskie.item.SuisouArmorItem;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GoodLooserMod.MODID);

    public static final DeferredItem<SuisouArmorItem> CHESTPLATE = ITEMS.register("good_looser_chestplate", () -> new SuisouArmorItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(5))));

    public static final DeferredItem<SuisouArmorItem> LEGGINGS = ITEMS.register("good_looser_leggings", () -> new SuisouArmorItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties()
                    .durability(ArmorItem.Type.LEGGINGS.getDurability(5))
                    .component(DataComponents.UNBREAKABLE, new Unbreakable(true))));

    public static final DeferredItem<SuisouArmorItem> BOOTS = ITEMS.register("good_looser_boots", () -> new SuisouArmorItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.BOOTS,
            new Item.Properties()
                    .durability(ArmorItem.Type.BOOTS.getDurability(5))
                    .component(DataComponents.UNBREAKABLE, new Unbreakable(true))));

    public static final DeferredItem<LoosersnailItem> NAIL_HEAD = ITEMS.register("good_looser_nail", () -> new LoosersnailItem(
            ModArmorMaterials.GOOD_LOOSER,
            ArmorItem.Type.HELMET,
            new Item.Properties()
                    .durability(ArmorItem.Type.HELMET.getDurability(5))
                    .component(DataComponents.UNBREAKABLE, new Unbreakable(true))));

    private ModItems() {}
}
