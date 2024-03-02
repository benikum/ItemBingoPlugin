package de.benikum.itembingo;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemGUIHandler implements Listener {
    Game game;
    public ItemGUIHandler(Game game) {
        this.game = game;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().title().toString().equals("Bingo Items")) {
            event.setCancelled(true);
            if (event.getWhoClicked().isOp() && player.getGameMode().equals(GameMode.CREATIVE)) {
                Material material = event.getCurrentItem().getType();
                game.searchItems.rerollItem(material);
                player.sendMessage(String.format("changed item %s", material.name()));
            }
        }
    }
}
