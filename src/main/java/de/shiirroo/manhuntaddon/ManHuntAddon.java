package de.shiirroo.manhuntaddon;

import de.shiirroo.manhuntaddon.command.CommandManager;
import de.shiirroo.manhuntaddon.events.onPlayerJoin;
import de.shiirroo.manhuntaddon.utilis.CustomPlayerScoreBoard;
import de.shiirroo.manhuntaddon.utilis.CustomScoreBoard;
import de.shiirroo.manhuntaddon.utilis.repeatingtask.GameTimes;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;
import java.util.*;

public final class ManHuntAddon extends JavaPlugin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static Plugin plugin;

    private static final CustomPlayerScoreBoard customPlayerScoreBoard = new CustomPlayerScoreBoard(getprefix());;


    public static String getprefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Man" + ChatColor.RED + "Hunt"+ChatColor.AQUA+"Addon" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
    }


    public static Plugin getPlugin() {
        return plugin;
    }

    public static CustomPlayerScoreBoard getCustomPlayerScoreBoar() {
        return customPlayerScoreBoard;
    }

    @Override
    public void onEnable() {
        plugin = this;
        setdefaultScoreBoard();
        for (Player player: Bukkit.getOnlinePlayers()) {
            //customPlayerScoreBoard.addPlayerToCustomScoreBoard(player);
        }


        getServer().getPluginManager().registerEvents(new onPlayerJoin(customPlayerScoreBoard), this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new GameTimes(), 1, 1);

        Objects.requireNonNull(getCommand("ManHuntAddon")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("ManHuntAddon")).setTabCompleter(new CommandManager());
        Bukkit.getLogger().info(getprefix() + "plugin started.");

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(getprefix() + "plugin stopped.");
    }



    public static void setdefaultScoreBoard(){
        for(int i = 9; i != 0; i--){
            customPlayerScoreBoard.setDefaultSidebarScore(i, " ");
        }
    }
}
