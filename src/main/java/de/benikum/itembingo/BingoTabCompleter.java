package de.benikum.itembingo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BingoTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            completions.add("player");
            completions.add("start");
            completions.add("items");
            completions.add("reset");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("player")) {
            completions.add("add");
            completions.add("add_all");
            completions.add("remove");
            completions.add("list");
        } else if (args.length == 3 && args[0].equalsIgnoreCase("player") && (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove"))) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                completions.add(p.getName());
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
