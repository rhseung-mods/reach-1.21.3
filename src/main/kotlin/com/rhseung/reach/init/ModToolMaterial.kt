package com.rhseung.reach.init

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.ToolComponent
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.item.Item.*
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.Registries
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey

class ModToolMaterial(
    private val incorrectBlocksForDrops: TagKey<Block>,
    private val durability: Int,
    private val speed: Float,
    private val attackDamageBonus: Float,
    private val enchantmentValue: Int,
    private val repairItems: TagKey<Item>
) {
    companion object {
        val WOOD = ModToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0f, 0.0f, 15, ItemTags.WOODEN_TOOL_MATERIALS);
        val STONE = ModToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, ItemTags.STONE_TOOL_MATERIALS);
        val IRON = ModToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14, ItemTags.IRON_TOOL_MATERIALS);
        val DIAMOND = ModToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10, ItemTags.DIAMOND_TOOL_MATERIALS);
        val GOLD = ModToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 12.0F, 0.0F, 22, ItemTags.GOLD_TOOL_MATERIALS);
        val NETHERITE = ModToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, ItemTags.NETHERITE_TOOL_MATERIALS);
        val STEEL = ModToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 400, 7.0F, 2.5F, 14, ItemTags.IRON_TOOL_MATERIALS);

        fun createAttributeModifiers(attackDamage: Float, attackDamageBonus: Float, attackSpeed: Float, reachRange: Float): AttributeModifiersComponent {
            return AttributeModifiersComponent.builder()
                .add(
                    EntityAttributes.ATTACK_DAMAGE, EntityAttributeModifier(
                        BASE_ATTACK_DAMAGE_MODIFIER_ID,
                        (attackDamage + attackDamageBonus).toDouble(),
                        EntityAttributeModifier.Operation.ADD_VALUE
                    ), AttributeModifierSlot.MAINHAND
                ).add(
                    EntityAttributes.ATTACK_SPEED, EntityAttributeModifier(
                        BASE_ATTACK_SPEED_MODIFIER_ID,
                        attackSpeed.toDouble(),
                        EntityAttributeModifier.Operation.ADD_VALUE
                    ), AttributeModifierSlot.MAINHAND
                ).add(
                    ModAttributes.REACH_DISTANCE, EntityAttributeModifier(
                        ModAttributes.BASE_REACH_DISTANCE_MODIFIER_ID,
                        reachRange.toDouble(),
                        EntityAttributeModifier.Operation.ADD_VALUE
                    ), AttributeModifierSlot.MAINHAND
                )
                .build();
        }

        fun toModToolMaterial(material: ToolMaterial): ModToolMaterial {
            return when (material) {
                ToolMaterial.WOOD -> WOOD;
                ToolMaterial.STONE -> STONE;
                ToolMaterial.IRON -> IRON;
                ToolMaterial.DIAMOND -> DIAMOND;
                ToolMaterial.GOLD -> GOLD;
                ToolMaterial.NETHERITE -> NETHERITE;
                else -> ModToolMaterial(material.incorrectBlocksForDrops, material.durability, material.speed, material.attackDamageBonus, material.enchantmentValue, material.repairItems);
            };
        }
    }

    fun incorrectBlocksForDrops(): TagKey<Block> {
        return incorrectBlocksForDrops;
    }

    fun durability(): Int {
        return durability;
    }

    fun speed(): Float {
        return speed;
    }

    fun attackDamageBonus(): Float {
        return attackDamageBonus;
    }

    fun enchantmentValue(): Int {
        return enchantmentValue;
    }

    fun repairItems(): TagKey<Item> {
        return repairItems;
    }

    fun applyBaseSettings(settings: Settings): Settings {
        return settings.apply {
            maxDamage(durability)
            repairable(repairItems)
            enchantable(enchantmentValue)
        };
    }

    fun applySwordSettings(settings: Settings, attackDamage: Float, attackSpeed: Float, reachRange: Float): Settings {
        val registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);

        return applyBaseSettings(settings).component(
            DataComponentTypes.TOOL, ToolComponent(listOf(
                ToolComponent.Rule.ofAlwaysDropping(
                    RegistryEntryList.of(Blocks.COBWEB.registryEntry), 15.0f
                ), ToolComponent.Rule.of(
                    registryEntryLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5f
                )
            ), 1.0f, 2)
        ).attributeModifiers(
            createSwordAttributeModifiers(attackDamage, attackSpeed, reachRange)
        )
    }

    fun applyToolSettings(settings: Settings, effectiveBlocks: TagKey<Block>, attackDamage: Float, attackSpeed: Float, reachRange: Float): Settings {
        val registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);

        return applyBaseSettings(settings).component(
            DataComponentTypes.TOOL, ToolComponent(listOf(
                ToolComponent.Rule.ofNeverDropping(
                    registryEntryLookup.getOrThrow(incorrectBlocksForDrops)
                ), ToolComponent.Rule.ofAlwaysDropping(
                    registryEntryLookup.getOrThrow(effectiveBlocks),
                    speed
                )
            ), 1.0f, 1)
        ).attributeModifiers(
            createToolAttributeModifiers(attackDamage, attackSpeed, reachRange)
        );
    }

    fun createSwordAttributeModifiers(attackDamage: Float, attackSpeed: Float, reachRange: Float)
        = createAttributeModifiers(attackDamage, this.attackDamageBonus, attackSpeed, reachRange);

    fun createToolAttributeModifiers(attackDamage: Float, attackSpeed: Float, reachRange: Float)
        = createAttributeModifiers(attackDamage, this.attackDamageBonus, attackSpeed, reachRange);
}