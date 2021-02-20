package me.koziolek.Commands;

import me.koziolek.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class DeathSwapCommand implements CommandExecutor {

    Main plugin;
    boolean gameIsEnded = true;
    int r1;

    public DeathSwapCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))
            return true;

        List<Player> players = new ArrayList<>(2);;
        Player p1 = (Player) sender;
        players.add(p1);
        Player p2 = Bukkit.getPlayer(args[0]);

        String cooldown = args[1];
        if(cooldown == null) {
            cooldown = "300";
        }

        if(p2 == null) {
            p1.sendMessage("Unknown Player!");
            return true;
        } else {
            players.add(p2);
            gameIsEnded = false;
            Bukkit.broadcastMessage("THE GAME HAS STARTED!");
        }

        for (Player player:players) {
            player.setHealth(20);
            player.setFoodLevel(20);
        }

        int finalCountdown = Integer.parseInt(cooldown);
        r1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            int countdown = finalCountdown;

            @Override
            public void run()
            {
                if(p1.getHealth() <= 0 || !(p1.isOnline())) {
                    Bukkit.broadcastMessage("§3"+p2.getDisplayName() + " won the game!");
                    Bukkit.getScheduler().cancelTask(r1);
                }
                else if(p2.getHealth() <= 0 || !(p2.isOnline())) {
                    Bukkit.broadcastMessage("§3"+p1.getDisplayName() + " won the game!");
                    Bukkit.getScheduler().cancelTask(r1);
                }


                //Bukkit.broadcastMessage("Swap in " + String.valueOf(countdown) + "sec");
                countdown--;


                if(countdown == 60) {
                    Bukkit.broadcastMessage("§cSwap in 40sec");
                }
                if(countdown == 30) {
                    Bukkit.broadcastMessage("§cSwap in 30sec");
                }
                if(countdown == 5) {
                    Bukkit.broadcastMessage("§4SWAP IN 5");
                }
                if(countdown == 4) {
                    Bukkit.broadcastMessage("§4SWAP IN 4");
                }
                if(countdown == 3) {
                    Bukkit.broadcastMessage("§4SWAP IN 3");
                }
                if(countdown == 2) {
                    Bukkit.broadcastMessage("§4SWAP IN 2");
                }
                if(countdown == 1) {
                    Bukkit.broadcastMessage("§4SWAP IN 1");
                }
                if(countdown == 0)
                {
                    Location p1Pos = p1.getLocation();
                    Location p2Pos = p2.getLocation();
                    p1.teleport(p2Pos);
                    p2.teleport(p1Pos);
                    Bukkit.broadcastMessage("§4S W A P!!");
                    countdown = finalCountdown;
                }
            }

        }, 0L, 20L);

        return true;
    }
}
