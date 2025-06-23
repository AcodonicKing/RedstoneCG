package net.acodonic_king.redstonecg.procedures;

import net.acodonic_king.redstonecg.init.RedstonecgModTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.acodonic_king.redstonecg.init.RedstonecgModBlocks;

public class GetWireRedstoneSignalProcedure {
	public static int getIntIntersectionPower(LevelAccessor world, BlockPos BlockPosN, Direction direction) {
		if (direction == null)
			return 0;
		BlockState ThisBlock = world.getBlockState(BlockPosN);
		Direction BlockFacing = LittleTools.getDirection(ThisBlock).getOpposite();
		int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
		if (connection == 0) {
			if (BlockFacing == direction || (BlockFacing.getOpposite()) == direction) {
				return (int) LittleTools.getBlockEntityNBTValue(world, BlockPosN, "powerA");
			} else if ((BlockFacing.getCounterClockWise(Direction.Axis.Y)) == direction || (BlockFacing.getClockWise(Direction.Axis.Y)) == direction) {
				return (int) LittleTools.getBlockEntityNBTValue(world, BlockPosN, "powerB");
			}
		} else if (connection == 1) {
			if (BlockFacing == direction || (BlockFacing.getCounterClockWise(Direction.Axis.Y)) == direction) {
				return (int) LittleTools.getBlockEntityNBTValue(world, BlockPosN, "powerA");
			} else if ((BlockFacing.getOpposite()) == direction || (BlockFacing.getClockWise(Direction.Axis.Y)) == direction) {
				return (int) LittleTools.getBlockEntityNBTValue(world, BlockPosN, "powerB");
			}
		}
		return 0;
	}
	public static int getIntPower(LevelAccessor world, BlockPos BlockPosN, Direction BlockFacing){
		BlockState _bs = world.getBlockState(BlockPosN);
		if (_bs.is(RedstonecgModTags.Blocks.REDCUWIRES)) {
			return (int) LittleTools.getBlockEntityNBTValue(world, BlockPosN, "power");
		} else if (RedstonecgModBlocks.FLOOR_RED_CU_INTERSECTION.get() == _bs.getBlock()) {
			return getIntIntersectionPower(world, BlockPosN, BlockFacing);
		} else {
			int SidePower = world instanceof Level _lvl_getRedPow ? _lvl_getRedPow.getSignal(BlockPosN, BlockFacing) : 0;
			if (SidePower == 0) {
				if (_bs.getBlock() == Blocks.REDSTONE_WIRE) {
					SidePower = LittleTools.getIntegerProperty(_bs, "power");
				}
			}
			return SidePower * 16;
		}
	}
}
