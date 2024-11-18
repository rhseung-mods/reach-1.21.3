package com.rhseung.reach.data

import com.rhseung.reach.Mod.MOD_ID
import com.rhseung.reach.init.ModEnchantmentEffectComponentTypes
import com.rhseung.reach.init.ModEnchantments
import com.rhseung.reach.init.ModTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentLevelBasedValue
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect
import net.minecraft.item.Item
import net.minecraft.registry.RegistryEntryLookup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class RegistryProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricDynamicRegistryProvider(output, registriesFuture) {

    override fun getName(): String {
        return "$MOD_ID's Registry Provider";
    }

    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.ENCHANTMENT));
    }
}