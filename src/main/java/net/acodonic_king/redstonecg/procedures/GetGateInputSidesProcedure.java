package net.acodonic_king.redstonecg.procedures;

import net.minecraft.core.NonNullList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;

public class GetGateInputSidesProcedure {
	public static Direction Get1Gate(BlockState blockstate){
		Direction direction = LittleTools.getDirection(blockstate);
		return switch (LittleTools.getIntegerProperty(blockstate, "connection")) {
            case (0) -> direction.getOpposite();
            case (1) -> direction.getCounterClockWise(Direction.Axis.Y);
            case (2) -> direction.getClockWise(Direction.Axis.Y);
            default -> Direction.UP;
        };
    }
	public static Direction[] Get2Gate(BlockState blockstate){
		Direction direction = LittleTools.getDirection(blockstate);
		return switch (LittleTools.getIntegerProperty(blockstate, "connection")) {
			case (0) -> new Direction[]{direction.getCounterClockWise(Direction.Axis.Y), direction.getClockWise(Direction.Axis.Y)};
			case (1) -> new Direction[]{direction.getOpposite(), direction.getCounterClockWise(Direction.Axis.Y)};
			case (2) -> new Direction[]{direction.getClockWise(Direction.Axis.Y), direction.getOpposite()};
			default -> new Direction[]{Direction.UP, Direction.UP};
		};
	}
	public static Direction[] Get2ABGate(BlockState blockstate){
		Direction direction = LittleTools.getDirection(blockstate);
		Direction SideA = Direction.UP;
		Direction SideB = Direction.UP;
		int connection = LittleTools.getIntegerProperty(blockstate, "connection");
		if (connection < 2) {
			SideA = direction.getOpposite();
		} else if (connection < 4) {
			SideA = direction.getCounterClockWise(Direction.Axis.Y);
		} else {
			SideA = direction.getClockWise(Direction.Axis.Y);
		}
		if (connection == 5 || connection == 0){
			SideB = direction.getCounterClockWise(Direction.Axis.Y);
		} else if (connection < 3){
			SideB = direction.getClockWise(Direction.Axis.Y);
		} else {
			SideB = direction.getOpposite();
		}
		return new Direction[]{SideA, SideB};
	}
	public static Direction[] Get3ABCGate(BlockState blockstate){
		Direction direction = LittleTools.getDirection(blockstate);
		Direction SideA = Direction.UP;
		Direction SideB = Direction.UP;
		int connection = LittleTools.getIntegerProperty(blockstate, "connection");
		if (connection < 2) {
			SideA = direction.getOpposite();
		} else if (connection < 4) {
			SideA = direction.getCounterClockWise(Direction.Axis.Y);
		} else {
			SideA = direction.getClockWise(Direction.Axis.Y);
		}
		if (connection == 5 || connection == 0){
			SideB = direction.getCounterClockWise(Direction.Axis.Y);
		} else if (connection < 3){
			SideB = direction.getClockWise(Direction.Axis.Y);
		} else {
			SideB = direction.getOpposite();
		}
		connection = connection > 2 ? connection - 3 : connection;
		Direction SideC = switch(connection){
			case(0) -> direction.getClockWise(Direction.Axis.Y);
			case(1) -> direction.getCounterClockWise(Direction.Axis.Y);
			case(2) -> direction.getOpposite();
			default -> Direction.UP;
		};
		return new Direction[]{SideA, SideB, SideC};
	}
	public static NonNullList<Direction> Get1_4Gate(BlockState blockstate){
		Direction direction = LittleTools.getDirection(blockstate);
		int connection = LittleTools.getIntegerProperty(blockstate, "connection");
		NonNullList<Direction> out = NonNullList.create();
		int connection3bit = connection & 3;
		if (connection3bit == 0 || connection3bit == 2) {
			out.add(direction);
		}
		if (connection3bit == 1 || connection3bit == 2) {
			out.add(direction.getClockWise(Direction.Axis.Y));
		}
		if (connection > 2 && connection < 7 || (connection > 10)) {
			out.add(direction.getOpposite());
		}
		if (connection > 6) {
			out.add(direction.getCounterClockWise(Direction.Axis.Y));
		}
		return out;
	}
	public static NonNullList<Direction> Get1_3Gate(BlockState blockstate){
		Direction direction = LittleTools.getDirection(blockstate);
		NonNullList<Direction> out = NonNullList.create();
		int connection = LittleTools.getIntegerProperty(blockstate, "connection");
		int connection3bit = connection & 3;
		if((connection3bit == 0) || (connection3bit == 2)){
			out.add(direction.getCounterClockWise(Direction.Axis.Y));
		}
		if(!(connection == 0 || connection == 3 || connection == 4)){
			out.add(direction.getOpposite());
		}
		if(connection > 2){
			out.add(direction.getClockWise(Direction.Axis.Y));
		}
		return out;
	}
}
