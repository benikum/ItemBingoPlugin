package de.benikum.itembingo;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bingo")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(player.getName());
                return true;
            }
        }
        return false;
    }
}
