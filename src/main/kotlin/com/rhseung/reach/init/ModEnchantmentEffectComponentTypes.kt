package com.rhseung.reach.init

import com.rhseung.reach.util.RegistryHelper
import net.minecraft.component.ComponentType
import net.minecraft.enchantment.effect.EnchantmentValueEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.util.function.UnaryOperator

object ModEnchantmentEffectComponentTypes {
    fun load() {}

    val REACH_RANGE: ComponentType<EnchantmentValueEffect>
        = RegistryHelper.register("reach_distance") { it.codec(EnchantmentValueEffect.CODEC); }
}