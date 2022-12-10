package me.xddanda.itemrush.Commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.xddanda.itemrush.DataStorage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class JoinCommand extends SubCommand {
    @Override
    public String getName() {
        return "join";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "you start waiting for the game to start";
    }

    @Override
    public String getSyntax() {
        return "/game join";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        DataStorage storage = new DataStorage();
        storage.joinPlayer(p);
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
