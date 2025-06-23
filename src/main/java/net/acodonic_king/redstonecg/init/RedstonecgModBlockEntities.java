package net.acodonic_king.redstonecg.init;

import net.acodonic_king.redstonecg.block.entity.RedCuCrafterBlockEntity;
import net.acodonic_king.redstonecg.block.floor.entity.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.acodonic_king.redstonecg.RedstonecgMod;

public class RedstonecgModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RedstonecgMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ANALOG_SOURCE = register("analog_source", RedstonecgModBlocks.FLOOR_ANALOG_SOURCE, AnalogSourceBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> RED_CU_WIRE = register("red_cu_wire", RedstonecgModBlocks.FLOOR_RED_CU_WIRE, RedCuWireBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> REDSTONE_TO_RED_CU_CONVERTER = register("redstone_to_red_cu_converter", RedstonecgModBlocks.FLOOR_REDSTONE_TO_RED_CU_CONVERTER, RedstoneToRedCuConverterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> RED_CU_INTERSECTION = register("red_cu_intersection", RedstonecgModBlocks.FLOOR_RED_CU_INTERSECTION, RedCuIntersectionBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> RED_CU_CRAFTER = register("red_cu_crafter", RedstonecgModBlocks.RED_CU_CRAFTER, RedCuCrafterBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
