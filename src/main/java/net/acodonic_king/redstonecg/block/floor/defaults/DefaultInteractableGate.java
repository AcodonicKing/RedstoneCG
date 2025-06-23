package net.acodonic_king.redstonecg.block.floor.defaults;

import net.acodonic_king.redstonecg.procedures.OnBlockRightClickedProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DefaultInteractableGate extends DefaultGate {
    public DefaultInteractableGate(){
        super();
    }
    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        OnBlockRightClickedProcedure.execute(world, pos, blockstate);
        return InteractionResult.SUCCESS;
    }
}
