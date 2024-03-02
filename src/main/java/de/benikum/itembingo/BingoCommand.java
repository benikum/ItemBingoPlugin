package de.benikum.itembingo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BingoCommand implements CommandExecutor {
    Game game;
    
    public BingoCommand(Game game) {
        this.game = game;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (args.length >= 1) {
            // /bingo ___
            if (args[0].equalsIgnoreCase("reset")) {
                // /bingo reset
                game.resetGame();
                player.sendMessage("Game is reset");
                return true;
            } else if (args[0].equalsIgnoreCase("player")) {
                // /bingo player
                if (args.length >= 2) {
                    // /bingo player ___
                    if (args[1].equalsIgnoreCase("add_all")) {
                        // /bingo player add_all
                        if (game.gameActive) return false;
                        game.addAllPlayers();
                        player.sendMessage("§aAll online Players were added to the Bingo game!");
                        return true;
                    } else if (args[1].equalsIgnoreCase("list")) {
                        // /bingo player list
                        game.printPlayers(player);
                        return true;
                    } else if (args.length >= 3) {
                        // /bingo player xxx ___
                        String argPlayerName = args[2];
                        boolean isPlayerOnline = false;
                        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                            if (onlinePlayer.getName().equals(argPlayerName)) {
                                isPlayerOnline = true;
                                break;
                            }
                        }
                        
                        Player argPlayer;
                        if (isPlayerOnline) {
                            argPlayer = Bukkit.getServer().getPlayer(argPlayerName);
                        } else {
                            argPlayer = player;
                        }
                        
                        if (args[1].equalsIgnoreCase("add")) {
                            if (game.gameActive) return false;
                            game.addPlayer(argPlayer);
                            player.sendMessage(String.format("§aPlayer %s was added to the Bingo game!", argPlayer.getName()));
                            return true;
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (game.gameActive) return false;
                            game.removePlayer(argPlayer);
                            player.sendMessage(String.format("§cPlayer %s is no longer in the Bingo game!", argPlayer.getName()));
                            return true;
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("start")) {
                // /bingo start
                game.startGame();
                return true;
            } else if (args[0].equalsIgnoreCase("items")) {
                // /bingo items
                game.openBingoInventory(player);
                return true;
            } // more commands
        }
        return false;
    }
}