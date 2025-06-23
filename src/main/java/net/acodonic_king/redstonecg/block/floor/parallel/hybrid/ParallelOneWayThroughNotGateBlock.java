
package net.acodonic_king.redstonecg.block.floor.parallel.hybrid;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultParallelAnalogInteractableABGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ParallelOneWayThroughNotGateBlock extends DefaultParallelAnalogInteractableABGate {
	public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");
	public ParallelOneWayThroughNotGateBlock() {
		super();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(ENABLED);
	}

	@Override
	public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
		BlockState ThisBlock = world.getBlockState(pos);
		int[] SidePower = GetParallelSignalProcedure.executeOpposite(world, pos, direction);
		if (SidePower[0] == -2) {
			return SidePower[1];
		}
		int ASignal = SidePower[0];
		int BSignal = SidePower[1];
		if(LittleTools.getIntegerProperty(ThisBlock, "connection") == 0){
			ASignal = SidePower[1];
			BSignal = SidePower[0];
		}
		LittleTools.setIntegerProperty(world, pos, ASignal, "power");
		LittleTools.setBooleanProperty(world, pos, BSignal == 0, "enabled");
		if(BSignal == 0){
			return ASignal;
		}
		return 0;
	}
}
