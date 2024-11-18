package com.rhseung.reach.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.rhseung.reach.init.ModToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ToolMaterial.class)
public abstract class ToolMaterialMixin {
    @ModifyReturnValue(
        method = "applyToolSettings(Lnet/minecraft/item/Item$Settings;Lnet/minecraft/registry/tag/TagKey;FF)Lnet/minecraft/item/Item$Settings;",
        at = @At("RETURN")
    )
    private Item.Settings attributeModifiersMixin(Item.Settings original,
        @Local(ordinal = 0, argsOnly = true) Item.Settings settings,
        @Local(ordinal = 0, argsOnly = true) TagKey<Block> effectiveBlocks,
        @Local(ordinal = 0, argsOnly = true) float attackDamage,
        @Local(ordinal = 1, argsOnly = true) float attackSpeed
    ) {
        var that = (ToolMaterial) (Object) this;
        ModToolMaterial material = ModToolMaterial.Companion.toModToolMaterial(that);

        return material.applyToolSettings(settings, effectiveBlocks, attackDamage, attackSpeed, 0f);
    }

    @ModifyReturnValue(
            method = "applySwordSettings(Lnet/minecraft/item/Item$Settings;FF)Lnet/minecraft/item/Item$Settings;",
            at = @At("RETURN")
    )
    private Item.Settings attributeModifiersMixin2(Item.Settings original,
        @Local(ordinal = 0, argsOnly = true) Item.Settings settings,
        @Local(ordinal = 0, argsOnly = true) float attackDamage,
        @Local(ordinal = 1, argsOnly = true) float attackSpeed
    ) {
        var that = (ToolMaterial) (Object) this;
        ModToolMaterial material = ModToolMaterial.Companion.toModToolMaterial(that);

        return material.applySwordSettings(settings, attackDamage, attackSpeed, 0f);
    }
}
