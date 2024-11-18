package com.rhseung.reach.data

import com.rhseung.reach.init.ModItems
import com.rhseung.reach.init.ModTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class ItemTagProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {

    override fun configure(wrapper: RegistryWrapper.WrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(ModItems.STEEL_SWORD)
        ;

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(ModItems.STEEL_PICKAXE)
        ;

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(ModItems.STEEL_AXE)
        ;

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(ModItems.STEEL_SHOVEL)
        ;

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(ModItems.STEEL_HOE)
        ;

        getOrCreateTagBuilder(ModTags.HANDHELDS_ENCHANTABLE)
            .forceAddTag(ItemTags.MINING_LOOT_ENCHANTABLE)
            .forceAddTag(ItemTags.WEAPON_ENCHANTABLE)
            .forceAddTag(ItemTags.TRIDENT_ENCHANTABLE)
        ;
    }
}