package net.acodonic_king.redstonecg.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.acodonic_king.redstonecg.world.inventory.RedCuCrafterGUIMenu;
import net.acodonic_king.redstonecg.world.inventory.AnalogSourceGUIMenu;
import net.acodonic_king.redstonecg.RedstonecgMod;

public class RedstonecgModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RedstonecgMod.MODID);
	public static final RegistryObject<MenuType<AnalogSourceGUIMenu>> ANALOG_SOURCE_GUI = REGISTRY.register("analog_source_gui", () -> IForgeMenuType.create(AnalogSourceGUIMenu::new));
	public static final RegistryObject<MenuType<RedCuCrafterGUIMenu>> RED_CU_CRAFTER_GUI = REGISTRY.register("red_cu_crafter_gui", () -> IForgeMenuType.create(RedCuCrafterGUIMenu::new));
}
