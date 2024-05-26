package com.sammy.breaking_tools_drops_items.mixins;

import com.sammy.breaking_tools_drops_items.BtdsConfig;
import net.minecraft.server.commands.ReloadCommand;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

// This is an incredibly jank way to add reload
@Mixin(ReloadCommand.class)
abstract public class ReloadCommandMixin {
	@Inject(method = "discoverNewPacks", at = @At("HEAD"))
	private static void discoverNewPacksMixin(PackRepository packRepository, WorldData worldData, Collection<String> selectedIds, CallbackInfoReturnable<Collection<String>> cir) throws Exception {
		BtdsConfig.reload();
	}
}
