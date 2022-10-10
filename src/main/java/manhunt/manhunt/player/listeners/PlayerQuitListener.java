package manhunt.manhunt.player.listeners;

import manhunt.manhunt.Managers;
import manhunt.manhunt.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final PlayerManager playerManager = Managers.getPlayerManager();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        playerManager.addPlayerQuitName(event.getPlayer().getName());
    }


}
