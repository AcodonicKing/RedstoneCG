package net.acodonic_king.redstonecg.block.floor.defaults;

import net.acodonic_king.redstonecg.procedures.CanConnectRedstoneGateProcedure;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DefaultEmitting1_4Gate extends DefaultGate {
    public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,14);
    public static final IntegerProperty POWER = IntegerProperty.create("power",0,15);
    public DefaultEmitting1_4Gate(){
        super();
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONNECTION,POWER);
    }
    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return CanConnectRedstoneGateProcedure.To1_4Gate(state, side);
    }
    public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
        BlockState ThisBlock = (world.getBlockState(pos));
        if (CanConnectRedstoneGateProcedure.To1_4Gate(ThisBlock, direction)) {
            return LittleTools.getIntegerProperty(ThisBlock, "power");
        }
        return 0;
    }
    @Override
    public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
        Level world = (Level) blockAccess;
        return this.emittedRedstonePower(world, pos, direction);
    }
}
