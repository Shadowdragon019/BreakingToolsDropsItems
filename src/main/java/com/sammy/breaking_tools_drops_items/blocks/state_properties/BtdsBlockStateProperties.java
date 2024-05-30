package com.sammy.breaking_tools_drops_items.blocks.state_properties;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class BtdsBlockStateProperties extends BlockStateProperties {
	public static final EnumProperty<PanelState> panelState = EnumProperty.create("panel_state", PanelState.class);
	public static final BooleanProperty isHigherUp = BooleanProperty.create("is_higher_up");
}
