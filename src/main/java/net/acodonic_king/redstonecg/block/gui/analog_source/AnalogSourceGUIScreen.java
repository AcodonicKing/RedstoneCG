package net.acodonic_king.redstonecg.block.gui.analog_source;

import net.acodonic_king.redstonecg.block.normal.analog.AnalogSourceBlock;
import net.acodonic_king.redstonecg.default_gui_classes.AbstractContainerScreenRide;
import net.acodonic_king.redstonecg.default_gui_classes.ScreenTools;
import net.acodonic_king.redstonecg.init.RedstonecgModVersionRides;
import net.acodonic_king.redstonecg.procedures.LittleTools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.level.block.state.BlockState;

public class AnalogSourceGUIScreen extends AbstractContainerScreenRide<AnalogSourceGUIMenu> {
	private final static HashMap<String, Object> guistate = AnalogSourceGUIMenu.guistate;
	private final Level world;
	private final BlockPos pos;
	private final Player entity;
	Button button_add;
	Button button_sub;
	Button button_change;

	public AnalogSourceGUIScreen(AnalogSourceGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.pos = container.pos;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 83;
	}
	private static final ResourceLocation background_texture = ScreenTools.getImage("redstonecg","textures/screens/analog_source_gui.png");
	private static final ResourceLocation block_textures = ScreenTools.getImage("redstonecg","textures/block/analog_source.png");
	private static final ResourceLocation pinmark_f = ScreenTools.getImage("redstonecg","textures/block/pins/f.png");
	private static final ResourceLocation pinmark_r = ScreenTools.getImage("redstonecg","textures/block/pins/r.png");
	private static final ResourceLocation pinmark_b = ScreenTools.getImage("redstonecg","textures/block/pins/b.png");
	private static final ResourceLocation pinmark_l = ScreenTools.getImage("redstonecg","textures/block/pins/l.png");

	@Override
	public void render(ScreenStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms.stack);
		//super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms.stack, mouseX, mouseY);
	}

	@Override
	public void renderBg(ScreenStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		ScreenTools.blitTexture(this, ms, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, background_texture);
		ScreenTools.blitTexture(this, ms, this.leftPos + 7, this.topPos + 7, 32, 32, block_textures);
		int connection = LittleTools.getIntegerProperty(this.world.getBlockState(this.pos),"connection");
		int lp = this.leftPos + 7;
		int tp = this.topPos + 7;
		if ((connection & 3) == 0 || (connection & 3) == 2) {
			ScreenTools.blitTexture(this, ms, lp, tp, 32, 32, pinmark_f);
		}
		if ((connection & 3) == 1 || (connection & 3) == 2) {
			ScreenTools.blitTexture(this, ms, lp, tp, 32, 32, pinmark_r);
		}
		if ((connection > 2 && connection < 7) || connection > 10) {
			ScreenTools.blitTexture(this, ms, lp, tp, 32, 32, pinmark_b);
		}
		if (connection > 6) {
			ScreenTools.blitTexture(this, ms, lp, tp, 32, 32, pinmark_l);
		}

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	public void renderLabels(ScreenStack ms, int mouseX, int mouseY) {
		//int value = LittleTools.getIntegerProperty(this.world.getBlockState(pos),"power");
		BlockState ThisBlock = this.world.getBlockState(pos);
		if(ThisBlock.getBlock() instanceof AnalogSourceBlock b) {
			int value = b.getPower(this.world, pos);
			ScreenTools.drawString(this.font, ms, new java.text.DecimalFormat("##.##").format(value), 116, 29, 0xF0F0F0);
		}
	}

	@Override
	public void init() {
		super.init();
		button_sub = RedstonecgModVersionRides.createButton(this.leftPos + 94, this.topPos + 55, 30, 20, "gui.redstonecg.analog_source_gui.button_sub", e -> {
			AnalogSourceGUIButtonMessage.sendAndHandle(entity, 0, this.pos);
		});
		guistate.put("button:button_sub", button_sub);
		this.addRenderableWidget(button_sub);
		button_add = RedstonecgModVersionRides.createButton(this.leftPos + 133, this.topPos + 55, 30, 20, "gui.redstonecg.analog_source_gui.button_add", e -> {
			AnalogSourceGUIButtonMessage.sendAndHandle(entity, 1, this.pos);
		});
		guistate.put("button:button_add", button_add);
		this.addRenderableWidget(button_add);
		button_change = RedstonecgModVersionRides.createButton(this.leftPos + 8, this.topPos + 55, 56, 20, "gui.redstonecg.analog_source_gui.button_change", e -> {
			AnalogSourceGUIButtonMessage.sendAndHandle(entity, 2, this.pos);
		});
		guistate.put("button:button_change", button_change);
		this.addRenderableWidget(button_change);
	}
}
