package org.github.kasuroskie.registry;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.entity.NailProjectile;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, GoodLooserMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<NailProjectile>> NAIL =
            ENTITY_TYPES.register("nail", () -> EntityType.Builder.<NailProjectile>of(NailProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("nail"));

    private ModEntities() {}
}
