package de.shiirroo.manhuntaddon.utilis;

import de.shiirroo.manhuntaddon.ManHuntAddon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class CustomPlayerScoreBoard{
    private CustomScoreBoard CustomScoreBoard = new CustomScoreBoard(ManHuntAddon.getprefix());
    private final Map<Integer, String> defaults = new HashMap<>();
    private final String name;


    public CustomPlayerScoreBoard(String name) {
        this.name = name;
    }

    public void setSidebarScore(int slot, String content){
        if(slot < 0) throw new IllegalArgumentException("slot must be > 0");
        if(slot > 16) throw new IllegalArgumentException("slot must be > 16");
        CustomScoreBoard.setSidebarScore(slot, content);
    }

    public void setDefaultSidebarScore(int slot, String content){
        if(slot < 0) throw new IllegalArgumentException("slot must be > 0");
        if(slot > 16) throw new IllegalArgumentException("slot must be > 16");
        setSidebarScore(slot, content);
        if(content == null) defaults.remove(slot);
        else defaults.put(slot, content);

    }

    public void addPlayerToCustomScoreBoard(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        player.setScoreboard(scoreboard);
    }

    public void removePlayersFromCustomScoreBoard(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective sidebar = scoreboard.getObjective("sidebar");
        if(sidebar != null){
            sidebar.unregister();
        }
    }

    public CustomScoreBoard getCustomScoreBoard() {
        return CustomScoreBoard;
    }

    public Map<Integer, String> getDefaults() {
        return defaults;
    }

    public String  getName() {
        return name;
    }
}
