package com.rhseung.reach.item

import com.rhseung.reach.Mod
import com.rhseung.reach.util.RegistryHelper
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

open class BasicItem(
    val name: String,
    settings: Item.Settings,
    val group: RegistryKey<ItemGroup>? = null,
    val id: Identifier = Mod.of(name),
    val registryKey: RegistryKey<Item> = RegistryKey.of(RegistryKeys.ITEM, id)
) : Item(settings.registryKey(registryKey)) {
    init {
        RegistryHelper.register(this);
    }
}