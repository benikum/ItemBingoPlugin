package de.benikum.itembingo;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerPickupItem implements Listener {
    Game game;
    public PlayerPickupItem(Game game) {
        this.game = game;
    }
    @EventHandler
    public static void onPickup(EntityPickupItemEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = event.getItem().getItemStack();
        // code
    }
}
