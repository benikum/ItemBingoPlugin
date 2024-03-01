package de.benikum.itembingo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BingoTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (command.getName().equalsIgnoreCase("bingo")) {
            if (args.length == 1) {
                completions.add("player");
                completions.add("start");
            } else if (args.length == 2 && Objects.equals(args[1], "player")) {
                completions.add("add");
                completions.add("remove");
                completions.add("clear");
            } // switch for more
        }
        return completions;
    }
}
