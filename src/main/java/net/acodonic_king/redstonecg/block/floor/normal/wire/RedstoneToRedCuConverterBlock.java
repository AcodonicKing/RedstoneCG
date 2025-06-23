
package net.acodonic_king.redstonecg.block.floor.normal.wire;

import net.acodonic_king.redstonecg.block.floor.defaults.DefaultWire;
import net.acodonic_king.redstonecg.block.floor.entity.MinimalBlockEntity;
import net.acodonic_king.redstonecg.init.RedstonecgModBlockEntities;
import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class RedstoneToRedCuConverterBlock extends DefaultWire {
	public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,11);

	public RedstoneToRedCuConverterBlock() {
		super();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MinimalBlockEntity(RedstonecgModBlockEntities.REDSTONE_TO_RED_CU_CONVERTER.get(), pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return box(0, 0, 0, 16, 4, 16);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CONNECTION);
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		boolean canConnect = RedCuWireCanConnectRedstoneProcedure.toConverterWire(state, side);
		if(!canConnect){canConnect = RedCuWireCanConnectRedstoneProcedure.toConverterRedstone(state, side);}
		return canConnect;
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		Level world = (Level) blockAccess;
		if (RedCuWireCanConnectRedstoneProcedure.toConverterWire(blockstate, direction)) {
			return ((int) LittleTools.getBlockEntityNBTValue(world, pos, "power")) / 16;
		}
		return 0;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		LittleTools.setBlockEntityNBTValue(world, pos, 0, "power");
	}

	@Override
	public void onTick(LevelAccessor world, BlockPos pos){
		BlockState ThisBlock = world.getBlockState(pos);
		Direction BlockFaces = LittleTools.getDirection(ThisBlock);
		int connection = LittleTools.getIntegerProperty(ThisBlock, "connection");
		int SelfPower = 0;
		if (connection == 0 || connection >= 4) {
			SelfPower = GetRedstoneSignalProcedure.execute(world, pos, BlockFaces.getOpposite());
		}
		if (connection == 2 || connection == 3 || connection == 4 || connection == 6) {
			SelfPower = Math.max(SelfPower, GetRedstoneSignalProcedure.execute(world, pos, BlockFaces.getClockWise(Direction.Axis.Y)));
		}
		if (connection == 1 || connection == 3 || connection == 4 || connection == 8) {
			SelfPower = Math.max(SelfPower, GetRedstoneSignalProcedure.execute(world, pos, BlockFaces.getCounterClockWise(Direction.Axis.Y)));
		}
		if (connection == 10) {
			SelfPower = Math.max(SelfPower, GetRedstoneSignalProcedure.execute(world, pos, BlockFaces.getOpposite()));
		}
		SelfPower = SelfPower * 16 + 15;
		BlockPos CallPos = pos;
		if (connection <= 8 || connection == 11) {
			CallPos = pos.offset(BlockFaces.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, BlockFaces) - 1);
		}
		if (connection >= 9 || connection >= 5 && connection <= 6) {
			Direction dir = BlockFaces.getCounterClockWise(Direction.Axis.Y);
			CallPos = pos.offset(dir.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, dir) - 1);
		}
		if (connection >= 7) {
			Direction dir = BlockFaces.getClockWise(Direction.Axis.Y);
			CallPos = pos.offset(dir.getNormal());
			SelfPower = Math.max(SelfPower, GetWireRedstoneSignalProcedure.getIntPower(world, CallPos, dir) - 1);
		}
		if(LittleTools.getBlockEntityNBTValue(world, pos, "power") != SelfPower){
			LittleTools.setBooleanProperty(world, pos, !LittleTools.getBooleanProperty(ThisBlock, "powerchange") , "powerchange");
			LittleTools.setBlockEntityNBTValue(world, pos, SelfPower, "power");
		}
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		OnBlockRightClickedProcedure.execute(world, pos, blockstate);
		return InteractionResult.SUCCESS;
	}
}
