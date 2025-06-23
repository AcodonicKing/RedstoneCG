package net.acodonic_king.redstonecg.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.acodonic_king.redstonecg.RedstonecgMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RedstonecgModTabs {
    public static CreativeModeTab TAB_REDSTONE_CG_CREATIVE_TAB;

    public static void load(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == TAB_REDSTONE_CG_CREATIVE_TAB){
            event.accept(RedstonecgModItems.RED_CU_METER);
            event.accept(RedstonecgModItems.RED_CU_MIXTURE);
            event.accept(RedstonecgModItems.RED_CU_INGOT);
            event.accept(RedstonecgModItems.RED_CU_CRAFTER);
            event.accept(RedstonecgModItems.SMOOTH_STONE_PLATE);
            //==== Floor ====
            //Digital
            event.accept(RedstonecgModItems.FLOOR_AND);
            event.accept(RedstonecgModItems.FLOOR_OR);
            event.accept(RedstonecgModItems.FLOOR_XOR);
            event.accept(RedstonecgModItems.FLOOR_NAND);
            event.accept(RedstonecgModItems.FLOOR_NOR);
            event.accept(RedstonecgModItems.FLOOR_NXOR);
            event.accept(RedstonecgModItems.FLOOR_BUF);
            event.accept(RedstonecgModItems.FLOOR_NOT);
            event.accept(RedstonecgModItems.FLOOR_SR_LATCH);
            event.accept(RedstonecgModItems.FLOOR_D_LATCH);
            event.accept(RedstonecgModItems.FLOOR_T_TRIGGER);
            event.accept(RedstonecgModItems.FLOOR_JK_TRIGGER);
            //Analog
            event.accept(RedstonecgModItems.FLOOR_ANALOG_SOURCE);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_BIAS);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_GAIN);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_PASS);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_MEMORY);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_SUBTRACTOR);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_DIFFERENTIAL);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_MAX);
            event.accept(RedstonecgModItems.FLOOR_ANALOG_MIN);
            //Hybrid
            event.accept(RedstonecgModItems.FLOOR_COMPARATOR);
            event.accept(RedstonecgModItems.FLOOR_N_COMPARATOR);
            event.accept(RedstonecgModItems.FLOOR_FORWARD_PATH_SELECTOR);
            event.accept(RedstonecgModItems.FLOOR_REVERSED_PATH_SELECTOR);
            event.accept(RedstonecgModItems.FLOOR_ONE_WAY_THROUGH_GATE);
            event.accept(RedstonecgModItems.FLOOR_ONE_WAY_THROUGH_NOT_GATE);
            //Indicator
            event.accept(RedstonecgModItems.FLOOR_UNIVERSAL_INDICATOR);
            event.accept(RedstonecgModItems.FLOOR_SEVEN_SEGMENT_INDICATOR);
            event.accept(RedstonecgModItems.FLOOR_CLOCK_FILLING_INDICATOR);
            event.accept(RedstonecgModItems.FLOOR_ORB_INDICATOR);
            //Parallel Digital
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_AND);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_OR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_XOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_NAND);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_NOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_NXOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_SR_LATCH);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_D_LATCH);
            //Parallel Analog
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_BIAS);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_GAIN);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_PASS);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_DIFFERENTIAL);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_MEMORY);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_SUBTRACTOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_MAX);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ANALOG_MIN);
            //Parallel Hybrid
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_COMPARATOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_N_COMPARATOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ONE_WAY_PATH_SELECTOR);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ONE_WAY_THROUGH_GATE);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_ONE_WAY_THROUGH_NOT_GATE);
            //Wire
            event.accept(RedstonecgModItems.FLOOR_RED_CU_WIRE);
            event.accept(RedstonecgModItems.FLOOR_REDSTONE_TO_RED_CU_CONVERTER);
            event.accept(RedstonecgModItems.FLOOR_RED_CU_INTERSECTION);
            event.accept(RedstonecgModItems.FLOOR_PARALLEL_LINE_OUTPUT);
        }
    }

    @SubscribeEvent
    public static void registerCreativeModTabs(CreativeModeTabEvent.Register event){
        TAB_REDSTONE_CG_CREATIVE_TAB = event.registerCreativeModeTab(new ResourceLocation(MODID, "tab_redstone_cg_creative_tab"),
                builder -> builder
                        .icon(() -> new ItemStack(RedstonecgModBlocks.RED_CU_CRAFTER.get()))
                        .title(Component.translatable("itemGroup.tab_redstone_cg_creative_tab"))
        );
    }
}