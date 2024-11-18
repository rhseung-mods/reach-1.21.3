package com.rhseung.reach

import com.rhseung.reach.init.*
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object Mod : ModInitializer {
	const val MOD_ID = "reach";
    val LOGGER = LoggerFactory.getLogger(MOD_ID);

	fun of(path: String): Identifier = Identifier.of(MOD_ID, path);

	override fun onInitialize() {
		ModItems.load();
		ModAttributes.load();
		ModEnchantments.load();
		ModEnchantmentEffectComponentTypes.load();
		ModTags.load();
	}
}