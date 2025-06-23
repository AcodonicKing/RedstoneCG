
package net.acodonic_king.redstonecg.block.floor.normal.digital;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultDigitalInteractable1Gate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class TTriggerBlock extends DefaultDigitalInteractable1Gate {
	public static final BooleanProperty UNLOCKED = BooleanProperty.create("unlocked");
	public TTriggerBlock() {
		super();
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(UNLOCKED);
	}
	@Override
	public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
		BlockState ThisBlock = (world.getBlockState(pos));
		Direction BlockFacing = LittleTools.getDirection(ThisBlock).getOpposite();
		if (!(direction == BlockFacing)) {return 0;}
		Direction Side = GetGateInputSidesProcedure.Get1Gate(ThisBlock);
		int SidePower = GetRedstoneSignalProcedure.execute(world, pos, Side);
		boolean output = LittleTools.getBooleanProperty(ThisBlock, "output");
		if(SidePower > 0){
			if(LittleTools.getBooleanProperty(ThisBlock, "unlocked")){
				output = !output;
				LittleTools.setBooleanProperty(world, pos, output, "output");
				LittleTools.setBooleanProperty(world, pos, false, "unlocked");
			}
		} else {LittleTools.setBooleanProperty(world, pos, true, "unlocked");}
		if (output) {return 15;}
		return 0;
	}
}