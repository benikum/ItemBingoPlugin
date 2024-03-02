package de.benikum.itembingo;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PlayerPickupItem implements Listener {
    Game game;
    public PlayerPickupItem(Game game) {
        this.game = game;
    }
    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Material item = event.getItem().getItemStack().getType();
        game.playerFoundItem((Player) entity, item);
    }
}
