
package net.acodonic_king.redstonecg.block.floor.normal.hybrid;

import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class OneWayOneWayThroughNotGateBlock extends OneWayThroughGateBlock {
	public OneWayOneWayThroughNotGateBlock() {
		super();
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		Direction BlockFacing = LittleTools.getDirection(blockstate);
		Direction[] Sides = GetGateInputSidesProcedure.Get2ABGate(blockstate);
		int SideCPower = GetRedstoneSignalProcedure.execute(world, pos, BlockFacing);
		int SideDPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[0]);
		LittleTools.setBooleanProperty(world, pos, SideCPower == 0, "enabled");
		LittleTools.setIntegerProperty(world, pos, SideDPower, "power");
	}
}
