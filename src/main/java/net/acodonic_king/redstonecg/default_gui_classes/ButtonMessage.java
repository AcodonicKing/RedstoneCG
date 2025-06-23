package net.acodonic_king.redstonecg.default_gui_classes;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class ButtonMessage {
    public final int buttonID;
    public final BlockPos pos;
    public ButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        int x, y, z;
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        this.pos = new BlockPos(x, y, z);
    }
    public ButtonMessage(int buttonID, BlockPos pos) {
        this.buttonID = buttonID;
        this.pos = pos;
    }
    public static void buffer(ButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.pos.getX());
        buffer.writeInt(message.pos.getY());
        buffer.writeInt(message.pos.getZ());
    }
}
