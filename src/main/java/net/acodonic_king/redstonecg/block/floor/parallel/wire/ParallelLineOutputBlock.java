
package net.acodonic_king.redstonecg.block.floor.parallel.wire;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ParallelLineOutputBlock extends DefaultGate {
	public static final IntegerProperty POWER = IntegerProperty.create("power",0,15);
	public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,6);

	public ParallelLineOutputBlock() {
		super();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(POWER, CONNECTION);
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		Level world = (Level) blockAccess;
		BlockState ThisBlock = (world.getBlockState(pos));
		Direction BlockFaces = LittleTools.getDirection(ThisBlock).getOpposite();
		if (CanConnectRedstoneGateProcedure.To1_3Gate(ThisBlock, direction) || BlockFaces == direction) {
			int[] SidePower = GetParallelSignalProcedure.getLineOutput(world, pos, BlockFaces);
			if (SidePower[0] == -2) {
				LittleTools.setIntegerProperty(world, pos, SidePower[1], "power");
				return SidePower[1];
			}
		}
		return 0;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return CanConnectRedstoneGateProcedure.To1_3Gate(state, side);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		UpdateStateProcedure.execute(world, pos, fromPos);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		OnBlockRightClickedProcedure.execute(world, pos, blockstate);
		return InteractionResult.SUCCESS;
	}
}
