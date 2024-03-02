package de.benikum.itembingo;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Game {
    Set<Player> inGamePlayers;
    Map<Player, FoundItemController> playerItemMap;
    ItemSelector itemSelector = new ItemSelector(9);
    boolean gameActive = false;
    
    public void resetGame() {
        gameActive = false;
        inGamePlayers.clear();
        playerItemMap.clear();
        itemSelector.clear();
    }
    
    public void addPlayer(Player player) {
        inGamePlayers.add(player);
        Bukkit.broadcast(Component.text(String.format("Player %s has been added to the Game", player.getName())));
    }
    
    public void openBingoInventory(Player player) {
        if (!gameActive) return;
        Inventory inventory = Bukkit.createInventory(player, itemSelector.getItemSetSize(), Component.text("Bingo Items"));
        for (Material m : playerItemMap.get(player).getNotFoundItems(itemSelector)) {
            inventory.addItem(new ItemStack(m));
        }
        player.openInventory(inventory);
    }
    
    public void startGame() {
        gameActive = true;
        playerItemMap = new HashMap<>();
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            inGamePlayers.add(p);
            playerItemMap.put(p, new FoundItemController());
            p.sendMessage("§2Bingo game started");
            openBingoInventory(p);
        }
    }
    
    public void playerFoundItem(Player player, Material item) {
        if (!gameActive) return;
        if (inGamePlayers.contains(player) && itemSelector.getRandomMaterials().contains(item)) {
            playerItemMap.get(player).registerItem(item);
            player.sendMessage(String.format("§eFound Item: %s", item.name()));
            checkForWin(player);
        }
    }
    
    private void checkForWin(Player player) {
        if (!playerItemMap.get(player).getIfFoundAllItems(itemSelector)) return;
        resetGame();
        Bukkit.broadcast(Component.text(String.format("§ePlayer %s won the Game", player.getName())));
    }
}