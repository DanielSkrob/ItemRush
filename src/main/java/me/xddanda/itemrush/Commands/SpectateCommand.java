package me.xddanda.itemrush.Commands;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import me.xddanda.itemrush.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpectateCommand extends SubCommand {
    @Override
    public String getName() {
        return "spectate";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "allows you to spectate player when you have already lost";
    }

    @Override
    public String getSyntax() {
        return "/game spectate <playerName>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (DataStorage.getEliminated().contains(p.getUniqueId())){
            if (args.length >= 2){
                if (Bukkit.getPlayer(args[1]) != null){
                    Player target = Bukkit.getPlayer(args[1]);
                    if (DataStorage.getPlayers().contains(target.getUniqueId())){
                        p.teleport(target.getLocation());
                        p.sendMessage("You are now spectating: " + target.getName());
                        target.sendMessage(p.getName() + " is spectating you!");
                    } else {
                        p.sendMessage("Sorry, but this player isn't playing anymore");
                    }
                }
            } else {
                p.sendMessage(ColorTranslator.translateColorCodes("&c&l| &cSorry, but you do not have enough arguments to execute this command"));
            }
        } else {
            p.sendMessage("Sorry, but you are not eliminated!");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2){
            List<String> completer = new ArrayList<>();
            DataStorage.getPlayers().forEach(var -> completer.add(Bukkit.getPlayer(var).getName()));
            return completer;
        }
        return null;
   }
}
