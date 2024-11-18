package com.rhseung.reach.init

import com.rhseung.reach.item.tool.*
import net.minecraft.item.ItemGroups

object ModItems {
    fun load() {}

    val STEEL_SWORD = BasicSwordItem(
        "steel_sword",
        ModToolMaterial.STEEL,
        attackDamage = 3.0F,
        attackSpeed = -2.4F,
        reachRange = 0.5F,
        group = ItemGroups.COMBAT
    );

    val STEEL_PICKAXE = BasicPickaxeItem(
        "steel_pickaxe",
        ModToolMaterial.STEEL,
        attackDamage = 1.0F,
        attackSpeed = -2.8F,
        reachRange = 0.0F,
        group = ItemGroups.TOOLS
    );

    val STEEL_AXE = BasicAxeItem(
        "steel_axe",
        ModToolMaterial.STEEL,
        attackDamage = 6.0F,
        attackSpeed = -3.1F,
        reachRange = -0.5F,
        group = ItemGroups.TOOLS
    );

    val STEEL_SHOVEL = BasicShovelItem(
        "steel_shovel",
        ModToolMaterial.STEEL,
        attackDamage = 1.5F,
        attackSpeed = -3.0F,
        reachRange = 0.0F,
        group = ItemGroups.TOOLS
    );

    val STEEL_HOE = BasicHoeItem(
        "steel_hoe",
        ModToolMaterial.STEEL,
        attackDamage = -2.0F,
        attackSpeed = -1.0F,
        reachRange = 0.0F,
        group = ItemGroups.TOOLS
    );
}