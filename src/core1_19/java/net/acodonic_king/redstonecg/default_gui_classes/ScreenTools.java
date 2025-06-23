package net.acodonic_king.redstonecg.default_gui_classes;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

public class ScreenTools {
    public static ResourceLocation getImage(String name){
        return new ResourceLocation(name);
    }
    public static ResourceLocation getImage(String namespace, String identification){
        return new ResourceLocation(namespace, identification);
    }
    public static void renderRectangle(PoseStack poseStack, int color, int x, int y, int l, int w, int h) {
        poseStack.pushPose();
        poseStack.translate(0, 0, l);
        GuiComponent.fill(poseStack,x,y,x+w,y+h,color);
        poseStack.popPose();
    }
    public static void blitTexture(Screen the, PoseStack ms, int x, int y, int w, int h, ResourceLocation image){
        RenderSystem.setShaderTexture(0, image);
        the.blit(ms, x, y, 0, 0, w, h, w, h);
    }
    public static void drawString(Font font, PoseStack ms, String text, int x, int y, int c){
        font.draw(ms, text, x, y, c);
    }
}
