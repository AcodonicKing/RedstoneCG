
package net.acodonic_king.redstonecg.block.gui.redcu_crafter;

import net.acodonic_king.redstonecg.default_gui_classes.ButtonMessage;
import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.acodonic_king.redstonecg.RedstonecgMod;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RedCuCrafterGUIButtonMessage extends ButtonMessage {
	public RedCuCrafterGUIButtonMessage(FriendlyByteBuf buffer) {
		super(buffer);
	}
	public RedCuCrafterGUIButtonMessage(int buttonID, BlockPos pos) {
		super(buttonID, pos);
	}
	public static void handler(RedCuCrafterGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> handleButtonAction(Objects.requireNonNull(context.getSender()), message.buttonID, message.pos));
		context.setPacketHandled(true);
	}
	public static void sendAndHandle(Player entity, int buttonID, BlockPos pos){
		RedstonecgMod.PACKET_HANDLER.sendToServer(new RedCuCrafterGUIButtonMessage(buttonID, pos));
		handleButtonAction(entity, buttonID, pos);
	}
	public static void handleButtonAction(Player entity, int buttonID, BlockPos pos) {
		Level world = RedstonecgModVersionRides.getPlayerLevel(entity);
		HashMap guistate = RedCuCrafterGUIMenu.guistate;
		if (!world.hasChunkAt(pos))
			return;
		if (buttonID >= 0 && buttonID <= 4) {
			guistate.put("variable:block_category", buttonID);
			String strcategory = switch (buttonID) {
                case 0 -> "wires";
                case 1 -> "digital";
				case 2 -> "hybrid";
                case 3 -> "analog";
                case 4 -> "indicators";
                default -> "";
            };
            guistate.put("variable:block_category_string", strcategory);
			guistate.put("category:ingredients",null);
		}
		else if (buttonID >= 5 && buttonID <= 6) {
			guistate.put("variable:junction_type", buttonID-4);
			String strcategory = switch (buttonID - 5) {
                case 0 -> "/normal";
                case 1 -> "/parallel";
                default -> "";
            };
            guistate.put("variable:junction_type_string", strcategory);
			guistate.put("category:ingredients",null);
		}
	}
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RedstonecgMod.addNetworkMessage(RedCuCrafterGUIButtonMessage.class, RedCuCrafterGUIButtonMessage::buffer, RedCuCrafterGUIButtonMessage::new, RedCuCrafterGUIButtonMessage::handler);
	}
}
