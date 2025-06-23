package net.acodonic_king.redstonecg.init;

import net.acodonic_king.redstonecg.procedures.RedCuMeterMeasuringProcedure;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.acodonic_king.redstonecg.item.RedCuMixtureItem;
import net.acodonic_king.redstonecg.item.RedCuMeterItem;
import net.acodonic_king.redstonecg.item.RedCuIngotItem;
import net.acodonic_king.redstonecg.RedstonecgMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RedstonecgModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, RedstonecgMod.MODID);

	public static final RegistryObject<Item> RED_CU_METER = item("red_cu_meter", RedCuMeterItem::new);
	public static final RegistryObject<Item> RED_CU_MIXTURE = item("red_cu_mixture", RedCuMixtureItem::new);
	public static final RegistryObject<Item> RED_CU_INGOT = item("red_cu_ingot", RedCuIngotItem::new);
	public static final RegistryObject<Item> RED_CU_CRAFTER = block(RedstonecgModBlocks.RED_CU_CRAFTER);
	public static final RegistryObject<Item> SMOOTH_STONE_PLATE = block(RedstonecgModBlocks.SMOOTH_STONE_PLATE);

	//==== Floor ====
	//Digital
	public static final RegistryObject<Item> FLOOR_AND = block(RedstonecgModBlocks.FLOOR_AND);
	public static final RegistryObject<Item> FLOOR_OR = block(RedstonecgModBlocks.FLOOR_OR);
	public static final RegistryObject<Item> FLOOR_XOR = block(RedstonecgModBlocks.FLOOR_XOR);
	public static final RegistryObject<Item> FLOOR_NAND = block(RedstonecgModBlocks.FLOOR_NAND);
	public static final RegistryObject<Item> FLOOR_NOR = block(RedstonecgModBlocks.FLOOR_NOR);
	public static final RegistryObject<Item> FLOOR_NXOR = block(RedstonecgModBlocks.FLOOR_NXOR);
	public static final RegistryObject<Item> FLOOR_BUF = block(RedstonecgModBlocks.FLOOR_BUF);
	public static final RegistryObject<Item> FLOOR_NOT = block(RedstonecgModBlocks.FLOOR_NOT);
	public static final RegistryObject<Item> FLOOR_SR_LATCH = block(RedstonecgModBlocks.FLOOR_SR_LATCH);
	public static final RegistryObject<Item> FLOOR_D_LATCH = block(RedstonecgModBlocks.FLOOR_D_LATCH);
	public static final RegistryObject<Item> FLOOR_T_TRIGGER = block(RedstonecgModBlocks.FLOOR_T_TRIGGER);
	public static final RegistryObject<Item> FLOOR_JK_TRIGGER = block(RedstonecgModBlocks.FLOOR_JK_TRIGGER);
	//Analog
	public static final RegistryObject<Item> FLOOR_ANALOG_SOURCE = block(RedstonecgModBlocks.FLOOR_ANALOG_SOURCE);
	public static final RegistryObject<Item> FLOOR_ANALOG_BIAS = block(RedstonecgModBlocks.FLOOR_ANALOG_BIAS);
	public static final RegistryObject<Item> FLOOR_ANALOG_GAIN = block(RedstonecgModBlocks.FLOOR_ANALOG_GAIN);
	public static final RegistryObject<Item> FLOOR_ANALOG_PASS = block(RedstonecgModBlocks.FLOOR_ANALOG_PASS);
	public static final RegistryObject<Item> FLOOR_ANALOG_MEMORY = block(RedstonecgModBlocks.FLOOR_ANALOG_MEMORY);
	public static final RegistryObject<Item> FLOOR_ANALOG_SUBTRACTOR = block(RedstonecgModBlocks.FLOOR_ANALOG_SUBTRACTOR);
	public static final RegistryObject<Item> FLOOR_ANALOG_DIFFERENTIAL = block(RedstonecgModBlocks.FLOOR_ANALOG_DIFFERENTIAL);
	public static final RegistryObject<Item> FLOOR_ANALOG_MAX = block(RedstonecgModBlocks.FLOOR_ANALOG_MAX);
	public static final RegistryObject<Item> FLOOR_ANALOG_MIN = block(RedstonecgModBlocks.FLOOR_ANALOG_MIN);
	//Hybrid
	public static final RegistryObject<Item> FLOOR_COMPARATOR = block(RedstonecgModBlocks.FLOOR_COMPARATOR);
	public static final RegistryObject<Item> FLOOR_N_COMPARATOR = block(RedstonecgModBlocks.FLOOR_N_COMPARATOR);
	public static final RegistryObject<Item> FLOOR_FORWARD_PATH_SELECTOR = block(RedstonecgModBlocks.FLOOR_FORWARD_PATH_SELECTOR);
	public static final RegistryObject<Item> FLOOR_REVERSED_PATH_SELECTOR = block(RedstonecgModBlocks.FLOOR_REVERSED_PATH_SELECTOR);
	public static final RegistryObject<Item> FLOOR_ONE_WAY_THROUGH_GATE = block(RedstonecgModBlocks.FLOOR_ONE_WAY_THROUGH_GATE);
	public static final RegistryObject<Item> FLOOR_ONE_WAY_THROUGH_NOT_GATE = block(RedstonecgModBlocks.FLOOR_ONE_WAY_THROUGH_NOT_GATE);
	//Indicator
	public static final RegistryObject<Item> FLOOR_UNIVERSAL_INDICATOR = block(RedstonecgModBlocks.FLOOR_UNIVERSAL_INDICATOR);
	public static final RegistryObject<Item> FLOOR_SEVEN_SEGMENT_INDICATOR = block(RedstonecgModBlocks.FLOOR_SEVEN_SEGMENT_INDICATOR);
	public static final RegistryObject<Item> FLOOR_CLOCK_FILLING_INDICATOR = block(RedstonecgModBlocks.FLOOR_CLOCK_FILLING_INDICATOR);
	public static final RegistryObject<Item> FLOOR_ORB_INDICATOR = block(RedstonecgModBlocks.FLOOR_ORB_INDICATOR);
	//Parallel Digital
	public static final RegistryObject<Item> FLOOR_PARALLEL_AND = block(RedstonecgModBlocks.FLOOR_PARALLEL_AND);
	public static final RegistryObject<Item> FLOOR_PARALLEL_OR = block(RedstonecgModBlocks.FLOOR_PARALLEL_OR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_XOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_XOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_NAND = block(RedstonecgModBlocks.FLOOR_PARALLEL_NAND);
	public static final RegistryObject<Item> FLOOR_PARALLEL_NOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_NOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_NXOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_NXOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_SR_LATCH = block(RedstonecgModBlocks.FLOOR_PARALLEL_SR_LATCH);
	public static final RegistryObject<Item> FLOOR_PARALLEL_D_LATCH = block(RedstonecgModBlocks.FLOOR_PARALLEL_D_LATCH);
	//Parallel Analog
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_BIAS = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_BIAS);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_GAIN = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_GAIN);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_PASS = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_PASS);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_DIFFERENTIAL = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_DIFFERENTIAL);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_MEMORY = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_MEMORY);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_SUBTRACTOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_SUBTRACTOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_MAX = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_MAX);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ANALOG_MIN = block(RedstonecgModBlocks.FLOOR_PARALLEL_ANALOG_MIN);
	//Parallel Hybrid
	public static final RegistryObject<Item> FLOOR_PARALLEL_COMPARATOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_COMPARATOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_N_COMPARATOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_N_COMPARATOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ONE_WAY_PATH_SELECTOR = block(RedstonecgModBlocks.FLOOR_PARALLEL_ONE_WAY_PATH_SELECTOR);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ONE_WAY_THROUGH_GATE = block(RedstonecgModBlocks.FLOOR_PARALLEL_ONE_WAY_THROUGH_GATE);
	public static final RegistryObject<Item> FLOOR_PARALLEL_ONE_WAY_THROUGH_NOT_GATE = block(RedstonecgModBlocks.FLOOR_PARALLEL_ONE_WAY_THROUGH_NOT_GATE);
	//Wire
	public static final RegistryObject<Item> FLOOR_RED_CU_WIRE = block(RedstonecgModBlocks.FLOOR_RED_CU_WIRE);
	public static final RegistryObject<Item> FLOOR_REDSTONE_TO_RED_CU_CONVERTER = block(RedstonecgModBlocks.FLOOR_REDSTONE_TO_RED_CU_CONVERTER);
	public static final RegistryObject<Item> FLOOR_RED_CU_INTERSECTION = block(RedstonecgModBlocks.FLOOR_RED_CU_INTERSECTION);
	public static final RegistryObject<Item> FLOOR_PARALLEL_LINE_OUTPUT = block(RedstonecgModBlocks.FLOOR_PARALLEL_LINE_OUTPUT);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> RedstonecgModVersionRides.createBlockItem(block));
	}

	private static RegistryObject<Item> item(String reg, Supplier<? extends Item> sup) {
		return REGISTRY.register(reg, sup);
	}

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(RED_CU_METER.get(), new ResourceLocation("redstonecg:red_cu_meter_measuring"), (itemStackToRender, clientWorld, entity, itemEntityId) -> RedCuMeterMeasuringProcedure.GetTextureID(itemStackToRender));
		});
	}
}
