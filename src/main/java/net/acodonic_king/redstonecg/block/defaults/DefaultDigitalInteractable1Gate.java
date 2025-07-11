package net.acodonic_king.redstonecg.block.defaults;

import net.acodonic_king.redstonecg.procedures.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DefaultDigitalInteractable1Gate extends DefaultDigitalInteractableGate {
    public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,2);
    public DefaultDigitalInteractable1Gate(){super();}
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONNECTION);
    }
    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        ConnectionFace connectionFaceB = BlockFrameTransformUtils.canConnectRedstoneTargetConnectionFace(world, pos, side);
        return CanConnectWallGateProcedure.To1Gate(state, connectionFaceB);
    }
    public boolean redstoneOutputOperation(int SidePower){return false;}
    @Override
    public int onRedstoneUpdate(LevelAccessor world, BlockState blockState, BlockPos pos){
        Direction Side = GetGateInputSidesProcedure.Get1GateForth(blockState);
        ConnectionFace thisFace = BlockFrameTransformUtils.getConnectionFace(blockState, Side);
        int SidePower = GetRedstoneSignalProcedure.execute(world, pos, thisFace);
        boolean output = this.redstoneOutputOperation(SidePower);
        setOutput(world, blockState, pos, output);
        if (output) {return 15;}
        return 0;
    }
}
