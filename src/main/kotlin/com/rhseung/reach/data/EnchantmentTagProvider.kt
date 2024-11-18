package com.rhseung.reach.data

import com.rhseung.reach.init.ModEnchantments
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.EnchantmentTags
import java.util.concurrent.CompletableFuture

class EnchantmentTagProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.EnchantmentTagProvider(output, completableFuture) {

    override fun configure(wrapper: RegistryWrapper.WrapperLookup) {
        getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
            .addOptional(ModEnchantments.REACH)
        ;

        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
            .addOptional(ModEnchantments.REACH)
        ;

        getOrCreateTagBuilder(EnchantmentTags.ON_RANDOM_LOOT)
            .addOptional(ModEnchantments.REACH)
        ;

        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE)
            .addOptional(ModEnchantments.REACH)
        ;
    }
}