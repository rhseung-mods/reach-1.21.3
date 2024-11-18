package com.rhseung.reach

import com.rhseung.reach.data.*
import com.rhseung.reach.init.ModEnchantments
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys

object ModData : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack();

		pack.addProvider(::LanguageProvider);
		pack.addProvider(::ModelProvider);
		pack.addProvider(::RegistryProvider);
		pack.addProvider(::ItemTagProvider);
		pack.addProvider(::EnchantmentTagProvider);
	}

	override fun buildRegistry(registryBuilder: RegistryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);
	}
}