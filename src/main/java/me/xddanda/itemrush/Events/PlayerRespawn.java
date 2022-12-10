package me.xddanda.itemrush.Events;

import me.xddanda.itemrush.DataStorage;
import me.xddanda.itemrush.GameManager;
import me.xddanda.itemrush.ItemRush;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawn implements Listener {

    private static final Plugin plugin = ItemRush.getPlugin(ItemRush.class);

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if (DataStorage.getPlayers().contains(p.getUniqueId())) {
            GameManager.giveInvulnerability(p);

            new BukkitRunnable() {
                @Override
                public void run() {
                    GameManager.teleportPlayerToArena(p);
                }
            }.runTaskLater(plugin, 1L);
        } else {
            p.teleport(DataStorage.getSpawnLocation());
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.teleport(DataStorage.getSpawnLocation());
                }
            }.runTaskLater(plugin, 1L);
        }
    }
}
