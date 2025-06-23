package net.acodonic_king.redstonecg.block.floor.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import net.acodonic_king.redstonecg.init.RedstonecgModBlockEntities;

public class RedCuWireBlockEntity extends MinimalBlockEntity {
	public RedCuWireBlockEntity(BlockPos position, BlockState state) {
		super(RedstonecgModBlockEntities.RED_CU_WIRE.get(), position, state);
	}
}
