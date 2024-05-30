package com.sammy.breaking_tools_drops_items.blocks;

import com.sammy.breaking_tools_drops_items.blocks.state_properties.BtdsBlockStateProperties;
import com.sammy.breaking_tools_drops_items.blocks.state_properties.PanelState;
import com.sammy.breaking_tools_drops_items.tags.BtdsItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PanelBlock extends HorizontalDirectionalBlock {
	public static final VoxelShape east = box(0, 0, 1, 4, 16, 15);
	public static final VoxelShape west = box(12, 0, 1, 16, 16, 15);
	public static final VoxelShape south = box(1, 0, 0, 15, 16, 4);
	public static final VoxelShape north = box(1, 0, 12, 15, 16, 16);
	public PanelBlock(Properties properties) {
		super(properties);
		registerDefaultState(
				getStateDefinition().any()
						.setValue(BtdsBlockStateProperties.panelState, PanelState.permaBroken)
						.setValue(BtdsBlockStateProperties.isHigherUp, false)
		);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos blockPos, @NotNull CollisionContext ctx) {
		return switch (state.getValue(BtdsBlockStateProperties.HORIZONTAL_FACING)) {
			case EAST -> east;
			case WEST -> west;
			case SOUTH -> south;
			case NORTH -> north;
			default -> throw new IllegalStateException("Not a possible direction! How?!");
		};
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState state = defaultBlockState();
		Direction direction = ctx.getClickedFace();
		if (!ctx.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
			state = state.setValue(BtdsBlockStateProperties.HORIZONTAL_FACING, direction);
		} else {
			state = state.setValue(BtdsBlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
		}
		
		return state;
	}
	
	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BtdsBlockStateProperties.HORIZONTAL_FACING, BtdsBlockStateProperties.panelState, BtdsBlockStateProperties.isHigherUp);
	}
	
	public void fill(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos1, @NotNull BlockPos pos2, int flag) {
		var minX = Math.min(pos1.getX(), pos2.getX());
		var maxX = Math.max(pos1.getX(), pos2.getX());
		var minY = Math.min(pos1.getY(), pos2.getY());
		var maxY = Math.max(pos1.getY(), pos2.getY());
		var minZ = Math.min(pos1.getZ(), pos2.getZ());
		var maxZ = Math.max(pos1.getZ(), pos2.getZ());
		
		for (int x = minX; x <= maxX; ++x)
			for (int y = minY; y <= maxY; ++y)
				for (int z = minZ; z <= maxZ; ++z)
					level.setBlock(new BlockPos(x, y, z), state, flag);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (state.getValue(BtdsBlockStateProperties.panelState) == PanelState.fixed) {
			// Proceed if requires key & has key OR
			// is normal
			if (state.getValue(BtdsBlockStateProperties.isHigherUp) && player.getMainHandItem().is(BtdsItemTags.higherTierCard) || !state.getValue(BtdsBlockStateProperties.isHigherUp)) {
				if (state.getValue(BtdsBlockStateProperties.isHigherUp))
					player.getMainHandItem().shrink(1);
				
				if (state.getValue(BtdsBlockStateProperties.HORIZONTAL_FACING) == Direction.NORTH)
					fill(Blocks.AIR.defaultBlockState(), level, pos.offset(-1, -1, 1), pos.offset(-3, 1, 1), 1 | 2);
				else if (state.getValue(BtdsBlockStateProperties.HORIZONTAL_FACING) == Direction.WEST)
					fill(Blocks.AIR.defaultBlockState(), level, pos.offset(1, -1, 1), pos.offset(1, 1, 3), 1 | 2);
				else if (state.getValue(BtdsBlockStateProperties.HORIZONTAL_FACING) == Direction.SOUTH)
					fill(Blocks.AIR.defaultBlockState(), level, pos.offset(1, -1, -1), pos.offset(3, 1, -1), 1 | 2);
				else if (state.getValue(BtdsBlockStateProperties.HORIZONTAL_FACING) == Direction.EAST)
					fill(Blocks.AIR.defaultBlockState(), level, pos.offset(-1, -1, -1), pos.offset(-1, 1, -3), 1 | 2);
				
				return InteractionResult.SUCCESS;
			} else
				return super.use(state, level, pos, player, hand, hit);
		} else
			return super.use(state, level, pos, player, hand, hit);
	}
}
