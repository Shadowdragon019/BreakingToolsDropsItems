package com.sammy.breaking_tools_drops_items.mixins;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
abstract class ItemStackMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<ItemStack> implements net.minecraftforge.common.extensions.IForgeItemStack {
	protected ItemStackMixin(Class<ItemStack> baseClass) {
		super(baseClass);
	}
	
	@Inject(method = "hurt", at = @At("RETURN"))
	private void hurtMixin(int amount, RandomSource random, ServerPlayer player, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue()) {
			player.spawnAtLocation(new ItemStack(Items.GOLD_NUGGET, 64));
		}
	}
}
