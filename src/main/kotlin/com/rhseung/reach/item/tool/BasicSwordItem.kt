package com.rhseung.reach.item.tool

import com.rhseung.reach.Mod
import com.rhseung.reach.init.ModToolMaterial
import com.rhseung.reach.item.BasicItem
import net.minecraft.block.BlockState
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class BasicSwordItem(
    name: String,
    material: ModToolMaterial,
    attackDamage: Float,
    attackSpeed: Float,
    reachRange: Float = 0.5f,
    settings: Settings = Settings(),
    group: RegistryKey<ItemGroup>? = null,
    id: Identifier = Mod.of(name),
) : BasicItem(
    name,
    material.applySwordSettings(settings, attackDamage, attackSpeed, reachRange),
    group,
    id
) {
    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean {
        return !miner.isCreative;
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return true;
    }

    override fun postDamageEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}