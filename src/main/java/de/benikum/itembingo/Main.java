package de.benikum.itembingo;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    Game game;
    @Override
    public void onEnable() {
        this.game = new Game();
        getCommand("bingo").setExecutor(new BingoCommand(game));
        getCommand("bingo").setTabCompleter(new BingoTabCompleter());
        getServer().getPluginManager().registerEvents(new PlayerPickupItem(game), this);
    }
}