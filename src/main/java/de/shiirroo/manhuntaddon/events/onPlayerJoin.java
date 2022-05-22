package de.shiirroo.manhuntaddon.events;


import de.shiirroo.manhuntaddon.utilis.CustomPlayerScoreBoard;
import de.shiirroo.manhuntaddon.utilis.CustomScoreBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {

    private final CustomPlayerScoreBoard customPlayerScoreBoard;

    public onPlayerJoin(CustomPlayerScoreBoard customPlayerScoreBoard){
        this.customPlayerScoreBoard = customPlayerScoreBoard;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
    }



}
