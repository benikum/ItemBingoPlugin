package de.benikum.itembingo;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Game bingoGame = new Game();
        getCommand("bingo").setExecutor(new Command());
        // getServer().getPluginManager().registerEvents();
    }
}
