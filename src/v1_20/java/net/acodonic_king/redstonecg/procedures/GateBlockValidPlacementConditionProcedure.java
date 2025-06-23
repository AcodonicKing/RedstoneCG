package net.acodonic_king.redstonecg.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class GateBlockValidPlacementConditionProcedure {
	public static boolean floor(LevelAccessor world, BlockPos pos) {
		boolean BlockBelowIsSolid = false;
		if (world instanceof Level _level)
			_level.updateNeighborsAt(pos, _level.getBlockState(pos).getBlock());
		BlockBelowIsSolid = world.getBlockState(pos.offset(0,-1,0)).isSolid();
		return BlockBelowIsSolid;
	}
}
