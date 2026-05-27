package org.github.kasuroskie;

import org.github.kasuroskie.network.ModNetworking;
import org.github.kasuroskie.rage.RageEvents;
import org.github.kasuroskie.registry.ModAttachments;
import org.github.kasuroskie.registry.ModEffects;
import org.github.kasuroskie.registry.ModEntities;
import org.github.kasuroskie.item.ModArmorMaterials;
import org.github.kasuroskie.registry.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(GoodLooserMod.MODID)
public class GoodLooserMod {
    public static final String MODID = "good_looser";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB =
            CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.good_looser"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.CHESTPLATE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.CHESTPLATE.get());
                        output.accept(ModItems.LEGGINGS.get());
                        output.accept(ModItems.BOOTS.get());
                    })
                    .build());

    public GoodLooserMod(IEventBus modEventBus, ModContainer modContainer) {
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(ModNetworking::registerPayloads);

        NeoForge.EVENT_BUS.register(RageEvents.class);
    }
}
