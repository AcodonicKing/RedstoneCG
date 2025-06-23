package net.acodonic_king.redstonecg.init;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.acodonic_king.redstonecg.RedstonecgMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = RedstonecgMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RedstonecgModMissingMappingsHandler {
    private static final Map<String, ResourceLocation> REMAP_TABLE = new HashMap<>();
    static {
        try (InputStream stream = RedstonecgModMissingMappingsHandler.class.getClassLoader().getResourceAsStream("data/redstonecg/remap/block_remap.json")) {
            if (stream != null) {
                JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    REMAP_TABLE.put(entry.getKey(), new ResourceLocation(RedstonecgMod.MODID, entry.getValue().getAsString()));
                }
                System.out.println("[Remap] Loaded " + REMAP_TABLE.size() + " block remaps.");
            } else {
                System.err.println("[Remap] block_remap.json not found in resources.");
            }
        } catch (Exception e) {
            System.err.println("[Remap] Failed to load block_remap.json: " + e.getMessage());
        }
    }
    @SubscribeEvent
    public static void onMissingMappings(MissingMappingsEvent event) {
        event.getMappings(ForgeRegistries.BLOCKS.getRegistryKey(), RedstonecgMod.MODID)
                .forEach(mapping -> {
                    String oldPath = mapping.getKey().getPath();
                    ResourceLocation newId = REMAP_TABLE.get(oldPath);

                    if (newId != null) {
                        Block newBlock = ForgeRegistries.BLOCKS.getValue(newId);
                        if (newBlock != null) {
                            mapping.remap(newBlock);
                            System.out.println("[Remap] Remapped block: " + oldPath + " -> " + newId);
                        } else {
                            System.err.println("[Remap] Target block not found: " + newId);
                        }
                    }
                });
    }
}
