
package net.acodonic_king.redstonecg.block.floor.normal.wire;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultWire;
import net.acodonic_king.redstonecg.block.floor.entity.MinimalBlockEntity;
import net.acodonic_king.redstonecg.init.RedstonecgModBlockEntities;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class RedCuIntersectionBlock extends DefaultWire {
	public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,1);

	public RedCuIntersectionBlock() {
		super();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MinimalBlockEntity(RedstonecgModBlockEntities.RED_CU_INTERSECTION.get(), pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CONNECTION);
	}

	@Override
	public void onTick(LevelAccessor world, BlockPos pos){
		BlockState ThisBlock = world.getBlockState(pos);
		Direction BlockFacing = LittleTools.getDirection(ThisBlock);
		BlockPos CallPos = pos.offset(BlockFacing.getNormal());
		int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
		int SelfPowerA = GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing);
		int SelfPowerB = 0;
		Direction[] Bdirections;
		if (connection == 0) {
			BlockFacing = BlockFacing.getOpposite();
			CallPos = pos.offset(BlockFacing.getNormal());
			SelfPowerA = Math.max(SelfPowerA, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));

			Bdirections = new Direction[]{BlockFacing.getClockWise(Direction.Axis.Y), BlockFacing.getCounterClockWise(Direction.Axis.Y)};
        } else {
			BlockFacing = BlockFacing.getCounterClockWise(Direction.Axis.Y);
			CallPos = pos.offset(BlockFacing.getNormal());
			SelfPowerA = Math.max(SelfPowerA, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));

			Bdirections = new Direction[]{BlockFacing.getOpposite(), BlockFacing.getCounterClockWise(Direction.Axis.Y)};
        }
		for(Direction dir: Bdirections){
			CallPos = pos.offset(dir.getNormal());
			SelfPowerB = Math.max(SelfPowerB, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, dir));
		}

        SelfPowerA = Math.max(SelfPowerA - 1, 0);
		SelfPowerB = Math.max(SelfPowerB - 1, 0);
		if((((int) LittleTools.getBlockEntityNBTValue(world, pos, "powerA")) != SelfPowerA) || (((int) LittleTools.getBlockEntityNBTValue(world, pos, "powerB")) != SelfPowerB)){
			LittleTools.setBooleanProperty(world, pos, !LittleTools.getBooleanProperty(ThisBlock, "powerchange") , "powerchange");
			LittleTools.setBlockEntityNBTValue(world, pos, SelfPowerA, "powerA");
			LittleTools.setBlockEntityNBTValue(world, pos, SelfPowerB, "powerB");
		}
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		Level world = (Level) blockAccess;
		return GetWireRedstoneSignalProcedure.getIntIntersectionPower(world, pos, direction) / 16;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		LittleTools.setBlockEntityNBTValue(world, pos, 0, "powerA");
		LittleTools.setBlockEntityNBTValue(world, pos, 0, "powerB");
	}
}
