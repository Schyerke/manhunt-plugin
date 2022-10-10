package manhunt.manhunt.game.listeners;

import manhunt.manhunt.Constants;
import manhunt.manhunt.Managers;
import manhunt.manhunt.challenge.Challenge;
import manhunt.manhunt.game.events.GameEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class GameEndListener implements Listener {
    @EventHandler
    public void onGameEnd(GameEndEvent event){
        List<Player> winners = event.getWinners();
        List<Player> losers = event.getLosers();
        Challenge challengeActivated = event.getChallengeActivated();

        for(Player winner : winners){
            Bukkit.broadcastMessage(ChatColor.GREEN + winner.getName() + " won the game!");
            winner.sendTitle(ChatColor.YELLOW + "You won!", "Challenge: " + challengeActivated.name(), 10, 70, 20);
        }
        for(Player loser : losers){
            Bukkit.broadcastMessage(ChatColor.RED + loser.getName() + " lost the game!");
            loser.sendTitle(ChatColor.RED + "You lost!", "Challenge:" + challengeActivated.name(), 10, 70, 20);
        }
        Bukkit.getScheduler().runTaskLater(Constants.PLUGIN, this::init, 20*10);
    }

    private void init(){

        Managers.getGameManager().endGame();
    }
}
