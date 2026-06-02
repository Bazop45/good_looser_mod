package org.github.kasuroskie.registry;

import org.github.kasuroskie.GoodLooserMod;
import org.github.kasuroskie.effect.CombatDisabledEffect;
import org.github.kasuroskie.effect.PinnedEffect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, GoodLooserMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> PINNED =
            MOB_EFFECTS.register("pinned", PinnedEffect::new);

    public static final DeferredHolder<MobEffect, MobEffect> COMBAT_DISABLED =
            MOB_EFFECTS.register("combat_disabled", CombatDisabledEffect::new);

    private ModEffects() {}
}
