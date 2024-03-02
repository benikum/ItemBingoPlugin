package de.benikum.itembingo;

import org.bukkit.GameMode;
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
            if (event.getWhoClicked().isOp() && player.getGameMode().equals(GameMode.CREATIVE)) {
                game.searchItems.changeItem(event.getCurrentItem().getType());
            }
            event.setCancelled(true);
        }
    }
}