
package net.acodonic_king.redstonecg.block.floor.parallel.digital;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelDigitalInteractableABGate;
import net.acodonic_king.redstonecg.procedures.GetParallelSignalProcedure;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class ParallelDLatchBlock extends DefaultParallelDigitalInteractableABGate {
	public ParallelDLatchBlock() {
		super();
	}
	@Override
	public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
		BlockState ThisBlock = world.getBlockState(pos);
		int[] SidePower = GetParallelSignalProcedure.execute(world, pos, direction);
		if (SidePower[0] == -2) {
			return SidePower[1];
		}
		int Primary = SidePower[0];
		int Secondary = SidePower[1];
		if(LittleTools.getIntegerProperty(ThisBlock, "connection") == 0){
			Primary = SidePower[1];
			Secondary = SidePower[0];
		}
		if (Secondary > 0) {
			if (Primary > 0) {
				LittleTools.setBooleanProperty(world, pos, true, "output");
				return 15;
			}
			LittleTools.setBooleanProperty(world, pos, false, "output");
			return 0;
		}
		if (LittleTools.getBooleanProperty(ThisBlock, "output")) {return 15;}
		return 0;
	}
}
