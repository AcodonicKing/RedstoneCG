package net.acodonic_king.redstonecg.init;

import net.acodonic_king.redstonecg.block.*;
import net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogBiasBlock;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.acodonic_king.redstonecg.RedstonecgMod;

public class RedstonecgModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, RedstonecgMod.MODID);

	public static final RegistryObject<Block> SMOOTH_STONE_PLATE = REGISTRY.register("smooth_stone_plate", SmoothStonePlateBlock::new);
	public static final RegistryObject<Block> RED_CU_CRAFTER = REGISTRY.register("red_cu_crafter", RedCuCrafterBlock::new);

	//==== Floor ====
	//Digital
	public static final RegistryObject<Block> FLOOR_AND = REGISTRY.register("floor_and", net.acodonic_king.redstonecg.block.floor.normal.digital.AndBlock::new);
	public static final RegistryObject<Block> FLOOR_OR = REGISTRY.register("floor_or", net.acodonic_king.redstonecg.block.floor.normal.digital.OrBlock::new);
	public static final RegistryObject<Block> FLOOR_XOR = REGISTRY.register("floor_xor", net.acodonic_king.redstonecg.block.floor.normal.digital.XorBlock::new);
	public static final RegistryObject<Block> FLOOR_BUF = REGISTRY.register("floor_buf", net.acodonic_king.redstonecg.block.floor.normal.digital.BufBlock::new);
	public static final RegistryObject<Block> FLOOR_NAND = REGISTRY.register("floor_nand", net.acodonic_king.redstonecg.block.floor.normal.digital.NandBlock::new);
	public static final RegistryObject<Block> FLOOR_NOR = REGISTRY.register("floor_nor", net.acodonic_king.redstonecg.block.floor.normal.digital.NorBlock::new);
	public static final RegistryObject<Block> FLOOR_NXOR = REGISTRY.register("floor_nxor", net.acodonic_king.redstonecg.block.floor.normal.digital.NxorBlock::new);
	public static final RegistryObject<Block> FLOOR_NOT = REGISTRY.register("floor_not", net.acodonic_king.redstonecg.block.floor.normal.digital.NotBlock::new);
	public static final RegistryObject<Block> FLOOR_SR_LATCH = REGISTRY.register("floor_sr_latch", net.acodonic_king.redstonecg.block.floor.normal.digital.SRLatchBlock::new);
	public static final RegistryObject<Block> FLOOR_D_LATCH = REGISTRY.register("floor_d_latch", net.acodonic_king.redstonecg.block.floor.normal.digital.DLatchBlock::new);
	public static final RegistryObject<Block> FLOOR_T_TRIGGER = REGISTRY.register("floor_t_trigger", net.acodonic_king.redstonecg.block.floor.normal.digital.TTriggerBlock::new);
	public static final RegistryObject<Block> FLOOR_JK_TRIGGER = REGISTRY.register("floor_jk_trigger", net.acodonic_king.redstonecg.block.floor.normal.digital.JKTriggerBlock::new);
	//Analog
	public static final RegistryObject<Block> FLOOR_ANALOG_SOURCE = REGISTRY.register("floor_analog_source", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogSourceBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_BIAS = REGISTRY.register("floor_analog_bias", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogBiasBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_GAIN = REGISTRY.register("floor_analog_gain", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogGainBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_PASS = REGISTRY.register("floor_analog_pass", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogPassBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_MEMORY = REGISTRY.register("floor_analog_memory", AnalogBiasBlock.AnalogMemoryBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_SUBTRACTOR = REGISTRY.register("floor_analog_subtractor", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogSubtractorBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_DIFFERENTIAL = REGISTRY.register("floor_analog_differential", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogDifferentialBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_MAX = REGISTRY.register("floor_analog_max", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogMaxBlock::new);
	public static final RegistryObject<Block> FLOOR_ANALOG_MIN = REGISTRY.register("floor_analog_min", net.acodonic_king.redstonecg.block.floor.normal.analog.AnalogMinBlock::new);
	//Hybrid
	public static final RegistryObject<Block> FLOOR_COMPARATOR = REGISTRY.register("floor_comparator", net.acodonic_king.redstonecg.block.floor.normal.hybrid.ComparatorBlock::new);
	public static final RegistryObject<Block> FLOOR_N_COMPARATOR = REGISTRY.register("floor_n_comparator", net.acodonic_king.redstonecg.block.floor.normal.hybrid.NComparatorBlock::new);
	public static final RegistryObject<Block> FLOOR_FORWARD_PATH_SELECTOR = REGISTRY.register("floor_forward_path_selector", net.acodonic_king.redstonecg.block.floor.normal.hybrid.ForwardPathSelectorBlock::new);
	public static final RegistryObject<Block> FLOOR_REVERSED_PATH_SELECTOR = REGISTRY.register("floor_reversed_path_selector", net.acodonic_king.redstonecg.block.floor.normal.hybrid.ReversedPathSelectorBlock::new);
	public static final RegistryObject<Block> FLOOR_ONE_WAY_THROUGH_GATE = REGISTRY.register("floor_one_way_through_gate", net.acodonic_king.redstonecg.block.floor.normal.hybrid.OneWayThroughGateBlock::new);
	public static final RegistryObject<Block> FLOOR_ONE_WAY_THROUGH_NOT_GATE = REGISTRY.register("floor_one_way_through_not_gate", net.acodonic_king.redstonecg.block.floor.normal.hybrid.OneWayOneWayThroughNotGateBlock::new);
	//Indicator
	public static final RegistryObject<Block> FLOOR_UNIVERSAL_INDICATOR = REGISTRY.register("floor_universal_indicator", net.acodonic_king.redstonecg.block.floor.normal.indicator.UniversalIndicatorBlock::new);
	public static final RegistryObject<Block> FLOOR_SEVEN_SEGMENT_INDICATOR = REGISTRY.register("floor_seven_segment_indicator", net.acodonic_king.redstonecg.block.floor.normal.indicator.SevenSegmentIndicatorBlock::new);
	public static final RegistryObject<Block> FLOOR_CLOCK_FILLING_INDICATOR = REGISTRY.register("floor_clock_filling_indicator", net.acodonic_king.redstonecg.block.floor.normal.indicator.ClockFillingIndicatorBlock::new);
	public static final RegistryObject<Block> FLOOR_ORB_INDICATOR = REGISTRY.register("floor_orb_indicator", net.acodonic_king.redstonecg.block.floor.normal.indicator.OrbIndicatorBlock::new);
	//Parallel Digital
	public static final RegistryObject<Block> FLOOR_PARALLEL_AND = REGISTRY.register("floor_parallel_and", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelAndBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_OR = REGISTRY.register("floor_parallel_or", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelOrBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_XOR = REGISTRY.register("floor_parallel_xor", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelXorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_NAND = REGISTRY.register("floor_parallel_nand", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelNandBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_NOR = REGISTRY.register("floor_parallel_nor", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelNorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_NXOR = REGISTRY.register("floor_parallel_nxor", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelNxorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_SR_LATCH = REGISTRY.register("floor_parallel_sr_latch", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelSRLatchBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_D_LATCH = REGISTRY.register("floor_parallel_d_latch", net.acodonic_king.redstonecg.block.floor.parallel.digital.ParallelDLatchBlock::new);
	//Parallel Analog
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_BIAS = REGISTRY.register("floor_parallel_analog_bias", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogBiasBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_GAIN = REGISTRY.register("floor_parallel_analog_gain", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogGainBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_PASS = REGISTRY.register("floor_parallel_analog_pass", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogPassBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_DIFFERENTIAL = REGISTRY.register("floor_parallel_analog_differential", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogDifferentialBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_MEMORY = REGISTRY.register("floor_parallel_analog_memory", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogMemoryBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_SUBTRACTOR = REGISTRY.register("floor_parallel_analog_subtractor", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogSubtractorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_MAX = REGISTRY.register("floor_parallel_analog_max", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogMaxBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ANALOG_MIN = REGISTRY.register("floor_parallel_analog_min", net.acodonic_king.redstonecg.block.floor.parallel.analog.ParallelAnalogMinBlock::new);
	//Parallel Hybrid
	public static final RegistryObject<Block> FLOOR_PARALLEL_COMPARATOR = REGISTRY.register("floor_parallel_comparator", net.acodonic_king.redstonecg.block.floor.parallel.hybrid.ParallelComparatorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_N_COMPARATOR = REGISTRY.register("floor_parallel_n_comparator", net.acodonic_king.redstonecg.block.floor.parallel.hybrid.ParallelNComparatorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ONE_WAY_PATH_SELECTOR = REGISTRY.register("floor_parallel_one_way_path_selector", net.acodonic_king.redstonecg.block.floor.parallel.hybrid.ParallelPathSelectorBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ONE_WAY_THROUGH_GATE = REGISTRY.register("floor_parallel_one_way_through_gate", net.acodonic_king.redstonecg.block.floor.parallel.hybrid.ParallelOneWayThroughGateBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_ONE_WAY_THROUGH_NOT_GATE = REGISTRY.register("floor_parallel_one_way_through_not_gate", net.acodonic_king.redstonecg.block.floor.parallel.hybrid.ParallelOneWayThroughNotGateBlock::new);
	//Wire
	public static final RegistryObject<Block> FLOOR_RED_CU_WIRE = REGISTRY.register("floor_red_cu_wire", net.acodonic_king.redstonecg.block.floor.normal.wire.RedCuWireBlock::new);
	public static final RegistryObject<Block> FLOOR_REDSTONE_TO_RED_CU_CONVERTER = REGISTRY.register("floor_redstone_to_red_cu_converter", net.acodonic_king.redstonecg.block.floor.normal.wire.RedstoneToRedCuConverterBlock::new);
	public static final RegistryObject<Block> FLOOR_RED_CU_INTERSECTION = REGISTRY.register("floor_red_cu_intersection", net.acodonic_king.redstonecg.block.floor.normal.wire.RedCuIntersectionBlock::new);
	public static final RegistryObject<Block> FLOOR_PARALLEL_LINE_OUTPUT = REGISTRY.register("floor_parallel_line_output", net.acodonic_king.redstonecg.block.floor.parallel.wire.ParallelLineOutputBlock::new);
}
