package de.shiirroo.manhuntaddon.utilis.repeatingtask;

import de.shiirroo.manhunt.ManHuntPlugin;
import de.shiirroo.manhunt.command.subcommands.Ready;
import de.shiirroo.manhunt.event.menu.menus.setting.gamepreset.GamePresetMenu;
import de.shiirroo.manhunt.teams.model.ManHuntRole;
import de.shiirroo.manhuntaddon.ManHuntAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import javax.swing.*;

public class GameTimes implements Runnable {

    int assasinSize = -1;
    int hunterSize = -1;;
    int speedRunnerSize = -1;;

    int playerReady = -1;


    public void run() {
        if (!ManHuntPlugin.getGameData().getGameStatus().isGame()) {
            if(ManHuntAddon.getCustomPlayerScoreBoar().getCustomScoreBoard().checkSidebar()){
                ManHuntAddon.getCustomPlayerScoreBoar().getCustomScoreBoard().addSidebar(ManHuntAddon.getprefix());
                resetScore();
            }


            if (playerReady != Ready.ready.getPlayers().size()) {
                playerReady = Ready.ready.getPlayers().size();
                ManHuntAddon.getCustomPlayerScoreBoar().setSidebarScore(8, ChatColor.GREEN + "Ready: " + ChatColor.GOLD +  Ready.ready.getPlayers().size() + ChatColor.GRAY + " | " + ChatColor.GOLD + Bukkit.getOnlinePlayers().stream().filter(player -> player.getGameMode() != GameMode.SPECTATOR).count());
            }
            if(Ready.ready.getbossBarCreator() != null && Ready.ready.getbossBarCreator().isRunning()){
                ManHuntAddon.getCustomPlayerScoreBoar().setSidebarScore(2, ChatColor.GREEN + "Game start in: "+ ChatColor.GOLD + (Ready.ready.getbossBarCreator().getTimer() + 1));
            } else  ManHuntAddon.getCustomPlayerScoreBoar().setSidebarScore(2,"");
            if (assasinSize != ManHuntPlugin.getGameData().getPlayerData().getPlayersByRole(ManHuntRole.Assassin).size()) {
                assasinSize = ManHuntPlugin.getGameData().getPlayerData().getPlayersByRole(ManHuntRole.Assassin).size();
                ManHuntAddon.getCustomPlayerScoreBoar().setSidebarScore(6, ManHuntRole.Assassin.getChatColor() + "Assassin: " + ChatColor.GOLD + assasinSize + ChatColor.GRAY + " | " + ChatColor.GOLD + GamePresetMenu.preset.getAssassinMaxSize());
            }
            if (hunterSize != ManHuntPlugin.getGameData().getPlayerData().getPlayersByRole(ManHuntRole.Hunter).size()) {
                hunterSize = ManHuntPlugin.getGameData().getPlayerData().getPlayersByRole(ManHuntRole.Hunter).size();
                ManHuntAddon.getCustomPlayerScoreBoar().setSidebarScore(5, ManHuntRole.Hunter.getChatColor() + "Hunter: " + ChatColor.GOLD + hunterSize + ChatColor.GRAY + " | " + ChatColor.GOLD + GamePresetMenu.preset.getHunterMaxSize());
            }
            if (speedRunnerSize != ManHuntPlugin.getGameData().getPlayerData().getPlayersByRole(ManHuntRole.Speedrunner).size()) {
                speedRunnerSize = ManHuntPlugin.getGameData().getPlayerData().getPlayersByRole(ManHuntRole.Speedrunner).size();
                ManHuntAddon.getCustomPlayerScoreBoar().setSidebarScore(4, ManHuntRole.Speedrunner.getChatColor() + "Speedrunner: " + ChatColor.GOLD + speedRunnerSize + ChatColor.GRAY + " | " + ChatColor.GOLD + GamePresetMenu.preset.getSpeedRunnersMaxSize());
            }


        } else {
            ManHuntAddon.getCustomPlayerScoreBoar().removePlayersFromCustomScoreBoard();


        }
    }

    private void resetScore(){
        ManHuntAddon.setdefaultScoreBoard();
        assasinSize = -1;
        hunterSize = -1;;
        speedRunnerSize = -1;;
        playerReady = -1;

    }
}
