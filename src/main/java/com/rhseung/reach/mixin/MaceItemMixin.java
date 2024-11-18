package com.rhseung.reach.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.rhseung.reach.init.ModToolMaterial;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.MaceItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MaceItem.class)
public abstract class MaceItemMixin {
    @ModifyReturnValue(method = "createAttributeModifiers()Lnet/minecraft/component/type/AttributeModifiersComponent;", at = @At("RETURN"))
    private static AttributeModifiersComponent createAttributeModifiersMixin(AttributeModifiersComponent original) {
        return ModToolMaterial.Companion.createAttributeModifiers(5.0F, 0.0F, -3.4F, 0.0F);
    }
}
