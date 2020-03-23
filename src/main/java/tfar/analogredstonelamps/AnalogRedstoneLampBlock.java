package tfar.analogredstonelamps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class AnalogRedstoneLampBlock extends Block {
	public AnalogRedstoneLampBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(LIGHT, 0));
	}

	public int getLuminance(BlockState state) {
		return state.get(LIGHT);
	}

	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean moved) {
		super.onBlockAdded(state, world, pos, oldState, moved);
	}

	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(LIGHT, ctx.getWorld().getReceivedRedstonePower(ctx.getBlockPos()));
	}

	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean moved) {
		if (!world.isClient) {
			int light = state.get(LIGHT);
			int redstone = world.getReceivedRedstonePower(pos);
			if (light != redstone) {
				if (light == 0) {
					world.getBlockTickScheduler().schedule(pos, this, 4);
				} else {
					world.setBlockState(pos, state.with(LIGHT,redstone), 2);
				}
			}

		}
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (state.get(LIGHT) != world.getReceivedRedstonePower(pos)) {
			world.setBlockState(pos, state.with(LIGHT,world.getReceivedRedstonePower(pos)), 2);
		}
	}

	public static final IntProperty LIGHT = IntProperty.of("light",0,15);

	public boolean allowsSpawning(BlockState state, BlockView view, BlockPos pos, EntityType<?> type) {
		return true;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIGHT);
	}
}
