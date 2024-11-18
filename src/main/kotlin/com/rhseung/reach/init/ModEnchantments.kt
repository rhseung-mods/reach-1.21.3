package com.rhseung.reach.init

import com.rhseung.reach.Mod
import com.rhseung.reach.util.RegistryHelper
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentLevelBasedValue
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect
import net.minecraft.item.Item
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryEntryLookup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object ModEnchantments {
    fun load() {}

    fun of(id: String): RegistryKey<Enchantment> {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Mod.of(id));
    }

    val REACH = of("reach");

    fun bootstrap(registerable: Registerable<Enchantment>) {
        val itemLookup: RegistryEntryLookup<Item> = registerable.getRegistryLookup(RegistryKeys.ITEM);

        RegistryHelper.register(registerable, REACH, Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(ModTags.HANDHELDS_ENCHANTABLE),
                2,  // probability of showing up in the enchantment table
                3,
                Enchantment.leveledCost(15, 9),  // base cost
                Enchantment.leveledCost(65, 9), // max cost
                4,  // anvil applying cost
                AttributeModifierSlot.MAINHAND
            )
        ).addNonListEffect(
            ModEnchantmentEffectComponentTypes.REACH_RANGE,
            AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.0F, 0.5F))
        ));
    }
}