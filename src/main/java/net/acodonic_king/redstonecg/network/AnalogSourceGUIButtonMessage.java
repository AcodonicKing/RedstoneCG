
package net.acodonic_king.redstonecg.network;

import net.acodonic_king.redstonecg.default_gui_classes.ButtonMessage;
import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.acodonic_king.redstonecg.procedures.OnBlockRightClickedProcedure;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.acodonic_king.redstonecg.RedstonecgMod;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnalogSourceGUIButtonMessage extends ButtonMessage {
	public AnalogSourceGUIButtonMessage(FriendlyByteBuf buffer) {
		super(buffer);
	}
	public AnalogSourceGUIButtonMessage(int buttonID, BlockPos pos) {
		super(buttonID, pos);
	}
	public static void handler(AnalogSourceGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> handleButtonAction(Objects.requireNonNull(context.getSender()), message.buttonID, message.pos));
		context.setPacketHandled(true);
	}
	public static void sendAndHandle(Player entity, int buttonID, BlockPos pos){
		RedstonecgMod.PACKET_HANDLER.sendToServer(new AnalogSourceGUIButtonMessage(buttonID, pos));
		handleButtonAction(entity, buttonID, pos);
	}
	public static void handleButtonAction(Player entity, int buttonID, BlockPos pos) {
		Level world = RedstonecgModVersionRides.getPlayerLevel(entity);
		if (!world.hasChunkAt(pos))
			return;
		//HashMap guistate = AnalogSourceGUIMenu.guistate;
		if (buttonID < 2){
			BlockState ThisBlock = world.getBlockState(pos);
			if (ThisBlock.getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _integerProp){
				int _value = ThisBlock.getValue(_integerProp) + buttonID + buttonID - 1;
				if (_integerProp.getPossibleValues().contains(_value))
					world.setBlock(pos, ThisBlock.setValue(_integerProp, _value), 3);
			}
		}
		if (buttonID == 2) {
			OnBlockRightClickedProcedure.execute(world, pos);
		}
	}
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RedstonecgMod.addNetworkMessage(AnalogSourceGUIButtonMessage.class, AnalogSourceGUIButtonMessage::buffer, AnalogSourceGUIButtonMessage::new, AnalogSourceGUIButtonMessage::handler);
	}
}
