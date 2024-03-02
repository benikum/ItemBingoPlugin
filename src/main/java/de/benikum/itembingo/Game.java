package de.benikum.itembingo;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
    Set<Player> inGamePlayers = new HashSet<>();
    Map<Player, FoundItemController> playerItemMap = new HashMap<>();
    ItemSelector searchItems = new ItemSelector(9);
    boolean gameActive = false;

    public void resetGame() {
        gameActive = false;
        inGamePlayers.clear();
        playerItemMap.clear();
        searchItems.clear();
    }
    public void addPlayer(Player player) {
        inGamePlayers.add(player);
    }
    public void addAllPlayers() {
        inGamePlayers.addAll(Bukkit.getServer().getOnlinePlayers());
    }
    public void removePlayer(Player player) {
        inGamePlayers.remove(player);
    }
    public void printPlayers(Player player) {
        if (inGamePlayers.isEmpty()) {
            player.sendMessage("No Bingo Players :(");
            return;
        }
        StringBuilder msg = new StringBuilder("Bingo Players: ");
        // build output string from inGamePlayers
        for (Player p : inGamePlayers) {
            msg.append(p.getName()).append(", ");
        }
        // remove last ", "
        player.sendMessage(msg.substring(0, msg.length() - 2));
    }
    
    public void openBingoInventory(Player player) {
        if (!gameActive) return;
        Inventory inventory = Bukkit.createInventory(player, searchItems.itemSetSize, Component.text("Bingo Items"));
        for (Material m : playerItemMap.get(player).getNotFoundItems(searchItems)) {
            inventory.addItem(new ItemStack(m));
        }
        player.openInventory(inventory);
    }
    
    public void startGame() {
        gameActive = true;
        playerItemMap = new HashMap<>();
        for (Player p : inGamePlayers) {
            playerItemMap.put(p, new FoundItemController());
            p.sendMessage("§lBingo game started");
            openBingoInventory(p);
        }
    }
    
    public void playerFoundItem(Player player, Material item) {
        if (!gameActive) return;
        if (inGamePlayers.contains(player) && searchItems.getSearchItems().contains(item)) {
            playerItemMap.get(player).registerItem(item);
            player.sendMessage(String.format("§eFound Item: %s", item.name()));
            checkForWin(player);
        }
    }
    
    private void checkForWin(Player player) {
        if (!playerItemMap.get(player).getIfFoundAllItems(searchItems)) return;
        resetGame();
        for (Player p : inGamePlayers) {
            p.sendMessage(String.format("§l§9Player %s won the Bingo Game", player));
        }
    }
}