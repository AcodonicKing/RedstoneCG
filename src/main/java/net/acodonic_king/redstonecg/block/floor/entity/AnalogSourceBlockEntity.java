package net.acodonic_king.redstonecg.block.floor.entity;

import net.acodonic_king.redstonecg.block.entity.DefaultContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.acodonic_king.redstonecg.world.inventory.AnalogSourceGUIMenu;
import net.acodonic_king.redstonecg.init.RedstonecgModBlockEntities;

import io.netty.buffer.Unpooled;

public class AnalogSourceBlockEntity extends DefaultContainerBlockEntity {
	public AnalogSourceBlockEntity(BlockPos position, BlockState state) {
		super(RedstonecgModBlockEntities.ANALOG_SOURCE.get(), position, state, 0);
	}

	@Override
	public Component getDefaultName() {
		return Component.literal("analog_source");
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new AnalogSourceGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Analog Source");
	}

	@Override
	public boolean canTakeItemThroughFace(int p_19239_, ItemStack p_19240_, Direction p_19241_) {
		return false;
	}
}
