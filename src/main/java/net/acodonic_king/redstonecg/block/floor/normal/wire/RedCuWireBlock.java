
package net.acodonic_king.redstonecg.block.floor.normal.wire;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultWire;
import net.acodonic_king.redstonecg.block.floor.entity.MinimalBlockEntity;
import net.acodonic_king.redstonecg.init.RedstonecgModBlockEntities;
import net.acodonic_king.redstonecg.network.RedstonecgModVariables;
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

public class RedCuWireBlock extends DefaultWire {
	public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,3);

	public RedCuWireBlock() {
		super();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MinimalBlockEntity(RedstonecgModBlockEntities.RED_CU_WIRE.get(),pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CONNECTION);
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		Level world = (Level) blockAccess;
		if (RedCuWireCanConnectRedstoneProcedure.toWire(blockstate, direction)) {
			return ((int) LittleTools.getBlockEntityNBTValue(world, pos, "power")) / 16;
		}
		return 0;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return RedCuWireCanConnectRedstoneProcedure.toWire(state, side);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		LittleTools.setBlockEntityNBTValue(world, pos, 0, "power");
	}

	@Override
	public void onTick(LevelAccessor world, BlockPos pos){
		BlockState ThisBlock = world.getBlockState(pos);
		Direction BlockFacing = LittleTools.getDirection(ThisBlock);
		int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
		BlockPos CallPos = pos.offset(BlockFacing.getStepX(), BlockFacing.getStepY(), BlockFacing.getStepZ());
		int SelfPower = GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing);
		int SidePower = 0;
		if (connection == 0) {
			BlockFacing = BlockFacing.getOpposite();
			CallPos = pos.offset(BlockFacing.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));
		} else if (connection == 1) {
			BlockFacing = BlockFacing.getClockWise(Direction.Axis.Y);
			CallPos = pos.offset(BlockFacing.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));
		} else if (connection == 2) {
			BlockFacing = BlockFacing.getClockWise(Direction.Axis.Y);
			CallPos = pos.offset(BlockFacing.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));
			BlockFacing = BlockFacing.getOpposite();
			CallPos = pos.offset(BlockFacing.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));
		} else if (connection == 3) {
			for(int i = 0; i < 3; i++){
				BlockFacing = BlockFacing.getClockWise(Direction.Axis.Y);
				CallPos = pos.offset(BlockFacing.getNormal());
				SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFacing));
			}
		}
		SelfPower = Math.max(SelfPower - 1, 0);
		if(((int) LittleTools.getBlockEntityNBTValue(world, pos, "power")) != SelfPower){
			LittleTools.setBooleanProperty(world, pos, !LittleTools.getBooleanProperty(ThisBlock, "powerchange") , "powerchange");
		}
		LittleTools.setBlockEntityNBTValue(world, pos, SelfPower, "power");
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		{
			BlockState ThisBlock = world.getBlockState(pos);
			Direction BlockFacing = LittleTools.getDirection(ThisBlock);
			int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
			BlockPos CallPos = pos;
			boolean BlockBConnects = false;
			boolean BlockCConnects = false;
			boolean BlockDConnects = false;
			if (RedstonecgModVariables.MapVariables.get(world).enableredcuwireautoconnect) {
				if (connection == 0 || connection == 1) {
					if (connection == 0) {
						BlockFacing = BlockFacing.getClockWise(Direction.Axis.Y);
						CallPos = pos.offset(BlockFacing.getNormal());
						BlockBConnects = RedCuWireCanConnectRedstoneProcedure.checkForBlock(world.getBlockState(CallPos), BlockFacing);
						BlockFacing = BlockFacing.getOpposite();
					} else {
						BlockFacing = BlockFacing.getOpposite();
						CallPos = pos.offset(BlockFacing.getNormal());
						BlockBConnects = RedCuWireCanConnectRedstoneProcedure.checkForBlock(world.getBlockState(CallPos), BlockFacing);
						BlockFacing = BlockFacing.getClockWise(Direction.Axis.Y);
					}
					CallPos = pos.offset(BlockFacing.getNormal());
					BlockDConnects = RedCuWireCanConnectRedstoneProcedure.checkForBlock(world.getBlockState(CallPos), BlockFacing);
					if (BlockBConnects && BlockDConnects) {
						LittleTools.setIntegerProperty(world, pos, 3, "connection");
					} else if (BlockBConnects ^ BlockDConnects) {
						if (BlockBConnects) {
							BlockFacing = BlockFacing.getOpposite();
						}
						if (connection == 0) {
							LittleTools.setDirection(world, pos, BlockFacing);
						} else if (BlockBConnects) {
							LittleTools.setDirection(world, pos, BlockFacing);
						}
						LittleTools.setIntegerProperty(world, pos, 2, "connection");
					}
				} else if (connection == 2) {
					BlockFacing = BlockFacing.getOpposite();
					CallPos = pos.offset(BlockFacing.getNormal());
					BlockCConnects = RedCuWireCanConnectRedstoneProcedure.checkForBlock(world.getBlockState(CallPos), BlockFacing);
					if (BlockCConnects) {
						LittleTools.setIntegerProperty(world, pos, 3, "connection");
					}
				}
			}
		}
		this.onTick(world, pos);
	}
}
