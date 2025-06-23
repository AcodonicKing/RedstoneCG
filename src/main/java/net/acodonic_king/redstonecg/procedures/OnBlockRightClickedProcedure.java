package net.acodonic_king.redstonecg.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class OnBlockRightClickedProcedure {
	public static int execute(LevelAccessor world, BlockPos pos) {
		return execute(world, pos, world.getBlockState(pos));
	}
	public static int execute(LevelAccessor world, BlockPos pos, BlockState blockstate) {
		if (blockstate.getBlock().getStateDefinition().getProperty("connection") instanceof IntegerProperty _integerProp) {
			int connection = blockstate.getValue(_integerProp) + 1;
			if (_integerProp.getPossibleValues().contains(connection))
			{
				world.setBlock(pos, blockstate.setValue(_integerProp, connection), 3);
				return connection;
			} else {
				world.setBlock(pos, blockstate.setValue(_integerProp, 0), 3);
			}
		}
		return 0;
	}
}
