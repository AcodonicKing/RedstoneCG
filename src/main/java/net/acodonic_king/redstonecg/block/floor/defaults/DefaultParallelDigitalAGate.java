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
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class DefaultParallelDigitalAGate extends DefaultGate {
    public static final BooleanProperty OUTPUT = BooleanProperty.create("output");
    public DefaultParallelDigitalAGate(){
        super();
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OUTPUT);
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
    public boolean redstoneOutputOperation(int LinePower, int BackPower){return false;}
    public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
        int[] SidePower = GetParallelSignalProcedure.execute(world, pos, direction);
        if (SidePower[0] == -2) {
            return SidePower[1];
        }
        boolean output = this.redstoneOutputOperation(SidePower[0], SidePower[1]);
        LittleTools.setBooleanProperty(world, pos, output, "output");
        if (output) {return 15;}
        return 0;
    }
    @Override
    public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
        Level world = (Level) blockAccess;
        return this.emittedRedstonePower(world, pos, direction);
    }
}
