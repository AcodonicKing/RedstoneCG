
package net.acodonic_king.redstonecg.network;

import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.acodonic_king.redstonecg.world.inventory.RedCuCrafterGUIMenu;
import net.acodonic_king.redstonecg.client.gui.RedCuCrafterGUIScreen.ingredients_option;
import net.acodonic_king.redstonecg.RedstonecgMod;

import java.util.function.Supplier;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.inventory.Slot;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RedCuCrafterGUISlotMessage {
	private final int slotID, changeType, meta;
	private BlockPos pos;

	public RedCuCrafterGUISlotMessage(int slotID, BlockPos pos, int changeType, int meta) {
		this.slotID = slotID;
		this.pos = pos;
		this.changeType = changeType;
		this.meta = meta;
	}

	public RedCuCrafterGUISlotMessage(FriendlyByteBuf buffer) {
		this.slotID = buffer.readInt();
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		this.pos = new BlockPos(x, y, z);
		this.changeType = buffer.readInt();
		this.meta = buffer.readInt();
	}

	public static void buffer(RedCuCrafterGUISlotMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.pos.getX());
		buffer.writeInt(message.pos.getY());
		buffer.writeInt(message.pos.getZ());
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}

	public static void handler(RedCuCrafterGUISlotMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int slotID = message.slotID;
			int changeType = message.changeType;
			int meta = message.meta;
			BlockPos pos = message.pos;
			handleSlotAction(entity, slotID, changeType, meta, pos);
		});
		context.setPacketHandled(true);
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, BlockPos pos) {
		Level world = RedstonecgModVersionRides.getPlayerLevel(entity);
		HashMap guistate = RedCuCrafterGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(pos))
			return;
		if (slot == 3 && changeType == 2){
			Object ingredientsobj = guistate.get("category:ingredients");
			if(ingredientsobj != null) {
				ingredients_option ingredientsopt = (ingredients_option) ingredientsobj;
				NonNullList<Ingredient> ingredients = ingredientsopt.recipe.getIngredients();
				if (entity.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					int amt = 64;
					for(int i = 0; i < ingredients.size(); i++){
						if (ingredients.get(i).getItems().length != 0) {
							Slot theslot = (Slot) _slots.get(i);
							ItemStack stack = theslot.getItem();
							amt = Math.min(amt,stack.getCount());
						}
					}
					ItemStack result = RedCuCrafterGUIMenu.getResultItem();
					amt = Math.min(result.getCount() * amt, result.getMaxStackSize());
					amt /= result.getCount();
					result.setCount(result.getCount() * amt);
					((Slot) _slots.get(3)).set(result);
					amt -= 1;
					for(int i = 0; i < ingredients.size(); i++){
						if(ingredients.get(i).getItems().length != 0){
							((Slot) _slots.get(i)).remove(amt);
						}
					}
				}
			}
		}
		if (slot == 3 && changeType == 1) {
			Object ingredientsobj = guistate.get("category:ingredients");
			if(ingredientsobj != null){
				ingredients_option ingredientsopt = (ingredients_option) ingredientsobj;
				NonNullList<Ingredient> ingredients = ingredientsopt.recipe.getIngredients();
	
			if (entity.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					for(int i = 0; i < ingredients.size(); i++){
	            		if(ingredients.get(i).getItems().length != 0){
							((Slot) _slots.get(i)).remove(1);
	            		}
					}
					entity.containerMenu.broadcastChanges();
				}
			}
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RedstonecgMod.addNetworkMessage(RedCuCrafterGUISlotMessage.class, RedCuCrafterGUISlotMessage::buffer, RedCuCrafterGUISlotMessage::new, RedCuCrafterGUISlotMessage::handler);
	}
}
