package de.benikum.itembingo;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Game {
    Set<Player> inGamePlayers = new HashSet<>();
    Set<Material> searchItems;
    boolean gameActive = false;

    public void addPlayer(Player player) {
        inGamePlayers.add(player);
    }
    public void removePlayer(Player player) {
        inGamePlayers.remove(player);
    }
    public void resetPlayers() {
        inGamePlayers.clear();
    }
    public void printPlayers(Player player) {
        StringBuilder msg = new StringBuilder("Bingo Players: ");
        // build output String from inGamePlayers
        for (Player p : inGamePlayers) {
            msg.append(p.getName()).append(", ");
        }
        // remove last ", "
        player.sendMessage(msg.substring(0, msg.length() - 2));
    }
    public void startGame() {
        gameActive = true;
        searchItems = ItemSelector.getRandomMaterialSet(9);
        for (Player p : inGamePlayers) p.sendMessage("Game started");
    }
    public void playerFoundItem(Player player, ItemStack itemStack) {
        if (!gameActive) return;
        Material item = itemStack.getType();
        if (inGamePlayers.contains(player) && searchItems.contains(item)) {
            // logik
        }
    }
}