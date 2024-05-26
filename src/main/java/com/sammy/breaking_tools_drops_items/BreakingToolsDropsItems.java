package com.sammy.breaking_tools_drops_items;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(BreakingToolsDropsItems.mod_id)
public class BreakingToolsDropsItems {
	public static final String mod_id = "breaking_tools_drops_items";
	
	public static final Logger logger = LogUtils.getLogger();
	
	public BreakingToolsDropsItems() throws Exception {
		MinecraftForge.EVENT_BUS.register(this);
		BtdsConfig.register();
	}
}
