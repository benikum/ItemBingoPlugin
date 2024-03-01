package de.benikum.itembingo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BingoCommand implements CommandExecutor {
    Game game;
    public BingoCommand(Game game) {
        this.game = game;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        switch (args[1]) {
            case "player":
                Player argPlayer = Bukkit.getServer().getPlayer(args[3]);
                switch (args[2]) {
                    case "add":
                        game.addPlayer(argPlayer);
                        break;
                    case "remove":
                        game.removePlayer(argPlayer);
                        break;
                    case "list":
                        game.printPlayers(player);
                    case "reset":
                        game.resetPlayers();
                        break;
                    default:
                        return false;
                }
            case "start":
            default:
                // open inventory
        }
        return false;
    }

    }
}
