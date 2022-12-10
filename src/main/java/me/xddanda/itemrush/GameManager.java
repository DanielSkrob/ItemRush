package me.xddanda.itemrush;

import me.xddanda.itemrush.Enums.GameStage;
import me.xddanda.itemrush.Models.ItemStorage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class GameManager {

    private static final Plugin plugin = ItemRush.getPlugin(ItemRush.class);

    private static GameStage stage = null;
    private static Boolean terminator = null;

    public void init(){
        setStage(GameStage.WAITING);
        System.out.println("The game has been initialized");
        setTerminator(false);
    }

    public void gameStart(){
        int minPlayers = 2;
        if (stage.equals(GameStage.WAITING)){
            setStage(GameStage.STARTING);
            new BukkitRunnable() {
                private int timer = 5;
                @Override
                public void run() {
                    if (DataStorage.getPlayers().size() < minPlayers){
                        messageActivePlayers("The game has been terminated due to not having enough players in it!");
                        this.cancel();
                    }

                    messageActivePlayers("The game stars in: " + timer + " seconds");

                    if (timer == 0){
                        gameActive();
                        this.cancel();
                    }
                    timer--;
                }
            }.runTaskTimer(plugin, 0L, 20L);
        } else {
            System.out.println("someone tried to start a game of item rush");
        }
    }

    private void gameActive() {
        Random random = new Random();
        int x = random.nextInt(100000);
        int y = 150;
        int z = random.nextInt(100000);

        DataStorage.setArenaLocation(new Location(Bukkit.getWorld("arena"), x, y, z));

        messageActivePlayers("The game has started!");
        setStage(GameStage.PLAYING);
        DataStorage.getPlayers().forEach(var -> {
            Player target = Bukkit.getPlayer(var);
            giveInvulnerability(target);
            givePlayerKit(target);
            teleportPlayerToArena(target);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "advancement revoke " + target.getName() + " everything");
        });

        new BukkitRunnable() {
            private final int defaultTime = 300;
            private int round = 1;
            private int timer = defaultTime;
            final ItemStorage storage = new ItemStorage();

            @Override
            public void run() {
                if (timer == defaultTime){
                    storage.generate(round);
                    messageActivePlayers("You have " + defaultTime + " seconds to find: " + storage.getCurrentItem().getType() + " | round: " + round);
                }

                if (((DataStorage.getPlayers().size()) + (DataStorage.getEliminated().size())) < 2){
                    //TODO: upgrade and add some logic
                    messageActivePlayers("Game terminated due to not having enough players!");
                    cleanUp();
                    this.cancel();
                }

                if (getTerminator()){
                    messageActivePlayers("The game has been stopped!");
                    cleanUp();
                    this.cancel();
                }

                if (Arrays.asList(30, 60, 120, 180, 240, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0).contains(timer)){
                    messageActivePlayers("Time left until next round: " + timer + " seconds!");
                }

                if (timer == 0) {
                    List<UUID> eliminatedPlayers = new ArrayList<>();
                    DataStorage.getPlayers().forEach(var -> {
                        Player target = Bukkit.getPlayer(var);
                        boolean checker = false;
                        for (ItemStack item : target.getInventory().getContents()){
                            if (item != null) {
                                if (item.getType().equals(ItemStorage.getCurrentItem().getType())) {
                                    checker = true;
                                }
                            }
                        }

                        if (checker){
                            target.sendMessage("You have completed this round!");
                        } else {
                            eliminatedPlayers.add(var);
                        }
                    });

                    if (eliminatedPlayers.equals(DataStorage.getPlayers())){
                        messageActivePlayers("No one has obtained the item. The game continues!");
                    } else {
                        eliminatedPlayers.forEach(var -> {
                            DataStorage.getPlayers().remove(var);
                            DataStorage.getEliminated().add(var);
                            Player p = Bukkit.getPlayer(var);
                            p.teleport(Bukkit.getPlayer(DataStorage.getPlayers().get(0)).getLocation());
                            p.setGameMode(GameMode.SPECTATOR);
                            p.getInventory().clear();
                        });
                    }

                    switch (DataStorage.getPlayers().size()){
                        case 1 -> {
                            Bukkit.broadcastMessage(Bukkit.getPlayer(DataStorage.getPlayers().get(0)).getName() + " has won the Item Rush");
                            cleanUp();
                            this.cancel();
                        }

                        case 0 -> {
                            Bukkit.broadcastMessage("No one has won the Item Rush!");
                            cleanUp();
                            this.cancel();
                        }
                    }

                    round++;
                    timer = defaultTime + 1;
                    storage.generate(round);
                    DataStorage.getOffline().clear();
                }
                timer--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public void cleanUp() {
        List<UUID> players = new ArrayList<>();
        players.addAll(DataStorage.getPlayers());
        players.addAll(DataStorage.getEliminated());

        players.forEach(var -> {
            Player target = Bukkit.getPlayer(var);
            target.setGameMode(GameMode.SURVIVAL);
            target.getInventory().clear();
            target.setHealth(20);
            target.setFoodLevel(20);
            target.teleport(DataStorage.getSpawnLocation());
        });

        messageActivePlayers("The game has ended!");
        DataStorage.getPlayers().clear();
        DataStorage.getEliminated().clear();
        setTerminator(false);
        init();
    }

    public static GameStage getStage() {
        return stage;
    }

    public static void setStage(GameStage stage) {
        GameManager.stage = stage;
    }

    public static void messageActivePlayers(String message) {
        DataStorage.getPlayers().forEach(var -> Bukkit.getPlayer(var).sendMessage(message));
        DataStorage.getEliminated().forEach(var -> Bukkit.getPlayer(var).sendMessage(message));
    }

    public static Boolean getTerminator() {
        return terminator;
    }

    public static void setTerminator(Boolean terminator) {
        GameManager.terminator = terminator;
    }

    public static void givePlayerKit(Player target){
        target.setHealth(20);
        target.setSaturation(20);
        target.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
    }

    public static void giveInvulnerability(Player p){
        p.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.setInvulnerable(false);
                p.sendMessage("Skončila ti nesmrtelnost!");
            }
        }.runTaskLater(plugin, 400);
    }

    public static void teleportPlayerToArena(Player p){
        p.teleport(DataStorage.getArenaLocation());
    }
}
