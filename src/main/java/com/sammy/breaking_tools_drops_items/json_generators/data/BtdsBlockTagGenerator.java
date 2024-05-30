package com.sammy.breaking_tools_drops_items.json_generators.data;

import com.sammy.breaking_tools_drops_items.BreakingToolsDropsItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BtdsBlockTagGenerator extends BlockTagsProvider {
	public BtdsBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, BreakingToolsDropsItems.mod_id, existingFileHelper);
	}
	
	@Override
	protected void addTags(HolderLookup.@NotNull Provider pProvider) {
	
	}
}
