package com.rhseung.reach.item

import com.rhseung.reach.Mod
import com.rhseung.reach.init.ModToolMaterial
import net.minecraft.block.Block
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

open class BasicMiningToolItem(
    name: String,
    material: ModToolMaterial,
    effectiveBlocks: TagKey<Block>,
    attackDamage: Float,
    attackSpeed: Float,
    reachRange: Float,
    settings: Settings = Settings(),
    group: RegistryKey<ItemGroup>? = null,
    id: Identifier = Mod.of(name),
) : BasicItem(
    name,
    material.applyToolSettings(settings, effectiveBlocks, attackDamage, attackSpeed, reachRange),
    group,
    id
) {
    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return true;
    }

    override fun postDamageEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
        stack.damage(2, attacker, EquipmentSlot.MAINHAND);
    }
}