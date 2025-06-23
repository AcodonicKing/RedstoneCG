package net.acodonic_king.redstonecg.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.acodonic_king.redstonecg.client.gui.RedCuCrafterGUIScreen;
import net.acodonic_king.redstonecg.client.gui.AnalogSourceGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RedstonecgModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(RedstonecgModMenus.ANALOG_SOURCE_GUI.get(), AnalogSourceGUIScreen::new);
			MenuScreens.register(RedstonecgModMenus.RED_CU_CRAFTER_GUI.get(), RedCuCrafterGUIScreen::new);
		});
	}
}
