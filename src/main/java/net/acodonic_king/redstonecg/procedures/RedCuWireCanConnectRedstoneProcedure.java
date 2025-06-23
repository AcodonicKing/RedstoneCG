package net.acodonic_king.redstonecg.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;

import net.acodonic_king.redstonecg.init.RedstonecgModBlocks;

public class RedCuWireCanConnectRedstoneProcedure {
	public static boolean toWire(BlockState blockstate, Direction direction) {
		Direction block_facing = LittleTools.getDirection(blockstate);
		return switch (LittleTools.getIntegerProperty(blockstate, "connection")){
			case(0) -> (direction == block_facing.getOpposite()) || (direction == block_facing);
			case(1) -> (direction == block_facing.getOpposite()) || (direction == block_facing.getCounterClockWise(Direction.Axis.Y));
			case(2) -> !(direction == block_facing);
			case(3) -> true;
			default -> false;
		};
    }
	public static boolean toConverterWire(BlockState blockstate, Direction direction) {
		Direction block_facing = LittleTools.getDirection(blockstate);
		if (direction == block_facing) {return false;}
		int connection = LittleTools.getIntegerProperty(blockstate, "connection");
		if (direction == block_facing.getOpposite()) {
			return (connection <= 8 || connection == 11);
		} // has wire on the front ^
		else if (direction == (block_facing.getClockWise(Direction.Axis.Y))) {
			return (connection >= 9 || connection == 5 || connection == 6);
		} // has wire on the left <
		else if (direction == (block_facing.getCounterClockWise(Direction.Axis.Y))) {
			return (connection >= 7);
		} // has wire on the right >
		return true;
	}
	public static boolean toConverterRedstone(BlockState blockstate, Direction direction) {
		Direction block_facing = LittleTools.getDirection(blockstate);
		int connection = LittleTools.getIntegerProperty(blockstate, "connection");
		if (direction == (block_facing)) {
			return connection == 0 || connection >= 4; // has redstone on the back v
		} else if (direction == (block_facing.getCounterClockWise(Direction.Axis.Y))) {
			return connection == 2 || connection == 3 || connection == 4 || connection == 6; // has redstone on the right >
		} else if (direction == (block_facing.getClockWise(Direction.Axis.Y))) {
			return connection == 1 || connection == 3 || connection == 4 || connection == 8; // has redstone on the left <
		} else if (direction == block_facing.getOpposite()) {
			return connection == 10; // has redstone on the front ^
		}
		return false;
	}

	public static boolean checkForBlock(BlockState blockstate, Direction direction) {
		if (RedstonecgModBlocks.FLOOR_RED_CU_WIRE.get() == blockstate.getBlock()) { return toWire(blockstate, direction);
		} else if (RedstonecgModBlocks.FLOOR_REDSTONE_TO_RED_CU_CONVERTER.get() == blockstate.getBlock()) { return toConverterWire(blockstate, direction);}
		return false;
	}
}
