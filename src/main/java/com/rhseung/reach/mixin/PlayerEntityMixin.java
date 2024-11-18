package com.rhseung.reach.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.rhseung.reach.init.ModAttributes;
import com.rhseung.reach.util.ReachUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@ModifyReturnValue(at = @At("RETURN"), method = "getBlockInteractionRange()D")
	private double getBlockInteractionRangeMixin(double original) {
		var player = (PlayerEntity) (Object) this;
		var baseReachRange = player.getAttributeValue(ModAttributes.INSTANCE.getREACH_DISTANCE());
		var stack = player.getMainHandStack();

		return ReachUtil.INSTANCE.getReachRange(stack, player, baseReachRange);
	}

	@ModifyReturnValue(at = @At("RETURN"), method = "getEntityInteractionRange()D")
	private double getEntityInteractionRangeMixin(double original) {
		var player = (PlayerEntity) (Object) this;
		var baseReachRange = player.getAttributeValue(ModAttributes.INSTANCE.getREACH_DISTANCE());
		var stack = player.getMainHandStack();

		// EntityInteractionRange는 BlockInteractionRage보다 1.5블록 짧음
		// 바닐라 코드에서 EntityAttributes.BLOCK_INTERACTION_RANGE와 EntityAttributes.ENTITY_INTERACTION_RANGE가 실제로 그러함.
		return Math.max(0, ReachUtil.INSTANCE.getReachRange(stack, player, baseReachRange) - 1.5);
	}

	@ModifyReturnValue(at = @At("RETURN"), method = "createPlayerAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;")
    private static DefaultAttributeContainer.Builder createPlayerAttributesMixin(DefaultAttributeContainer.Builder original) {
		return original.add(ModAttributes.INSTANCE.getREACH_DISTANCE(), 4.5F);
	}
}