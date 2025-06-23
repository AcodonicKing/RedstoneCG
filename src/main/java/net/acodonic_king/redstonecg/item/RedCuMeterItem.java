
package net.acodonic_king.redstonecg.item;

import net.acodonic_king.redstonecg.init.RedstonecgModTags;
import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RedCuMeterItem extends Item {
	public RedCuMeterItem() {
		super(RedstonecgModVersionRides.newItemSuper(1));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected) {
			if (entity == null)
				return;
			if (entity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(itemstack.getItem())) {
				return;
			}
			int RayDistance = 1;
			Vec3 eyep = entity.getEyePosition(1f);
			Level level = RedstonecgModVersionRides.getPlayerLevel(entity);
			BlockPos LookPos = level.clip(new ClipContext(eyep, eyep.add(entity.getViewVector(1f).scale(RayDistance)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();
			while (world.getBlockState(LookPos).is(RedstonecgModTags.Blocks.AIRS)) {
				RayDistance++;
				if (RayDistance > 10) {
					itemstack.getOrCreateTag().putBoolean("measured", false);
					return;
				}
				LookPos = level.clip(new ClipContext(eyep, eyep.add(entity.getViewVector(1f).scale(RayDistance)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();
			}
			BlockState ThisBlock = world.getBlockState(LookPos);
			double measure = 0;
			if (ThisBlock.is(RedstonecgModTags.Blocks.REDCUWIRES)) {
				measure = LittleTools.getBlockEntityNBTValue(world, LookPos, "power") / 16;
				if (measure == 0 && itemstack.getOrCreateTag().getDouble("measuring") > 0) {
					return;
				}
			} else if (ThisBlock.is(RedstonecgModTags.Blocks.BOOLEAN_OUTPUT)) {
				if(LittleTools.getBooleanProperty(ThisBlock,"output")){
					measure = 15;
				} else {
                    measure = world.getSignal(LookPos, LittleTools.getDirection(ThisBlock).getOpposite());
                }
			} else if (ThisBlock.is(RedstonecgModTags.Blocks.ANALOG_OUTPUT)) {
				measure = LittleTools.getIntegerProperty(ThisBlock, "power");
			} else {
				measure = world.getBestNeighborSignal(LookPos);
			}
			itemstack.getOrCreateTag().putDouble("measuring", measure);
			itemstack.getOrCreateTag().putBoolean("measured", true);
		}
	}
}
