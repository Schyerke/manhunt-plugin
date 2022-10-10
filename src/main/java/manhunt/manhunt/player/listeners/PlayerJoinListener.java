package manhunt.manhunt.player.listeners;

import manhunt.manhunt.Managers;
import manhunt.manhunt.player.PlayerManager;
import manhunt.manhunt.role.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final PlayerManager playerManager = Managers.getPlayerManager();
    private final RoleManager roleManager = Managers.getRoleManager();

    // This method is called when a player joins the server
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(playerManager.isPlayerQuit(player.getName())){
            playerManager.removePlayerQuitName(player.getName());

            if(roleManager.isRunner(player.getDisplayName())){
                player.sendMessage(ChatColor.GREEN + "Welcome back to the game!");
                roleManager.removeRunner(player.getDisplayName());
                roleManager.addRunner(player);
            }
            else if (roleManager.isHunter(player.getDisplayName())){
                player.sendMessage(ChatColor.GREEN + "Welcome back to the game!");
                roleManager.removeHunter(player.getDisplayName());
                roleManager.addHunter(player);
            }
            else{
                player.sendMessage(ChatColor.RED + "You are not in the game!");
                Managers.getPlayerManager().onJoin(player);

                player.sendTitle(ChatColor.RED + "Welcome to Manhunt!", ChatColor.YELLOW + "This is a work in progress", 10, 70, 20);
            }
        }

    }
}
