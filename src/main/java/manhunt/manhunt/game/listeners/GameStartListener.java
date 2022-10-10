package manhunt.manhunt.game.listeners;

import manhunt.manhunt.challenge.Challenge;
import manhunt.manhunt.game.events.GameStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class GameStartListener implements Listener {
    @EventHandler
    public void onGameStart(GameStartEvent event){
        List<Player> hunters = event.getHunters();
        List<Player> runners = event.getRunners();
        Challenge challenge = event.getChallengeActivated();
        for(Player hunter : hunters){
            hunter.setGameMode(GameMode.SURVIVAL);
            Bukkit.broadcastMessage(ChatColor.RED + hunter.getName() + " is a hunter!");
        }
        for(Player runner : runners){
            runner.setGameMode(GameMode.SURVIVAL);
            Bukkit.broadcastMessage(ChatColor.GREEN + runner.getName() + " is a runner!");
        }
        Bukkit.broadcastMessage(ChatColor.YELLOW + "Challenge activated: " + challenge.name());
    }
}
