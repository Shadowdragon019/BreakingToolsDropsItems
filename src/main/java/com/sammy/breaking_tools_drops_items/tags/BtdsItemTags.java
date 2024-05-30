package com.sammy.breaking_tools_drops_items.tags;

import com.sammy.breaking_tools_drops_items.BreakingToolsDropsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BtdsItemTags {
	public static final TagKey<Item> higherTierCard = tag("higher_tier_card");
	
	public static TagKey<Item> tag(String name) {
		return ItemTags.create(new ResourceLocation(BreakingToolsDropsItems.mod_id, name));
	}
}
