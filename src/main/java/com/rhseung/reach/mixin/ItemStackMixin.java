package com.rhseung.reach.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.rhseung.reach.init.ModAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Redirect(
        method = "appendAttributeModifierTooltip(Ljava/util/function/Consumer;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/entity/attribute/EntityAttributeModifier;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/attribute/EntityAttributeModifier;value()D"
        )
    )
    private double dMixin(EntityAttributeModifier modifier, @Local(ordinal = 0, argsOnly = true) @Nullable PlayerEntity player) {
        double ret = modifier.value();

        if (player != null) {
            if (modifier.idMatches(ModAttributes.INSTANCE.getBASE_REACH_DISTANCE_MODIFIER_ID())) {
                ret += player.getAttributeBaseValue(ModAttributes.INSTANCE.getREACH_DISTANCE());
            }
        }

        return ret;
    }

    @ModifyVariable(
        method = "appendAttributeModifierTooltip(Ljava/util/function/Consumer;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/entity/attribute/EntityAttributeModifier;)V",
        at = @At("STORE"), ordinal = 0
    )
    private boolean blMixin(boolean original, @Local(ordinal = 0, argsOnly = true) @Nullable PlayerEntity player, @Local(ordinal = 0, argsOnly = true) EntityAttributeModifier modifier) {
        if (player != null) {
            if (modifier.idMatches(ModAttributes.INSTANCE.getBASE_REACH_DISTANCE_MODIFIER_ID())) {
                return true;
            }
        }

        return original;
    }
}
