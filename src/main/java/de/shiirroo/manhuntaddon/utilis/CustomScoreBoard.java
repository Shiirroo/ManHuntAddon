package de.shiirroo.manhuntaddon.utilis;

import de.shiirroo.manhuntaddon.ManHuntAddon;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class CustomScoreBoard {
    private final Scoreboard scoreboard;
    private Objective sidebarObjective;

    public CustomScoreBoard(String name){
        this(name, Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void addSidebar(String name){
        sidebarObjective = scoreboard.registerNewObjective("sidebar", "dummy", name);
        sidebarObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }


    public CustomScoreBoard(String name, Scoreboard scoreboard){
        this.scoreboard = scoreboard;
        Objective sidebar = scoreboard.getObjective("sidebar");

        if(sidebar != null){
            sidebar.unregister();
        }
        addSidebar(name);
    }

    public Objective getSidebarObjective() {
        return sidebarObjective;
    }

    public boolean checkSidebar(){
        return scoreboard.getObjective("sidebar") == null;
    }

    public void setSidebarScore(int slot, String content){
        if(slot < 0) throw new IllegalArgumentException("slot must be > 0");
        if(slot > 16) throw new IllegalArgumentException("slot must be > 16");

        Team team = getOrCreateTeam(content);
        String entry = getEntry(slot);

        if(content == null){
            scoreboard.resetScores(entry);
            return;
        }
        team.setPrefix(content);
        team.addEntry(entry);
        sidebarObjective.getScore(entry).setScore(slot);

    }


    private Team getOrCreateTeam(String name){
        Team team  = scoreboard.getTeam(name);
        if(team != null) return team;
        return scoreboard.registerNewTeam(name);

    }

    private String getEntry(int slot){
        return ChatColor.values()[slot].toString() + ChatColor.values()[slot+1];
    }

}
