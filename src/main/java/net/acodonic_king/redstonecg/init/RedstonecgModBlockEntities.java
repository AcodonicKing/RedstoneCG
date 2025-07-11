package net.acodonic_king.redstonecg.init;

import net.acodonic_king.redstonecg.block.entity.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.acodonic_king.redstonecg.RedstonecgMod;

public class RedstonecgModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RedstonecgMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ANALOG_SOURCE = register("analog_source", RedstonecgModBlocks.NORMAL_ANALOG_SOURCE, AnalogSourceBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> RED_CU_INTERSECTION = register("red_cu_intersection", RedstonecgModBlocks.REDCU_WIRE_INTERSECTION, RedCuWireIntersectionBlockEntity::new);
	public static final RegistryObject<BlockEntityType<RedCuWireTransitionBlockEntity>> REDCU_WIRE_TRANSITION = REGISTRY.register("redcu_wire_transition", () -> BlockEntityType.Builder.of(
			RedCuWireTransitionBlockEntity::new,
			RedstonecgModBlocks.REDCU_WIRE_TRANSITION.get()
	).build(null));
	public static final RegistryObject<BlockEntityType<?>> RED_CU_CRAFTER = register("red_cu_crafter", RedstonecgModBlocks.REDCU_CRAFTER, RedCuCrafterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<DefaultAnalogGateBlockEntity>> DEFAULT_ANALOG_GATE = REGISTRY.register("default_analog_gate", () -> BlockEntityType.Builder.of(
			DefaultAnalogGateBlockEntity::new,
			RedstonecgModBlocks.NORMAL_ANALOG_SOURCE.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_BIAS.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_GAIN.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_PASS.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_DIFFERENTIAL.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_MEMORY.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_SUBTRACTOR.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_MAX.get(),
			RedstonecgModBlocks.NORMAL_ANALOG_MIN.get(),
			RedstonecgModBlocks.NORMAL_COMPARATOR.get(),
			RedstonecgModBlocks.NORMAL_N_COMPARATOR.get(),
			RedstonecgModBlocks.NORMAL_FORWARD_PATH_SELECTOR.get(),
			RedstonecgModBlocks.NORMAL_REVERSED_PATH_SELECTOR.get(),
			RedstonecgModBlocks.NORMAL_ONE_WAY_THROUGH_GATE.get(),
			RedstonecgModBlocks.NORMAL_ONE_WAY_THROUGH_NOT_GATE.get(),

			RedstonecgModBlocks.PARALLEL_ANALOG_BIAS.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_DIFFERENTIAL.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_GAIN.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_MAX.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_MEMORY.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_MIN.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_PASS.get(),
			RedstonecgModBlocks.PARALLEL_ANALOG_SUBTRACTOR.get(),

			RedstonecgModBlocks.PARALLEL_ONE_WAY_THROUGH_GATE.get(),
			RedstonecgModBlocks.PARALLEL_ONE_WAY_THROUGH_NOT_GATE.get(),
			RedstonecgModBlocks.PARALLEL_ONE_WAY_PATH_SELECTOR.get(),

			RedstonecgModBlocks.PARALLEL_LINE_OUTPUT.get()
	).build(null));
	public static final RegistryObject<BlockEntityType<DefaultDigitalGateBlockEntity>> DEFAULT_DIGITAL_GATE = REGISTRY.register("default_digital_gate", () -> BlockEntityType.Builder.of(
			DefaultDigitalGateBlockEntity::new,
			RedstonecgModBlocks.NORMAL_AND.get(),
			RedstonecgModBlocks.NORMAL_OR.get(),
			RedstonecgModBlocks.NORMAL_XOR.get(),
			RedstonecgModBlocks.NORMAL_NAND.get(),
			RedstonecgModBlocks.NORMAL_NOR.get(),
			RedstonecgModBlocks.NORMAL_NXOR.get(),
			RedstonecgModBlocks.NORMAL_SR_LATCH.get(),
			RedstonecgModBlocks.NORMAL_D_LATCH.get(),
			RedstonecgModBlocks.NORMAL_JK_TRIGGER.get(),
			RedstonecgModBlocks.NORMAL_T_TRIGGER.get(),

			RedstonecgModBlocks.PARALLEL_AND.get(),
			RedstonecgModBlocks.PARALLEL_OR.get(),
			RedstonecgModBlocks.PARALLEL_XOR.get(),
			RedstonecgModBlocks.PARALLEL_NAND.get(),
			RedstonecgModBlocks.PARALLEL_NOR.get(),
			RedstonecgModBlocks.PARALLEL_NXOR.get(),
			RedstonecgModBlocks.PARALLEL_SR_LATCH.get(),
			RedstonecgModBlocks.PARALLEL_D_LATCH.get(),

			RedstonecgModBlocks.PARALLEL_COMPARATOR.get(),
			RedstonecgModBlocks.PARALLEL_N_COMPARATOR.get()
	).build(null));
	public static final RegistryObject<BlockEntityType<DefaultDigitalTriggerGateBlockEntity>> DEFAULT_DIGITAL_TRIGGER_GATE = REGISTRY.register("default_digital_trigger_gate", () -> BlockEntityType.Builder.of(
			DefaultDigitalTriggerGateBlockEntity::new,
			RedstonecgModBlocks.NORMAL_JK_TRIGGER.get(),
			RedstonecgModBlocks.NORMAL_T_TRIGGER.get()
	).build(null));
	public static final RegistryObject<BlockEntityType<DefaultAnalogIndicatorBlockEntity>> DEFAULT_ANALOG_INDICATOR =  REGISTRY.register("default_analog_indicator", () -> BlockEntityType.Builder.of(
			DefaultAnalogIndicatorBlockEntity::new,
			RedstonecgModBlocks.UNIVERSAL_INDICATOR.get(),
			RedstonecgModBlocks.CLOCK_FILLING_INDICATOR.get(),
			RedstonecgModBlocks.ORB_INDICATOR.get(),
			RedstonecgModBlocks.SEVEN_SEGMENT_INDICATOR.get()
	).build(null));
	public static final RegistryObject<BlockEntityType<RedCuWireBlockEntity>> RED_CU_WIRE = REGISTRY.register("red_cu_wire", () -> BlockEntityType.Builder.of(
			RedCuWireBlockEntity::new,
			RedstonecgModBlocks.REDCU_WIRE.get(),
			RedstonecgModBlocks.REDSTONE_TO_REDCU_CONVERTER.get()
	).build(null));

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
