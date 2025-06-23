
package net.acodonic_king.redstonecg.block.floor.parallel.hybrid;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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

public class ParallelPathSelectorBlock extends DefaultGate {
	public static final IntegerProperty POWER = IntegerProperty.create("power",0,15);
	public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,3);
	public static final BooleanProperty DIRECTION = BooleanProperty.create("direction");
	public ParallelPathSelectorBlock() {
		super();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CONNECTION, POWER, DIRECTION);
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		Level world = (Level) blockAccess;
		BlockState ThisBlock = world.getBlockState(pos);
		Direction BlockFaces = LittleTools.getDirection(ThisBlock);
		int[] SidePower = GetParallelSignalProcedure.executeOpposite(world, pos, direction);
		if (SidePower[0] == -2) {
			return SidePower[1];
		}
		boolean ReDirect = SidePower[0] > 0;
		LittleTools.setBooleanProperty(world, pos, ReDirect, "direction");
		LittleTools.setIntegerProperty(world, pos, SidePower[1], "power");
		int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
		if((connection & 1) == 0){
			BlockFaces = BlockFaces.getClockWise(Direction.Axis.Y);
		} else {
			BlockFaces = BlockFaces.getCounterClockWise(Direction.Axis.Y);
		}
		if (ReDirect) {
			if (connection < 2) {
				return GetParallelSignalProcedure.getDirectedSignal(world, pos, BlockFaces);
			}
			return SidePower[1];
		}
		if (connection < 2) {
			return SidePower[1];
		}
		return GetParallelSignalProcedure.getDirectedSignal(world, pos, BlockFaces);
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
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
