package com.rhseung.reach.util

import com.rhseung.reach.init.ModEnchantmentEffectComponentTypes
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ItemEnchantmentsComponent
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.entry.RegistryEntry
import org.apache.commons.lang3.mutable.MutableFloat

object ReachUtil {
    /**
     * [net.minecraft.enchantment.EnchantmentHelper.forEachEnchantment]이 private라서 재구현함
     */
    fun forEachEnchantment(stack: ItemStack, consumer: (enchantment: RegistryEntry<Enchantment>, level: Int) -> Unit) {
        val itemEnchantmentsComponent = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

        for (entry in itemEnchantmentsComponent.enchantmentEntries) {
            consumer(entry.key, entry.intValue);
        }
    }

    fun getReachRange(stack: ItemStack, user: LivingEntity, baseReachRange: Double): Double {
        val value = MutableFloat(baseReachRange);
        forEachEnchantment(stack) { enchantment, level -> enchantment.value().modifyValue(ModEnchantmentEffectComponentTypes.REACH_RANGE, user.random, level, value) }
        return value.toDouble().coerceAtLeast(0.0);
    }
}