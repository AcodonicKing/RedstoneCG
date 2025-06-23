package net.acodonic_king.redstonecg.block.floor.defaults;

import net.acodonic_king.redstonecg.procedures.GetParallelSignalProcedure;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.acodonic_king.redstonecg.procedures.OnBlockRightClickedProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class DefaultParallelDigitalInteractableABGate extends DefaultParallelDigitalAGate {
    public static final IntegerProperty CONNECTION = IntegerProperty.create("connection",0,1);
    public DefaultParallelDigitalInteractableABGate(){
        super();
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONNECTION);
    }

    @Override
    public int emittedRedstonePower(LevelAccessor world, BlockPos pos, Direction direction){
        int[] SidePower = GetParallelSignalProcedure.execute(world, pos, direction);
        if (SidePower[0] == -2) {
            return SidePower[1];
        }
        boolean output = false;
        if(LittleTools.getIntegerProperty(world.getBlockState(pos), "connection") == 0){
            output = this.redstoneOutputOperation(SidePower[1], SidePower[0]);
        } else {
            output = this.redstoneOutputOperation(SidePower[0], SidePower[1]);
        }
        LittleTools.setBooleanProperty(world, pos, output, "output");
        if (output) {return 15;}
        return 0;
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        OnBlockRightClickedProcedure.execute(world, pos, blockstate);
        return InteractionResult.SUCCESS;
    }
}
