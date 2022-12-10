package me.xddanda.itemrush.Commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.xddanda.itemrush.GameManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StartCommand extends SubCommand {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "starts the game";
    }

    @Override
    public String getSyntax() {
        return "/game start";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("itemrush.start")){
            GameManager manager = new GameManager();
            manager.gameStart();
        } else {
            p.sendMessage("Sorry, but you do not have enough permissions!");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
