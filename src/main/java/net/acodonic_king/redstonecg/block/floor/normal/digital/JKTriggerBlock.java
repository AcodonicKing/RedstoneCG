
package net.acodonic_king.redstonecg.block.floor.normal.digital;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultDigitalInteractable2ABCGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class JKTriggerBlock extends DefaultDigitalInteractable2ABCGate {
	public static final BooleanProperty UNLOCKED = BooleanProperty.create("unlocked");
	public JKTriggerBlock() {
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
		Direction[] Sides = GetGateInputSidesProcedure.Get3ABCGate(ThisBlock);
		int SideCPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[2]);
		boolean output = LittleTools.getBooleanProperty(ThisBlock, "output");
		if(SideCPower > 0){
			if(LittleTools.getBooleanProperty(ThisBlock, "unlocked")){
				boolean SideAPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[0]) > 0;
				boolean SideBPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[1]) > 0;
				if (SideAPower && SideBPower) {
					output = !output;
				} else if (SideAPower) {
					output = true;
				} else if (SideBPower) {
					output = false;
				}
				LittleTools.setBooleanProperty(world, pos, output, "output");
				LittleTools.setBooleanProperty(world, pos, false, "unlocked");
			}
		} else {LittleTools.setBooleanProperty(world, pos, true, "unlocked");}
		if (output) {return 15;}
		return 0;
	}
}
