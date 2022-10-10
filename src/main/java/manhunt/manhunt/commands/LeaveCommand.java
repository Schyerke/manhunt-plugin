package manhunt.manhunt.commands;

import manhunt.manhunt.Managers;
import manhunt.manhunt.challenge.ChallengeManager;
import manhunt.manhunt.game.events.GameEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            final ChallengeManager challengeManager = Managers.getChallengeManager();
            Player player = (Player) sender;
            for(Player online : Bukkit.getOnlinePlayers()){
                online.sendMessage(player.getName() + " left the game");
            }
            GameEndEvent gameEndEvent = new GameEndEvent(new ArrayList<>(), new ArrayList<>(), challengeManager.getActiveChallenge());
            Bukkit.getPluginManager().callEvent(gameEndEvent);
            return true;
        }
        return false;
    }
}
