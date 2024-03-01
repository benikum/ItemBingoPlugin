package de.benikum.itembingo;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Game {
    Set<Player> inGamePlayers = new HashSet<>();
    Set<Material> searchItems;
    boolean active = false;

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
        if (inGamePlayers.isEmpty()) {
            player.sendMessage("No Bingo Players :(");
            return;
        }
        StringBuilder msg = new StringBuilder("Bingo Players: ");
        // build output String from inGamePlayers
        for (Player p : inGamePlayers) {
            msg.append(p.getName()).append(", ");
        }
        // remove last ", "
        player.sendMessage(msg.substring(0, msg.length() - 2));
    }
    public void startGame() {
        active = true;
        searchItems = ItemSelector.getRandomMaterialSet(9);
        for (Player p : inGamePlayers) p.sendMessage("Game started");
    }
    public void playerFoundItem(Player player, ItemStack itemStack) {
        if (!active) return;
        Material item = itemStack.getType();
        if (inGamePlayers.contains(player) && searchItems.contains(item)) {
            // logik
        }
    }
}