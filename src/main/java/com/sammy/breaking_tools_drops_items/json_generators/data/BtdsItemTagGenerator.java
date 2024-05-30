package com.sammy.breaking_tools_drops_items.json_generators.data;

import com.sammy.breaking_tools_drops_items.BreakingToolsDropsItems;
import com.sammy.breaking_tools_drops_items.tags.BtdsItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BtdsItemTagGenerator extends ItemTagsProvider {
	public BtdsItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, p_275322_, BreakingToolsDropsItems.mod_id, existingFileHelper);
	}
	
	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		tag(BtdsItemTags.higherTierCard);
	}
}
