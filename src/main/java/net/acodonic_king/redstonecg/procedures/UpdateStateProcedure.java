package net.acodonic_king.redstonecg.procedures;

import net.acodonic_king.redstonecg.RedstonecgMod;
import net.acodonic_king.redstonecg.init.RedstonecgModTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;

public class UpdateStateProcedure {
	public static void execute(LevelAccessor world, BlockPos pos, BlockPos fromPos) {
		BlockState ThisBlock = world.getBlockState(fromPos);
		if(!ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_GATES)){
			int[] propagator = {
				pos.getX() - fromPos.getX(),
				pos.getY() - fromPos.getY(),
				pos.getZ() - fromPos.getZ()
			};
			propagate(world, pos, propagator);
		}
	}
	public static void executeNoCheck(LevelAccessor world, BlockPos pos, BlockPos fromPos) {
		int[] propagator = {
			pos.getX() - fromPos.getX(),
			pos.getY() - fromPos.getY(),
			pos.getZ() - fromPos.getZ()
		};
		propagate(world, pos, propagator);
	}
	public static void propagate(LevelAccessor world, BlockPos pos, int[] propagator){
		//RedstonecgMod.LOGGER.debug(pos);
		BlockState ThisBlock = world.getBlockState(pos);
		Direction BlockFaces = LittleTools.getDirection(ThisBlock).getOpposite();
		if (world instanceof Level _lvl_getRedPow){
			if (ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_PROPAGATION_OPPOSITE)){
				_lvl_getRedPow.getSignal(pos, BlockFaces.getOpposite());
			} else {
				_lvl_getRedPow.getSignal(pos, BlockFaces);
			}
		}
		BlockPos newpos = pos.offset(propagator[0], propagator[1], propagator[2]);
		ThisBlock = world.getBlockState(newpos);
		if (ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_GATES)){
			propagate(world, newpos, propagator);
		}
	}
	public static void propagate(LevelAccessor world, BlockPos pos, Direction propagator){
		BlockState ThisBlock = world.getBlockState(pos);
		Direction BlockFaces = LittleTools.getDirection(ThisBlock).getOpposite();
		if (world instanceof Level _lvl_getRedPow){
			if (ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_PROPAGATION_OPPOSITE)){
				_lvl_getRedPow.getSignal(pos, BlockFaces.getOpposite());
			} else {
				_lvl_getRedPow.getSignal(pos, BlockFaces);
			}
		}
		BlockPos newpos = pos.offset(propagator.getNormal());
		ThisBlock = world.getBlockState(newpos);
		if (ThisBlock.is(RedstonecgModTags.Blocks.PARALLEL_GATES)){
			propagate(world, newpos, propagator);
		}
	}
}
