package com.sammy.breaking_tools_drops_items.mixins;

import com.sammy.breaking_tools_drops_items.BreakingToolsDropsItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.protocol.game.ServerboundChangeDifficultyPacket;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity implements Attackable, net.minecraftforge.common.extensions.IForgeLivingEntity {
	public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	}
	
	/*@Inject(method = "breakItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;spawnItemParticles(Lnet/minecraft/world/item/ItemStack;I)V"))
	private void breakItemMixin(ItemStack itemStack, CallbackInfo ci) {
		var level = level();
		var position = position();
		if (level instanceof ClientLevel) {
			// Do not send "CLIENT BOUND" packets
			//level.sendPacketToServer(new ClientboundAddEntityPacket(
			//		new ItemEntity(level, position.x, position.y, position.y, new ItemStack(Items.GOLD_NUGGET, 64))
			//));
			level.sendPacketToServer(new ServerboundChangeDifficultyPacket(Difficulty.HARD));
			// I do not see any packets to add entities... oh no
			((ClientLevel) level).putNonPlayerEntity(344234234, //I do not know what to put here. I'm expecting this to break shit.
					new ItemEntity(level, position.x, position.y, position.y, new ItemStack(Items.GOLD_NUGGET, 64))
			);
		}
	}*/
	/*@Inject(method = "handleEntityEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;breakItem(Lnet/minecraft/world/item/ItemStack;)V"))
	private void handleEntityEvent(byte pId, CallbackInfo ci) {
		BreakingToolsDropsItems.logger.info("BROKEEEEN~~~");
		BreakingToolsDropsItems.logger.info(level().toString());
	}*/
}
