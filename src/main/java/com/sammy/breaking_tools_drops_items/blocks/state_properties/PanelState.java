package com.sammy.breaking_tools_drops_items.blocks.state_properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum PanelState implements StringRepresentable {
	fixed("fixed"),
	broken("broken"),
	permaBroken("perma_broken");
	
	public final String name;
	
	PanelState(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	@Override
	public @NotNull String getSerializedName() {
		return name;
	}
}
