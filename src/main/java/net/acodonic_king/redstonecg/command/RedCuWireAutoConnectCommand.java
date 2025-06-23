
package net.acodonic_king.redstonecg.command;

import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.acodonic_king.redstonecg.network.RedstonecgModVariables;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import com.mojang.brigadier.arguments.BoolArgumentType;

@Mod.EventBusSubscriber
public class RedCuWireAutoConnectCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("redcuwireautoconnect")

				.then(Commands.argument("state", BoolArgumentType.bool()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();
					{
						RedstonecgModVariables.MapVariables.get(world).enableredcuwireautoconnect = BoolArgumentType.getBool(arguments, "state");
						RedstonecgModVariables.MapVariables.get(world).syncData(world);
						if (RedstonecgModVariables.MapVariables.get(world).enableredcuwireautoconnect) {
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("RedCu Wires automatic connection enabled."), RedstonecgModVersionRides.chat_type);
						} else {
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("RedCu Wires automatic connection disabled."), RedstonecgModVersionRides.chat_type);
						}
					}
					return 0;
				})));
	}
}
