package me.koziolek;

import me.koziolek.Commands.DeathSwapCommand;
import me.koziolek.Events.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Plugin startup logic
        register();
        Bukkit.getConsoleSender().sendMessage("Elo | WLACZONY");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("Nara | WYlACZONY");
    }

    private void register() {
        //dodawanie komend
        //Objects.requireNonNull(Bukkit.getPluginCommand("nazwa")).setExecutor(new nazwaKlasy());
        Objects.requireNonNull(Bukkit.getPluginCommand("deathswap")).setExecutor(new DeathSwapCommand(this));

        //dodawanie pluginow
        //getServer().getPluginManager().registerEvents(new nazwaKlasy(), this);

        //receptury
    }
}
