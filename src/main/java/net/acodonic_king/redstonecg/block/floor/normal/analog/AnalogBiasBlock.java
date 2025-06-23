package net.acodonic_king.redstonecg.block.floor.normal.analog;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultAnalogInteractible2ABGate;
import net.acodonic_king.redstonecg.block.floor.defaults.DefaultAnalogInteractible2Gate;
import net.acodonic_king.redstonecg.procedures.GetGateInputSidesProcedure;
import net.acodonic_king.redstonecg.procedures.GetRedstoneSignalProcedure;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class AnalogBiasBlock extends DefaultAnalogInteractible2Gate {
	public AnalogBiasBlock() {
		super();
	}
	@Override
	public int redstonePowerOperation(int SideAPower, int SideBPower){
		return Math.min(15, SideAPower + SideBPower);
	}

	public static class AnalogMemoryBlock extends DefaultAnalogInteractible2ABGate {
		public AnalogMemoryBlock() {
			super();
		}
		@Override
		public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
			BlockState ThisBlock = (world.getBlockState(pos));
			Direction BlockFacing = LittleTools.getDirection(ThisBlock).getOpposite();
			if (!(direction == BlockFacing)) {return 0;}
			Direction[] Sides = GetGateInputSidesProcedure.Get2ABGate(ThisBlock);
			int SideAPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[0]);
			int SideBPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[1]);
			if (SideBPower > 0) {
				LittleTools.setIntegerProperty(world, pos, SideAPower, "power");
				return SideAPower;
			}
			return LittleTools.getIntegerProperty(ThisBlock,"power");
		}
	}
}
