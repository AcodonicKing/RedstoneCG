package net.acodonic_king.redstonecg.block.floor.defaults;

import net.acodonic_king.redstonecg.procedures.GetGateInputSidesProcedure;
import net.acodonic_king.redstonecg.procedures.GetRedstoneSignalProcedure;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DefaultDigitalInteractable2ABCGate extends DefaultDigitalInteractableGate {
    public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,5);
    public DefaultDigitalInteractable2ABCGate(){super();}
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONNECTION);
    }
    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) { return true; }
    public boolean redstoneOutputOperation(int SideAPower, int SideBPower, int SideCPower){return false;}
    @Override
    public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
        BlockState ThisBlock = (world.getBlockState(pos));
        Direction BlockFacing = LittleTools.getDirection(ThisBlock).getOpposite();
        if (!(direction == BlockFacing)) {return 0;}
        Direction[] Sides = GetGateInputSidesProcedure.Get3ABCGate(ThisBlock);
        int SideAPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[0]);
        int SideBPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[1]);
        int SideCPower = GetRedstoneSignalProcedure.execute(world, pos, Sides[2]);
        boolean output = this.redstoneOutputOperation(SideAPower, SideBPower, SideCPower);
        LittleTools.setBooleanProperty(world, pos, output, "output");
        if (output) {return 15;}
        return 0;
    }
}
