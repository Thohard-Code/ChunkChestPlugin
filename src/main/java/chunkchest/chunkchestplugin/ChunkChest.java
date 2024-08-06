package chunkchest.chunkchestplugin;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ChunkChest {
    private final Location location;
    private final Inventory inventory;
    private final Map<String, ItemStack> items;

    public ChunkChest(Location location, Inventory inventory) {
        this.location = location;
        this.inventory = inventory;
        this.items = new HashMap<>();
    }

    public Location getLocation() {
        return location;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(ItemStack item) {
        String itemKey = item.getType().toString();
        items.put(itemKey, item);
    }

    public ItemStack getItem(String itemKey) {
        return items.get(itemKey);
    }

    public void sellItems(Economy economy, Player player) {
        double totalValue = 0;
        for (ItemStack item : items.values()) {
            // Calculate item value, this could be a fixed value or fetched from a config
            double itemValue = 10.0; // Example value
            totalValue += itemValue * item.getAmount();
        }
        economy.depositPlayer(player, totalValue);
        items.clear();
        player.sendMessage("Sold items for " + totalValue + " $ ");
    }
}
