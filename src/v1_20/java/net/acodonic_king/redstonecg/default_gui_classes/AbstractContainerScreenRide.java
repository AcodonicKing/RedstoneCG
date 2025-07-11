package net.acodonic_king.redstonecg.default_gui_classes;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class AbstractContainerScreenRide<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    public AbstractContainerScreenRide(T p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        render(new ScreenStack(guiGraphics), mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    public void render(ScreenStack ms, int mouseX, int mouseY, float partialTicks) {}

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        renderBg(new ScreenStack(guiGraphics), v, i, i1);
    }

    public void renderBg(ScreenStack ms, float v, int i, int i1){}

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderLabels(new ScreenStack(guiGraphics), mouseX, mouseY);
    }

    public void renderLabels(ScreenStack ms, int mouseX, int mouseY){}

    public void renderItemStack(ScreenStack ms, ItemStack itemStack, int x, int y){
        ms.stack.renderItem(itemStack, x, y);
    }

    public static class ScreenStack{
        public GuiGraphics stack;
        public ScreenStack(GuiGraphics guiGraphics){
            this.stack = guiGraphics;
        }
    }
}
