package net.acodonic_king.redstonecg.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.acodonic_king.redstonecg.RedstonecgMod.MODID;

public class RedstonecgModTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> TAB_REDSTONE_CG_CREATIVE_TAB = REGISTRY.register("tab",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.tab_redstone_cg_creative_tab")).icon(() -> new ItemStack(RedstonecgModBlocks.RED_CU_CRAFTER.get())).displayItems((parameters, tabData) -> {
                accept(tabData, RedstonecgModItems.RED_CU_METER);
                accept(tabData, RedstonecgModItems.RED_CU_CRAFTER);
                accept(tabData, RedstonecgModItems.RED_CU_METER);
                accept(tabData, RedstonecgModItems.RED_CU_MIXTURE);
                accept(tabData, RedstonecgModItems.RED_CU_INGOT);
                accept(tabData, RedstonecgModItems.RED_CU_CRAFTER);
                accept(tabData, RedstonecgModItems.SMOOTH_STONE_PLATE);
                //==== Floor ====
                //Digital
                accept(tabData, RedstonecgModItems.FLOOR_AND);
                accept(tabData, RedstonecgModItems.FLOOR_OR);
                accept(tabData, RedstonecgModItems.FLOOR_XOR);
                accept(tabData, RedstonecgModItems.FLOOR_NAND);
                accept(tabData, RedstonecgModItems.FLOOR_NOR);
                accept(tabData, RedstonecgModItems.FLOOR_NXOR);
                accept(tabData, RedstonecgModItems.FLOOR_BUF);
                accept(tabData, RedstonecgModItems.FLOOR_NOT);
                accept(tabData, RedstonecgModItems.FLOOR_SR_LATCH);
                accept(tabData, RedstonecgModItems.FLOOR_D_LATCH);
                accept(tabData, RedstonecgModItems.FLOOR_T_TRIGGER);
                accept(tabData, RedstonecgModItems.FLOOR_JK_TRIGGER);
                //Analog
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_SOURCE);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_BIAS);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_GAIN);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_PASS);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_MEMORY);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_SUBTRACTOR);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_DIFFERENTIAL);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_MAX);
                accept(tabData, RedstonecgModItems.FLOOR_ANALOG_MIN);
                //Hybrid
                accept(tabData, RedstonecgModItems.FLOOR_COMPARATOR);
                accept(tabData, RedstonecgModItems.FLOOR_N_COMPARATOR);
                accept(tabData, RedstonecgModItems.FLOOR_FORWARD_PATH_SELECTOR);
                accept(tabData, RedstonecgModItems.FLOOR_REVERSED_PATH_SELECTOR);
                accept(tabData, RedstonecgModItems.FLOOR_ONE_WAY_THROUGH_GATE);
                accept(tabData, RedstonecgModItems.FLOOR_ONE_WAY_THROUGH_NOT_GATE);
                //Indicator
                accept(tabData, RedstonecgModItems.FLOOR_UNIVERSAL_INDICATOR);
                accept(tabData, RedstonecgModItems.FLOOR_SEVEN_SEGMENT_INDICATOR);
                accept(tabData, RedstonecgModItems.FLOOR_CLOCK_FILLING_INDICATOR);
                accept(tabData, RedstonecgModItems.FLOOR_ORB_INDICATOR);
                //Parallel Digital
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_AND);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_OR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_XOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_NAND);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_NOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_NXOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_SR_LATCH);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_D_LATCH);
                //Parallel Analog
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_BIAS);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_GAIN);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_PASS);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_DIFFERENTIAL);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_MEMORY);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_SUBTRACTOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_MAX);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ANALOG_MIN);
                //Parallel Hybrid
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_COMPARATOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_N_COMPARATOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ONE_WAY_PATH_SELECTOR);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ONE_WAY_THROUGH_GATE);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_ONE_WAY_THROUGH_NOT_GATE);
                //Wire
                accept(tabData, RedstonecgModItems.FLOOR_RED_CU_WIRE);
                accept(tabData, RedstonecgModItems.FLOOR_REDSTONE_TO_RED_CU_CONVERTER);
                accept(tabData, RedstonecgModItems.FLOOR_RED_CU_INTERSECTION);
                accept(tabData, RedstonecgModItems.FLOOR_PARALLEL_LINE_OUTPUT);
            }).build());
    private static void accept(CreativeModeTab.Output tabData, RegistryObject<Item> obj){
        tabData.accept(obj.get().asItem());
    }
}