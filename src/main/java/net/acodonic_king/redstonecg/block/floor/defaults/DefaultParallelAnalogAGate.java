package net.acodonic_king.redstonecg.block.floor.defaults;

import net.acodonic_king.redstonecg.procedures.GetParallelSignalProcedure;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.acodonic_king.redstonecg.procedures.UpdateStateProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DefaultParallelAnalogAGate extends DefaultGate {
    public static final IntegerProperty POWER = IntegerProperty.create("power",0,15);
    public DefaultParallelAnalogAGate(){
        super();
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
    }
    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }
    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
        UpdateStateProcedure.execute(world, pos, fromPos);
    }
    public int redstonePowerOperation(int LinePower, int BackPower){return 0;}
    public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
        int[] SidePower = GetParallelSignalProcedure.execute(world, pos, direction);
        if (SidePower[0] == -2) {
            return SidePower[1];
        }
        int output = redstonePowerOperation(SidePower[0], SidePower[1]);
        LittleTools.setIntegerProperty(world, pos, output, "power");
        return output;
    }
    @Override
    public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
        Level world = (Level) blockAccess;
        return this.emittedRedstonePower(world, pos, direction);
    }
}
