package net.acodonic_king.redstonecg.block.gui.redcu_wire_transition;

import net.acodonic_king.redstonecg.RedstonecgMod;
import net.acodonic_king.redstonecg.block.entity.RedCuWireTransitionBlockEntity;
import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RedCuWireTransitionGUIButtonMessage {
    public final String button;
    public final BlockPos pos;

    public static void buffer(RedCuWireTransitionGUIButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeUtf(message.button);
        buffer.writeInt(message.pos.getX());
        buffer.writeInt(message.pos.getY());
        buffer.writeInt(message.pos.getZ());
    }
    public RedCuWireTransitionGUIButtonMessage(FriendlyByteBuf buffer) {
        this.button = buffer.readUtf();
        int x, y, z;
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        this.pos = new BlockPos(x, y, z);
    }
    public RedCuWireTransitionGUIButtonMessage(String button, BlockPos pos) {
        this.button = button;
        this.pos = pos;
    }
    public static void handler(RedCuWireTransitionGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> handleButtonAction(Objects.requireNonNull(context.getSender()), message.button, message.pos));
        context.setPacketHandled(true);
    }
    public static void sendAndHandle(Player entity, String button, BlockPos pos){
        RedstonecgMod.PACKET_HANDLER.sendToServer(new RedCuWireTransitionGUIButtonMessage(button, pos));
        handleButtonAction(entity, button, pos);
    }
    public static void handleButtonAction(Player entity, String button, BlockPos pos) {
        Level world = RedstonecgModVersionRides.getPlayerLevel(entity);
        if (!world.hasChunkAt(pos))
            return;
        if (world.isClientSide())
            return;
        if(RedCuWireTransitionGUIScreen.button_locations.containsKey(button)){
            if(world.getBlockEntity(pos) instanceof RedCuWireTransitionBlockEntity be){
                String node = button;
                char side = node.charAt(0);
                byte val = be.getSideCharacter(side);
                //RedstonecgMod.LOGGER.debug("reading {} {}", side, (int) val);
                byte set_to = (byte) (node.charAt(1) - '0');
                if(val == set_to){set_to = 5;}
                //RedstonecgMod.LOGGER.debug("{} {} {}", node, side, (int) set_to);
                be.setSideCharacter(side, set_to);
                be.setChanged();
                BlockState bs = world.getBlockState(pos);
                //world.setBlock(pos, bs, 11);
                //Block.updateOrDestroy(bs,bs,world,pos,3);
                //bs = bs.getBlock().updateShape(bs, Direction.UP, bs, world, pos, pos);
                world.sendBlockUpdated(pos, bs ,bs ,3);
                //world.scheduleTick(pos,bs.getBlock(),4);
                //Block.UP
            }
        }
    }
    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        RedstonecgMod.addNetworkMessage(RedCuWireTransitionGUIButtonMessage.class, RedCuWireTransitionGUIButtonMessage::buffer, RedCuWireTransitionGUIButtonMessage::new, RedCuWireTransitionGUIButtonMessage::handler);
    }
}
