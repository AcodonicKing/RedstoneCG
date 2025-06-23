package net.acodonic_king.redstonecg.procedures;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.acodonic_king.redstonecg.init.RedstonecgModItems;

public class RedCuMeterMeasuringProcedure {
	public static float GetTextureID(ItemStack itemstack){
		if (itemstack.getItem() == RedstonecgModItems.RED_CU_METER.get()) {
			if (itemstack.getOrCreateTag().getBoolean("measuring")) {return 1;}
		}
		return 0;
	}
	public static boolean IsMeasuringBool(Entity entity) {
		if (entity == null)
			return false;
		ItemStack HandedItem = ItemStack.EMPTY;
		HandedItem = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
		if (!(HandedItem.getItem() == RedstonecgModItems.RED_CU_METER.get())) {
			HandedItem = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
		}
		if (HandedItem.getItem() == RedstonecgModItems.RED_CU_METER.get()) {
			return HandedItem.getOrCreateTag().getBoolean("measured");
		}
		return false;
	}
	public static String MeasureString(Entity entity) {
		if (entity == null)
			return "";
		ItemStack HandedItem = ItemStack.EMPTY;
		HandedItem = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
		if (!(HandedItem.getItem() == RedstonecgModItems.RED_CU_METER.get())) {
			HandedItem = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
		}
		if (HandedItem.getItem() == RedstonecgModItems.RED_CU_METER.get()) {
			return "Redstone Signal: " + HandedItem.getOrCreateTag().getDouble("measuring");
		}
		return "";
	}
}
