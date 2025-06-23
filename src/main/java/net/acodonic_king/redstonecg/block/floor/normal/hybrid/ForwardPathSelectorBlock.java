
package net.acodonic_king.redstonecg.block.floor.normal.hybrid;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultGate;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ForwardPathSelectorBlock extends DefaultGate {
	public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,5);
	public static final IntegerProperty POWER = IntegerProperty.create("power",0,15);
	public static final BooleanProperty DIRECTION = BooleanProperty.create("direction");

	public ForwardPathSelectorBlock() {
		super();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CONNECTION, POWER, DIRECTION);
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		Direction[] Sides = GetGateInputSidesProcedure.Get3ABCGate(blockstate);
		int SideAPower = 0;
		int SideBPower = 0;
		if(LittleTools.getBooleanProperty(blockstate, "direction")){
			SideBPower = LittleTools.getIntegerProperty(blockstate, "power");
		} else {
			SideAPower = LittleTools.getIntegerProperty(blockstate, "power");
		}
		if (direction == Sides[0].getOpposite()) {
			return SideAPower;
		}
		if (direction == Sides[1].getOpposite()) {
			return SideBPower;
		}
		return 0;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		Direction BlockFacing = LittleTools.getDirection(blockstate);
		Direction[] Sides = GetGateInputSidesProcedure.Get3ABCGate(blockstate);
		int SideCPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[2]);
		int SideDPower = GetRedstoneSignalProcedure.execute(world, pos, BlockFacing);
		LittleTools.setBooleanProperty(world, pos, SideCPower > 0, "direction");
		LittleTools.setIntegerProperty(world, pos, SideDPower, "power");
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		OnBlockRightClickedProcedure.execute(world, pos, blockstate);
		return InteractionResult.SUCCESS;
	}
}
