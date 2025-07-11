package net.acodonic_king.redstonecg.init;

import net.acodonic_king.redstonecg.block.gui.redcu_wire_transition.RedCuWireTransitionGUIScreen;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.acodonic_king.redstonecg.block.gui.redcu_crafter.RedCuCrafterGUIScreen;
import net.acodonic_king.redstonecg.block.gui.analog_source.AnalogSourceGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RedstonecgModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(RedstonecgModMenus.ANALOG_SOURCE_GUI.get(), AnalogSourceGUIScreen::new);
			MenuScreens.register(RedstonecgModMenus.RED_CU_CRAFTER_GUI.get(), RedCuCrafterGUIScreen::new);
			MenuScreens.register(RedstonecgModMenus.REDCU_WIRE_TRANSITION_GUI.get(), RedCuWireTransitionGUIScreen::new);
		});
	}
}
