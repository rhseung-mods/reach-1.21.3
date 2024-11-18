package com.rhseung.reach.data

import com.rhseung.reach.init.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class LanguageProvider(
    output: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>,
    languageCode: String = "en_us"
): FabricLanguageProvider(output, languageCode, registryLookup) {
    override fun generateTranslations(registryLookup: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder) {
        translationBuilder.add(ModItems.STEEL_SWORD, "Steel Sword");
        translationBuilder.add(ModItems.STEEL_PICKAXE, "Steel Pickaxe");
        translationBuilder.add(ModItems.STEEL_AXE, "Steel Axe");
        translationBuilder.add(ModItems.STEEL_SHOVEL, "Steel Shovel");
        translationBuilder.add(ModItems.STEEL_HOE, "Steel Hoe");

        translationBuilder.addEnchantment(ModEnchantments.REACH, "Reach");
        translationBuilder.add(ModAttributes.REACH_DISTANCE, "Reach Distance");
        translationBuilder.add(ModTags.HANDHELDS_ENCHANTABLE, "Enchantable Handhelds");
    }
}