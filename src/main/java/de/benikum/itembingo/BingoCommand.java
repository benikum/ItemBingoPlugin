package de.benikum.itembingo;

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
            switch (args[0].toLowerCase()) {
                case "start":
                    // /bingo start
                    if (player.isOp()) {
                        game.startGame();
                    } else {
                        player.sendMessage("You don´t have permission to start the game");
                    }
                    return true;
                case "reset":
                    // /bingo reset
                    if (player.isOp()) {
                        game.resetGame();
                    } else {
                        player.sendMessage("You don´t have permission to reset the game");
                    }
                    return true;
                case "items":
                    // /bingo items
                    if (game.gameActive) {
                        game.openBingoInventory(player);
                    } else {
                        player.sendMessage("Bingo game is currently not active");
                    }
                    return true;
                case "add_player":
                    // /bingo add_player
                    game.addPlayer(player);
                    game.openBingoInventory(player);
                    return true;
            }
        }
        showUsage(player);
        return false;
    }
    private void showUsage(Player player) {
        player.sendMessage("§l§e_____ Bingo Game Commands _____");
        if (player.isOp()) {
            player.sendMessage("§a/bingo start §r- start the game");
            player.sendMessage("§c/bingo reset §r- reset the game (all progress will be lost)");
        }
        player.sendMessage("§9/bingo items §r- see items you need to find");
    }
}