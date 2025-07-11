package net.acodonic_king.redstonecg.default_gui_classes;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.acodonic_king.redstonecg.procedures.RCGMatrix;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScreenTools {
    public static ResourceLocation getImage(String name){
        return new ResourceLocation(name);
    }
    public static ResourceLocation getImage(String namespace, String identification){
        return new ResourceLocation(namespace, identification);
    }
    public static void renderRectangle(AbstractContainerScreenRide.ScreenStack ms, int color, int x, int y, int l, int w, int h) {
        ms.stack.pushPose();
        ms.stack.translate(0, 0, l);
        GuiComponent.fill(ms.stack,x,y,x+w,y+h,color);
        ms.stack.popPose();
    }
    public static void blitTexture(Screen the, AbstractContainerScreenRide.ScreenStack ms, int x, int y, int w, int h, ResourceLocation image){
        RenderSystem.setShaderTexture(0, image);
        the.blit(ms.stack, x, y, 0, 0, w, h, w, h);
    }
    public static void drawString(Font font, AbstractContainerScreenRide.ScreenStack ms, String text, int x, int y, int c){
        font.drawShadow(ms.stack, text, x, y, c);
    }
    public static void setTexture(ResourceLocation image){
        RenderSystem.setShaderTexture(0, image);
    }
    public static void blitSetTexture(Screen the, AbstractContainerScreenRide.ScreenStack ms, int x, int y, int w, int h){
        the.blit(ms.stack, x, y, 0, 0, w, h, w, h);
    }
    public static void blitSetTextureRegion(AbstractContainerScreenRide.ScreenStack ms, int OnScreenLeft, int OnScreenTop, int RenderWidthPx, int RenderHeightPx, int OnImageLeftPx, int OnImageTopPx, int ImageWidth, int ImageHeight){
        float OnImageLeft = (float) OnImageLeftPx / ImageWidth;
        float OnImageRight = (float) (OnImageLeftPx + RenderWidthPx) / ImageWidth;
        float OnImageTop = (float) OnImageTopPx / ImageHeight;
        float OnImageBottom = (float) (OnImageTopPx + RenderHeightPx) / ImageHeight;
        blitSetTextureRegion(
                ms,
                OnScreenLeft,OnScreenLeft + RenderWidthPx,
                OnScreenTop,OnScreenTop+ RenderHeightPx,
                0,
                OnImageLeft, OnImageRight,
                OnImageTop, OnImageBottom
        );
    }
    public static void blitSetTextureRegion(AbstractContainerScreenRide.ScreenStack ms, int OnScreenLeft, int OnScreenRight, int OnScreenTop, int OnScreenBottom, int ScreenLayer, float OnImageLeft, float OnImageRight, float OnImageTop, float OnImageBottom) {
        RCGMatrix.M4F matrix4f = new RCGMatrix.M4F(ms.stack.last().pose());
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f.matrix, (float) OnScreenLeft,  (float) OnScreenBottom, (float) ScreenLayer).uv(OnImageLeft,  OnImageBottom).endVertex();
        bufferbuilder.vertex(matrix4f.matrix, (float) OnScreenRight, (float) OnScreenBottom, (float) ScreenLayer).uv(OnImageRight, OnImageBottom).endVertex();
        bufferbuilder.vertex(matrix4f.matrix, (float) OnScreenRight, (float) OnScreenTop,    (float) ScreenLayer).uv(OnImageRight, OnImageTop   ).endVertex();
        bufferbuilder.vertex(matrix4f.matrix, (float) OnScreenLeft,  (float) OnScreenTop,    (float) ScreenLayer).uv(OnImageLeft,  OnImageTop   ).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
    }
}
