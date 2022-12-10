package me.xddanda.itemrush.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e){
        if (e.getLocation().getWorld().equals(Bukkit.getWorld("world"))){
            e.setCancelled(true);
        }
    }
}
