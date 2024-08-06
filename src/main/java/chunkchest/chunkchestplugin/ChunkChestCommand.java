package chunkchest.chunkchestplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChunkChestCommand implements CommandExecutor {
    private final ChunkChestPlugin plugin;

    public ChunkChestCommand(ChunkChestPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0 && args[0].equalsIgnoreCase("sell")) {
                ChunkChest chunkChest = plugin.getChunkChest(player.getLocation().getChunk());
                if (chunkChest != null) {
                    chunkChest.sellItems(plugin.getEconomy(), player);
                } else {
                    player.sendMessage("No Chunk Chest found in this chunk!");
                }
                return true;
            }
            // Other command logic
        }
        return false;
    }
}
