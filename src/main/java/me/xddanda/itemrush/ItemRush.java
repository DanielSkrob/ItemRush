package me.xddanda.itemrush;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.CommandManager;
import me.xddanda.itemrush.Commands.ForceStopCommand;
import me.xddanda.itemrush.Commands.JoinCommand;
import me.xddanda.itemrush.Commands.SpectateCommand;
import me.xddanda.itemrush.Commands.StartCommand;
import me.xddanda.itemrush.Events.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class ItemRush extends JavaPlugin {

    @Override
    public void onEnable() {
        GameManager manager = new GameManager();
        manager.init();

        try {
            CommandManager.createCoreCommand(this, "game", "minigame command", "/game", (sender, subCommandList) -> {
                        Player p = (Player) sender;
                        p.sendMessage(ColorTranslator.translateColorCodes("&e--------------------------------------"));
                        subCommandList.forEach(subCommand -> {
                            p.sendMessage(ColorTranslator.translateColorCodes("&a" + subCommand.getSyntax() + " - &f" + subCommand.getDescription()));
                        });
                        p.sendMessage(ColorTranslator.translateColorCodes("&e--------------------------------------"));
                    },
                    StartCommand.class,
                    JoinCommand.class,
                    ForceStopCommand.class,
                    SpectateCommand.class
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Arrays.asList(
                new BlockBreak(),
                new EntityDamage(),
                new EntitySpawn(),
                new PlayerDeath(),
                new PlayerJoin(),
                new PlayerLeave(),
                new PlayerRespawn()
        ).forEach(event -> getServer().getPluginManager().registerEvents(event, this));
    }
}
