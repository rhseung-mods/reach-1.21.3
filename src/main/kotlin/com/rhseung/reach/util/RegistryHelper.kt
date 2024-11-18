package com.rhseung.reach.util

import com.rhseung.reach.Mod
import com.rhseung.reach.item.BasicItem
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider.Entries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.component.ComponentType
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registerable
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.entry.RegistryEntry
import java.util.function.UnaryOperator

object RegistryHelper {
    fun register(id: String, attribute: EntityAttribute): RegistryEntry<EntityAttribute> {
        return Registry.registerReference(Registries.ATTRIBUTE, Mod.of(id), attribute)
    }

    fun <T> register(id: String, builderOperator: UnaryOperator<ComponentType.Builder<T>>): ComponentType<T> {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, id, builderOperator.apply(ComponentType.builder()).build());
    }

    fun register(registerable: Registerable<Enchantment>, key: RegistryKey<Enchantment>, builder: Enchantment.Builder): RegistryEntry.Reference<Enchantment> {
        return registerable.register(key, builder.build(key.value));
    }

    fun register(entry: BasicItem): BasicItem {
        val ret = Registry.register(Registries.ITEM, entry.id, entry);

        entry.group?.let { ItemGroupEvents.modifyEntriesEvent(it)
            .register { group -> group.add(ret) }
        }

        return ret;
    }

    fun register(entry: Item, path: String, group: RegistryKey<ItemGroup>? = null): Item {
        val ret = Registry.register(Registries.ITEM, Mod.of(path), entry);

        group?.let { ItemGroupEvents.modifyEntriesEvent(it)
            .register { group -> group.add(ret) }
        }

        return ret;
    }
}