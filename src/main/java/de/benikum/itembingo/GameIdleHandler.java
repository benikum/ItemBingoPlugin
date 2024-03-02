package de.benikum.itembingo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameIdleHandler implements Listener {
    Game game;
    public GameIdleHandler(Game game) {
        this.game = game;
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!game.gameActive) {
            event.setCancelled(true);
        }
    }
}
