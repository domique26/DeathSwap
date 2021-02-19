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
        getServer().getPluginManager().registerEvents(new Navigator(1), this);
        getServer().getPluginManager().registerEvents(new parkourRunEvent(), this);
        getServer().getPluginManager().registerEvents(new tntRunEvent(this), this);
        getServer().getPluginManager().registerEvents(new DuelNavi(1), this);
        getServer().getPluginManager().registerEvents(new duelFightEvent(), this);

        //receptury
        goldenAppleRec();


    }

    private void goldenAppleRec() {
        ItemStack result = new ItemStack(Material.GOLDEN_APPLE);
        NamespacedKey key = new NamespacedKey(this, "recipe1");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape(" G ", "GAG", " G ")
                .setIngredient('G', Material.GOLD_INGOT)
                .setIngredient('A', Material.APPLE);

        Bukkit.addRecipe(recipe);
    }
}
