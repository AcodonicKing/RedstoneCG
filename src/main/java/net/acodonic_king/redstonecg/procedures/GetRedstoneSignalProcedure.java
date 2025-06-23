package net.acodonic_king.redstonecg.procedures;

import net.acodonic_king.redstonecg.init.RedstonecgModTags;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class GetRedstoneSignalProcedure {
	public static int execute(LevelAccessor world, BlockPos pos, Direction Side) {
		if (Side == null)
			return 0;
		int SidePower = 0;
		BlockPos CallPos = pos.offset(Side.getNormal());
		BlockState block = world.getBlockState(CallPos);
		if (!block.is(RedstonecgModTags.Blocks.NORMAL_NEIGHBOR_REDSTONE_UPDATE)) {
			if (block.is(RedstonecgModTags.Blocks.BOOLEAN_OUTPUT)) {
				if ((LittleTools.getDirection(block) == Side.getOpposite()) && LittleTools.getBooleanProperty(block,"output")) {
					return 15;
				}
				return 0;
			} else if (block.is(RedstonecgModTags.Blocks.ANALOG_OUTPUT)) {
				if (LittleTools.getDirection(block) == (Side.getOpposite())) {
					return LittleTools.getIntegerProperty(block,"power");
				}
				return 0;
			}
		}
		SidePower = world instanceof Level _lvl_getRedPow ? _lvl_getRedPow.getSignal(CallPos, Side) : 0;
		if (SidePower == 0) {
			if (block.getBlock() == Blocks.REDSTONE_WIRE) {
				SidePower = LittleTools.getIntegerProperty(block,"power");
			}
		}
		return SidePower;
	}
}
