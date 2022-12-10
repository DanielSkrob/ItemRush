package me.xddanda.itemrush.Events;

import me.xddanda.itemrush.DataStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (DataStorage.getPlayers().contains(p.getUniqueId())){
            DataStorage.getPlayers().remove(p.getUniqueId());
            DataStorage.getOffline().put(p.getUniqueId(), p.getLocation());
        }

        if (DataStorage.getEliminated().contains(p.getUniqueId())){
            DataStorage.getEliminated().remove(p.getUniqueId());
        }
    }
}
