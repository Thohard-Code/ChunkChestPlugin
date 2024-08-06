package chunkchest.chunkchestplugin;


import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class ChunkChestListener implements Listener {
    private final ChunkChestPlugin plugin;

    public ChunkChestListener(ChunkChestPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        // Assuming chunk chest is stored in a map
        ChunkChest chunkChest = plugin.getChunkChest(event.getLocation().getChunk());
        if (chunkChest != null) {
            ItemStack item = event.getEntity().getItemStack();
            chunkChest.addItem(item);
            event.getEntity().remove();
            plugin.getLogger().info("Item collected in chunk chest!");
        }
    }
}
