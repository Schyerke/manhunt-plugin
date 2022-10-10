package manhunt.manhunt.challenge.listeners;

import manhunt.manhunt.Managers;
import manhunt.manhunt.Pair;
import manhunt.manhunt.challenge.Challenge;
import manhunt.manhunt.game.events.GameEndEvent;
import manhunt.manhunt.role.RoleManager;
import manhunt.manhunt.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.List;

public class WitherChallengeListener implements Listener {
    @EventHandler
    public void onWitherDeath(EntityDeathEvent event){
        if(event.getEntityType() == EntityType.WITHER){
            final RoleManager roleManager = Managers.getRoleManager();
            Pair<List<Player>, List<Player>, List<Player>> winnersAndLosers = roleManager.getSavedPlayers();
            GameEndEvent gameEndEvent = new GameEndEvent(
                    winnersAndLosers.getRunners(),
                    winnersAndLosers.getHunters(),
                    Challenge.WITHER
            );
            EntityDeathEvent.getHandlerList().unregister(this);
            Bukkit.getPluginManager().callEvent(gameEndEvent);
        }
    }

    @EventHandler
    public void onRunnerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        final RoleManager roleManager = Managers.getRoleManager();
        if(roleManager.isRunner(player)){
            roleManager.removeRunner(player);
        }
        if(roleManager.getRunnersCount() == 0){
            GameEndEvent gameEndEvent = new GameEndEvent(
                    roleManager.getHunters(),
                    roleManager.getRunners(),
                    Challenge.WITHER
            );
            PlayerDeathEvent.getHandlerList().unregister(this);
            Bukkit.getPluginManager().callEvent(gameEndEvent);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        final RoleManager roleManager = Managers.getRoleManager();
        final WorldManager worldManager = Managers.getWorldManager();
        event.setRespawnLocation(worldManager.getCurrentOverWorld().getSpawnLocation());
        if(roleManager.isRunner(player)){
            player.setGameMode(GameMode.SPECTATOR);
            roleManager.addSpectator(player);
        }
    }
}
