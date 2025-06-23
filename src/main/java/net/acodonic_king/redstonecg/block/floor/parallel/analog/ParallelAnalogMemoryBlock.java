
package net.acodonic_king.redstonecg.block.floor.parallel.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogInteractableABGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ParallelAnalogMemoryBlock extends DefaultParallelAnalogInteractableABGate {
	public ParallelAnalogMemoryBlock() {
		super();
	}

	@Override
	public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
		BlockState ThisBlock = world.getBlockState(pos);
		int[] SidePower = GetParallelSignalProcedure.execute(world, pos, direction);
		if (SidePower[0] == -2) {
			return SidePower[1];
		}
		int ASignal = SidePower[0];
		int BSignal = SidePower[1];
		if(LittleTools.getIntegerProperty(ThisBlock, "connection") == 0){
			ASignal = SidePower[1];
			BSignal = SidePower[0];
		}
		if(BSignal > 0){
			LittleTools.setIntegerProperty(world, pos, ASignal, "power");
			return ASignal;
		}
		return LittleTools.getIntegerProperty(ThisBlock,"power");
	}
}
