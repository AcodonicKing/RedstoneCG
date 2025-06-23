package net.acodonic_king.redstonecg.block.floor.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import net.acodonic_king.redstonecg.init.RedstonecgModBlockEntities;

public class RedstoneToRedCuConverterBlockEntity extends MinimalBlockEntity {
	public RedstoneToRedCuConverterBlockEntity(BlockPos position, BlockState state) {
		super(RedstonecgModBlockEntities.REDSTONE_TO_RED_CU_CONVERTER.get(), position, state);
	}
}
