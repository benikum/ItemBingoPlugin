package de.benikum.itembingo;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> inGamePlayers = new ArrayList<>();

    public void addPlayer(Player player) {
        inGamePlayers.add(player);
    }
}
