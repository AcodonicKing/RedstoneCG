package net.acodonic_king.redstonecg.recipe;

import net.acodonic_king.redstonecg.recipe.RedCuCrafterRecipe;
import net.acodonic_king.redstonecg.RedstonecgMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RedstonecgMod.MODID);

    public static final RegistryObject<RecipeSerializer<RedCuCrafterRecipe>> REDCU_CRAFTER_SERIALIZER = REGISTRY.register("redcu_crafting", () -> RedCuCrafterRecipe.Serializer.INSTANCE);
}