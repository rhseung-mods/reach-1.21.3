package com.rhseung.reach.init

import com.rhseung.reach.Mod
import com.rhseung.reach.util.RegistryHelper
import net.minecraft.entity.attribute.ClampedEntityAttribute
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

object ModAttributes {
    fun load() {}

    val REACH_DISTANCE = RegistryHelper.register("reach_distance", ClampedEntityAttribute(
        "attribute.name.reach_distance", 4.5, 0.0, 64.0).setTracked(true));

    val BASE_REACH_DISTANCE_MODIFIER_ID: Identifier = Mod.of("base_reach_distance");
}