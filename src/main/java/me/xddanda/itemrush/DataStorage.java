package me.xddanda.itemrush;

import me.xddanda.itemrush.Enums.GameStage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataStorage {

    private static final List<UUID> players = new ArrayList<UUID>();
    private static final List<UUID> eliminated = new ArrayList<UUID>();
    private static final HashMap<UUID, Location> offline = new HashMap<>();
    private static final Location spawnLocation = new Location(Bukkit.getWorld("world"), -61.544, 109.0, 216.706);
    private static Location arenaLocation = null;

    public void joinPlayer(Player p){
        if (!GameManager.getStage().equals(GameStage.PLAYING)){
            if (!players.contains(p.getUniqueId())){
                players.add(p.getUniqueId());
                players.forEach(var -> Bukkit.getPlayer(var).sendMessage(p.getName() + " has joined the lobby! (" + players.size() + ")"));
                p.sendMessage("You have joined the lobby! (" + players.size() + ")");
            } else {
                p.sendMessage("Sorry, but you are already waiting for the game to start!");
            }
        } else {
            p.sendMessage("Sorry, but the has already started, you have to wait for it to end!");
        }
    }

    public static List<UUID> getPlayers() {
        return players;
    }

    public static Location getSpawnLocation() {
        return spawnLocation;
    }

    public static List<UUID> getEliminated() {
        return eliminated;
    }

    public static Location getArenaLocation() {
        return arenaLocation;
    }

    public static void setArenaLocation(Location arenaLocation) {
        DataStorage.arenaLocation = arenaLocation;
    }

    public static HashMap<UUID, Location> getOffline() {
        return offline;
    }
}
