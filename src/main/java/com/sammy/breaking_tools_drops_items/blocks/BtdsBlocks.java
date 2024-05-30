package com.sammy.breaking_tools_drops_items.blocks;

import com.sammy.breaking_tools_drops_items.BreakingToolsDropsItems;
import com.sammy.breaking_tools_drops_items.blocks.state_properties.BtdsBlockStateProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;

public class BtdsBlocks {
	public static void register() {}
	
	@SuppressWarnings("unused")
	public static final BlockEntry<PanelBlock> panel =
			BreakingToolsDropsItems.registrate
					.block("panel", PanelBlock::new)
					.item()
					.model(
							(ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("block/broken_panel"))
					)
					.build()
					.blockstate(
							(ctx, provider) ->
									provider.getVariantBuilder(ctx.getEntry()).forAllStates(
											state -> {
												var modelPath = "block/" + state.getValue(BtdsBlockStateProperties.panelState) + "_panel";
												if (state.getValue(BtdsBlockStateProperties.isHigherUp))
													modelPath += "_higher_up";
												
												return ConfiguredModel.builder()
														.modelFile(provider.models().getExistingFile(provider.modLoc(modelPath)))
														.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
														.build();
											}
									)
					)
					.register();
}
