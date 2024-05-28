package com.sammy.breaking_tools_drops_items;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BtdsConfig {
	public static final Path path = Path.of(FMLPaths.CONFIGDIR.get().toString() + "/btds_config.json");
	
	public static HashMap<String, HashMap<String, Integer>> drops = new HashMap<>();
	public static HashMap<String, List<String>> commands = new HashMap<>();
	public static void reload() {
		try {
			Gson gson = new Gson();
			if (!Files.exists(path)) {
				JsonWriter writer = new JsonWriter(new FileWriter(path.toString()));
				JsonObject defaultData = new JsonObject();
				
				var shovelDrops = new JsonObject();
				shovelDrops.addProperty("minecraft:golden_shovel", 10);
				var shovelCommands = new JsonArray();
				shovelCommands.add("say heeeeeeeheeehe");
				var shovelConfig = new JsonObject();
				shovelConfig.add("drops", shovelDrops);
				shovelConfig.add("commands", shovelCommands);
				defaultData.add("minecraft:golden_shovel", shovelConfig);
				
				// closing
				gson.toJson(defaultData, writer);
				writer.close();
			}
			
			JsonReader reader = new JsonReader(new FileReader(path.toString()));
			JsonObject data = gson.fromJson(reader, JsonObject.class);
			
			for (var itemConfig : data.entrySet()) {
				var item = itemConfig.getKey();
				var config = itemConfig.getValue().getAsJsonObject();
				var _drops = config.getAsJsonObject("drops");
				var _commands = config.getAsJsonArray("commands");
				if (_drops != null) {
					drops.put(item, new HashMap<>());
					for (var entry : _drops.entrySet()) {
						drops.get(item).put(entry.getKey(), entry.getValue().getAsInt());
					}
				}
				if (_commands != null) {
					commands.put(item, new ArrayList<>());
					for (var command : _commands) {
						commands.get(item).add(command.getAsString());
					}
				}
			}
			
		} catch (Exception e) {
			BreakingToolsDropsItems.logger.warn("Could not reload config!");
		}
	}
}
