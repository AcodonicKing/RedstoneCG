package net.acodonic_king.redstonecg.init;

import net.acodonic_king.redstonecg.client.gui.RedCuCrafterGUIScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;

//1.20 & 1.20.1
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class RedstonecgModVersionRides {
    public static Capability<IItemHandler> item_handler = ForgeCapabilities.ITEM_HANDLER;
    public static boolean chat_type = false;
    public static Button createButton(int x, int y, int w, int h, String text, Button.OnPress onPress) {
        return Button.builder(Component.translatable(text), onPress).pos(x, y).size(w, h).build();
    }
    public static BlockItem createBlockItem(RegistryObject<Block> block){
        return new BlockItem(block.get(), new Item.Properties());
    }
    public static Item.Properties newItemSuper(int stacksize){
        return new Item.Properties().stacksTo(stacksize).rarity(Rarity.COMMON);
    }
    public static void renderItem(RedCuCrafterGUIScreen screen, GuiGraphics ms, ItemStack itemStack, int x, int y){
        ms.renderItem(itemStack, x, y);
    }
    public static Level getPlayerLevel(Player player){
        return player.level();
    }
    public static Level getPlayerLevel(Entity entity){
        return entity.level();
    }
}