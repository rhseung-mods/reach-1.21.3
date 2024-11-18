package com.rhseung.reach.item.tool

import com.rhseung.reach.Mod
import com.rhseung.reach.init.ModToolMaterial
import com.rhseung.reach.item.BasicMiningToolItem
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.Identifier

open class BasicAxeItem(
    name: String,
    material: ModToolMaterial,
    attackDamage: Float,
    attackSpeed: Float,
    reachRange: Float = -0.5f,
    settings: Settings = Settings(),
    group: RegistryKey<ItemGroup>? = null,
    id: Identifier = Mod.of(name),
) : BasicMiningToolItem(name, material, BlockTags.AXE_MINEABLE, attackDamage, attackSpeed, reachRange, settings, group, id) {
}