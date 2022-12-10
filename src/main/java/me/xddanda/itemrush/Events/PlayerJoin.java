package me.xddanda.itemrush.Events;

import me.xddanda.itemrush.DataStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (DataStorage.getOffline().containsKey(p.getUniqueId())){
            p.teleport(DataStorage.getOffline().get(p.getUniqueId()));
            p.sendMessage("You have successfully rejoined the game");
            DataStorage.getPlayers().add(p.getUniqueId());
            DataStorage.getOffline().remove(p.getUniqueId());
        } else {
            p.teleport(DataStorage.getSpawnLocation());
            p.sendMessage("You have been teleported to the lobby");
        }
    }
}
