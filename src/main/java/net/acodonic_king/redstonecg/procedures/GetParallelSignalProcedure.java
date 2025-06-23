package net.acodonic_king.redstonecg.procedures;

import net.acodonic_king.redstonecg.init.RedstonecgModTags;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.acodonic_king.redstonecg.init.RedstonecgModBlocks;

public class GetParallelSignalProcedure {
	public static int[] execute(LevelAccessor world, BlockPos pos, Direction direction) {
		if (direction == null)
			return new int[]{-2, 0};
		BlockState ThisBlock = (world.getBlockState(pos));
		Direction BlockFaces = LittleTools.getDirection(ThisBlock);
		if (direction == BlockFaces) { //Request comes from the back
			return new int[]{-2, 0};
		}
		if (direction == (BlockFaces.getOpposite())) {
			Direction SideA = BlockFaces.getClockWise(Direction.Axis.Y);
			int SideAPower = GetRedstoneSignalProcedure.execute(world, pos, SideA);
			SideA = BlockFaces.getCounterClockWise(Direction.Axis.Y);
			int SideBPower = GetRedstoneSignalProcedure.execute(world, pos, SideA);
			SideAPower = Math.max(SideAPower, SideBPower);
			SideA = BlockFaces.getOpposite();
			SideBPower = GetRedstoneSignalProcedure.execute(world, pos, SideA);
			SideAPower = Math.min(Math.max(SideAPower, 0),15); //Replace 0 with -1 to get Schrodingers Redstone
			SideBPower = Math.min(Math.max(SideBPower, 0),15); //Replace 0 with -1 to get Schrodingers Redstone
			return new int[]{SideAPower, SideBPower}; //Request comes from the front. Providing Line signal and Back signal
		}
		Direction CallSide = direction.getOpposite();
		BlockPos Callpos = pos.offset(CallSide.getStepX(), CallSide.getStepY(), CallSide.getStepZ());
		ThisBlock = world.getBlockState(Callpos);
		if (!ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_GATES)) {
			return new int[]{-2, 0}; //Request comes from left or right, and the requester is not a parallel gate
		}
		CallSide = direction;
		Callpos = pos.offset(CallSide.getStepX(), CallSide.getStepY(), CallSide.getStepZ());
		int SidePower = world instanceof Level _lvl_getRedPow ? _lvl_getRedPow.getSignal(Callpos, CallSide) : 0; //Replace 0 with -1 to get Schrodingers Redstone
		if (SidePower == 0) {
			ThisBlock = (world.getBlockState(Callpos));
			if (ThisBlock.getBlock() == Blocks.REDSTONE_WIRE) {
				SidePower = (ThisBlock.getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _getip22 ? ThisBlock.getValue(_getip22) : 0); //Add -1 to get Schrodingers Redstone
			}
		}
		SidePower = Math.min(Math.max(SidePower, 0),15); //Replace 0 with -1 to get Schrodingers Redstone
		return new int[]{-2, SidePower}; //Request comes from left or right, and the requester is a parrallel gate. Line signal is being provided
	}
	public static int[] executeOpposite(LevelAccessor world, BlockPos pos, Direction direction) {
		if (direction == null)
			return new int[]{-2, 0};
		BlockState ThisBlock = (world.getBlockState(pos));
		Direction BlockFaces = LittleTools.getDirection(ThisBlock);
		if (direction == BlockFaces.getOpposite()) { //Request comes from the back
			return new int[]{-2, 0};
		}
		if (direction == BlockFaces) {
			Direction SideA = BlockFaces.getClockWise(Direction.Axis.Y);
			int SideAPower = GetRedstoneSignalProcedure.execute(world, pos, SideA);
			SideA = BlockFaces.getCounterClockWise(Direction.Axis.Y);
			int SideBPower = GetRedstoneSignalProcedure.execute(world, pos, SideA);
			SideAPower = Math.max(SideAPower, SideBPower);
			SideA = BlockFaces;
			SideBPower = GetRedstoneSignalProcedure.execute(world, pos, SideA);
			SideAPower = Math.min(Math.max(SideAPower, 0),15); //Replace 0 with -1 to get Schrodingers Redstone
			SideBPower = Math.min(Math.max(SideBPower, 0),15); //Replace 0 with -1 to get Schrodingers Redstone
			return new int[]{SideAPower, SideBPower}; //Request comes from the front. Providing Line signal and Back signal
		}
		Direction CallSide = direction.getOpposite();
		BlockPos Callpos = pos.offset(CallSide.getStepX(), CallSide.getStepY(), CallSide.getStepZ());
		ThisBlock = world.getBlockState(Callpos);
		if (!ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_GATES)) {
			return new int[]{-2, 0}; //Request comes from left or right, and the requester is not a parallel gate
		}
		CallSide = direction;
		Callpos = pos.offset(CallSide.getStepX(), CallSide.getStepY(), CallSide.getStepZ());
		int SidePower = world instanceof Level _lvl_getRedPow ? _lvl_getRedPow.getSignal(Callpos, CallSide) : 0; //Replace 0 with -1 to get Schrodingers Redstone
		if (SidePower == 0) {
			ThisBlock = (world.getBlockState(Callpos));
			if (ThisBlock.getBlock() == Blocks.REDSTONE_WIRE) {
				SidePower = (ThisBlock.getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _getip22 ? ThisBlock.getValue(_getip22) : 0); //Add -1 to get Schrodingers Redstone
			}
		}
		SidePower = Math.min(Math.max(SidePower, 0),15); //Replace 0 with -1 to get Schrodingers Redstone
		return new int[]{-2, SidePower}; //Request comes from left or right, and the requester is a parrallel gate. Line signal is being provided
	}
	public static int[] getLineOutput(LevelAccessor world, BlockPos pos, Direction direction) {
		if (direction == null)
			return new int[]{-2, 0};
		int SidePower = 0;
		Direction CallSide = direction.getOpposite();
		BlockPos CallPos = pos.offset(CallSide.getStepX(), CallSide.getStepY(), CallSide.getStepZ());
		BlockState ThisBlock = world.getBlockState(CallPos);
		if (!ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_GATES)) {
			return new int[]{-2, 0};
		}
		SidePower = world instanceof Level _lvl_getRedPow ? _lvl_getRedPow.getSignal(CallPos, CallSide) : 0;
		if (SidePower == 0) {
			ThisBlock = world.getBlockState(CallPos);
			if (ThisBlock.getBlock() == Blocks.REDSTONE_WIRE) {
				SidePower = (ThisBlock.getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _getip22 ? ThisBlock.getValue(_getip22) : 0);
			}
		}
		return new int[]{-2, SidePower};
	}
	public static int getDirectedSignal(LevelAccessor world, BlockPos pos, Direction direction) {
		//As a block at pos, in the direction of direction, ask for a Parallel Path Selector (if present) Directed signal
		if (direction == null)
			return 0;
		BlockPos CallPos = pos.offset(direction.getStepX(), direction.getStepY(), direction.getStepZ());
		BlockState ThisBlock = (world.getBlockState(CallPos));
		if(ThisBlock.getBlock() == RedstonecgModBlocks.FLOOR_PARALLEL_ONE_WAY_PATH_SELECTOR.get()){
			Direction BlockFaces = LittleTools.getDirection(ThisBlock);
			int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
			if ((connection & 1) == 1) {
				BlockFaces = BlockFaces.getCounterClockWise(Direction.Axis.Y);
			} else {
				BlockFaces = BlockFaces.getClockWise(Direction.Axis.Y);
			}
			if (direction != BlockFaces) {
				return 0;
			}
			boolean ReDirect = LittleTools.getBooleanProperty(ThisBlock, "direction");
			if (connection < 2) {
				if (ReDirect) {
					return LittleTools.getIntegerProperty(ThisBlock, "power");
				}
				return getDirectedSignal(world, CallPos, direction);
			}
			if (ReDirect) {
				return getDirectedSignal(world, CallPos, direction);
			}
			return LittleTools.getIntegerProperty(ThisBlock, "power");
		}
		return 0;
	}
}
