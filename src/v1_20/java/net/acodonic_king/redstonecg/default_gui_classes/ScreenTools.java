package net.acodonic_king.redstonecg.default_gui_classes;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.Font;

public class ScreenTools {
    public static ResourceLocation getImage(String name){
        return new ResourceLocation(name);
    }
    public static ResourceLocation getImage(String namespace, String identification){
        return new ResourceLocation(namespace, identification);
    }
    public static void renderRectangle(GuiGraphics gui, int color, int x, int y, int l, int w, int h) {
        gui.pose().pushPose();
        gui.pose().translate(0, 0, l);
        gui.fill(x,y,x+w,y+h,color);
        gui.pose().popPose();
    }
    public static void blitTexture(Screen the, GuiGraphics ms, int x, int y, int w, int h, ResourceLocation image){
        ms.blit(image, x, y, 0, 0, w, h, w, h);
    }
    public static void drawString(Font font, GuiGraphics ms, String text, int x, int y, int c){
        ms.drawString(font, text, x, y, c);
    }
}
