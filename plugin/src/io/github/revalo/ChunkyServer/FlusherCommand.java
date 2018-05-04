package io.github.revalo.ChunkyServer;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;

public class FlusherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args[0] == "flush") {
                Chunk chunk = player.getLocation().getChunk();
                ((CraftWorld) player.getWorld()).getHandle().getChunkProviderServer().saveChunk(((CraftChunk) chunk).getHandle(), false);
            } else if(args[0] == "load") {
                player.getLocation().getChunk().unload(false, false);
                player.getLocation().getChunk().load(false);
            }
        }

        return true;
    }
}