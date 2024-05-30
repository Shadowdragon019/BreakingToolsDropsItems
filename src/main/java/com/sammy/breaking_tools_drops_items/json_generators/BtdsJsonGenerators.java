package com.sammy.breaking_tools_drops_items.json_generators;

import com.sammy.breaking_tools_drops_items.BreakingToolsDropsItems;
import com.sammy.breaking_tools_drops_items.json_generators.data.BtdsBlockTagGenerator;
import com.sammy.breaking_tools_drops_items.json_generators.data.BtdsItemTagGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BreakingToolsDropsItems.mod_id, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BtdsJsonGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		var generator = event.getGenerator();
		var output = generator.getPackOutput();
		var existingFileHelper = event.getExistingFileHelper();
		var lookupProvider = event.getLookupProvider();
		
		var blockTagGenerator = generator.addProvider(
				event.includeServer(),
				new BtdsBlockTagGenerator(output, lookupProvider, existingFileHelper)
		);
		generator.addProvider(event.includeServer(), new BtdsItemTagGenerator(
				output, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper
		));
	}
}
