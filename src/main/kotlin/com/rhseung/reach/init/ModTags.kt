package com.rhseung.reach.init

import com.rhseung.reach.Mod
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey

object ModTags {
    fun load() {}

    fun of(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Mod.of(id));
    }

    val HANDHELDS_ENCHANTABLE = of("enchantable/handhelds");
}