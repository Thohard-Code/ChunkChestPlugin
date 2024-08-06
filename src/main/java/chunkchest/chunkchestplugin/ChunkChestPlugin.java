package chunkchest.chunkchestplugin;

import org.bukkit.Chunk;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ChunkChestPlugin extends JavaPlugin {
    private final Map<Chunk, ChunkChest> chunkChests = new HashMap<>();
    private Economy economy;

    @Override
    public void onEnable() {
        // Initialize Essentials Economy
        if (!setupEconomy()) {
            getLogger().severe("Essentials Economy not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register events and commands
        getServer().getPluginManager().registerEvents(new ChunkChestListener(this), this);
        getCommand("chunkchest").setExecutor(new ChunkChestCommand(this));
        getLogger().info("ChunkChest Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ChunkChest Plugin Disabled!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Essentials") == null) {
            return false;
        }
        economy = (Economy) getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        return economy != null;
    }

    public Economy getEconomy() {
        return economy;
    }

    public void addChunkChest(Chunk chunk, ChunkChest chunkChest) {
        chunkChests.put(chunk, chunkChest);
    }

    public ChunkChest getChunkChest(Chunk chunk) {
        return chunkChests.get(chunk);
    }
}
