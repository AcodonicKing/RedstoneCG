
package net.acodonic_king.redstonecg.block.floor.normal.digital;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultDigitalInteractable2ABGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;


public class DLatchBlock extends DefaultDigitalInteractable2ABGate {
	public DLatchBlock() {
		super();
	}
	@Override
	public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
		BlockState ThisBlock = (world.getBlockState(pos));
		Direction BlockFacing = LittleTools.getDirection(ThisBlock).getOpposite();
		if (!(direction == BlockFacing)) {return 0;}
		Direction[] Sides = GetGateInputSidesProcedure.Get2ABGate(ThisBlock);
		if (GetRedstoneSignalProcedure.execute(world, pos, Sides[1]) > 0) {
			if (GetRedstoneSignalProcedure.execute(world, pos, Sides[0]) > 0) {
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
