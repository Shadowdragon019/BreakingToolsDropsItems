package com.sammy.breaking_tools_drops_items.mixins;

import com.sammy.breaking_tools_drops_items.BtdsConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
abstract class ItemStackMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<ItemStack> implements net.minecraftforge.common.extensions.IForgeItemStack {
	protected ItemStackMixin(Class<ItemStack> baseClass) {
		super(baseClass);
	}
	
	@Unique
	public String btds$getResourceLocation() {
		return ForgeRegistries.ITEMS.getKey(((ItemStack) (Object) this).getItem()).toString();
	}
	
	@Inject(method = "hurt", at = @At("RETURN"))
	private void hurtMixin(int amount, RandomSource random, ServerPlayer player, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue()) {
			var drops = BtdsConfig.drops.get(btds$getResourceLocation());
			for (var entry : drops.entrySet()) {
				player.spawnAtLocation(new ItemStack(
						ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry.getKey())),
						entry.getValue()
				));
			}
			
			var commands = BtdsConfig.commands.get(btds$getResourceLocation());
			for (var command : commands) {
				player.serverLevel().getServer().getCommands().performPrefixedCommand(
						player.serverLevel().getServer().createCommandSourceStack(),
						command
				);
			}
		}
	}
}
