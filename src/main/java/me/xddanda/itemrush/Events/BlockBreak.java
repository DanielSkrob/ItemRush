package me.xddanda.itemrush.Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (e.getBlock().getLocation().getWorld().equals(Bukkit.getWorld("world")) && e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            e.setCancelled(true);
        }
    }
}
