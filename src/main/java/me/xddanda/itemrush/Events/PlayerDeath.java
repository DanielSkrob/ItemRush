package me.xddanda.itemrush.Events;

import me.xddanda.itemrush.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        GameManager.teleportPlayerToArena(p);
        GameManager.giveInvulnerability(p);
    }
}
