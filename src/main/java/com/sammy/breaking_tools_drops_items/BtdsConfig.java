package com.sammy.breaking_tools_drops_items;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class BtdsConfig {
	public static final Path path = Path.of(FMLPaths.CONFIGDIR.get().toString() + "/btds_config.json");
	
	public static HashMap<String, HashMap<String, Integer>> itemDrops = new HashMap<>();
	
	public static void reload() {
		try {
			Gson gson = new Gson();
			if (!Files.exists(path)) {
				JsonWriter writer = new JsonWriter(new FileWriter(path.toString()));
				JsonObject defaultData = new JsonObject();
				
				//itemDrops
				var goldPickaxeDrops = new JsonObject();
				goldPickaxeDrops.addProperty("minecraft:gold_nugget", 9);
				goldPickaxeDrops.addProperty("minecraft:stick", 1);
				var itemDrops = new JsonObject();
				itemDrops.add("minecraft:golden_shovel", goldPickaxeDrops);
				defaultData.add(
						"item_drops", itemDrops
				);
				
				// closing
				gson.toJson(defaultData, writer);
				writer.close();
			}
			
			JsonReader reader = new JsonReader(new FileReader(path.toString()));
			JsonObject data = gson.fromJson(reader, JsonObject.class);
			
			for (var itemDropsEntry : data.getAsJsonObject("item_drops").entrySet()) {
				var brokenItem = itemDropsEntry.getKey();
				JsonObject brokenItemLoot = (JsonObject) itemDropsEntry.getValue();
				itemDrops.put(brokenItem, new HashMap<>());
				for (var itemEntry : brokenItemLoot.entrySet()) {
					itemDrops.get(brokenItem).put(itemEntry.getKey(), itemEntry.getValue().getAsInt());
				}
			}
		} catch (Exception e) {
			BreakingToolsDropsItems.logger.warn("Could not reload config!");
		}
	}
}
