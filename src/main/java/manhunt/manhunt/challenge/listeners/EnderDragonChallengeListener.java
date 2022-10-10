package manhunt.manhunt.challenge.listeners;


import manhunt.manhunt.Managers;
import manhunt.manhunt.Pair;
import manhunt.manhunt.challenge.Challenge;
import manhunt.manhunt.game.events.GameEndEvent;
import manhunt.manhunt.player.PlayerInGameManager;
import manhunt.manhunt.role.RoleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnderDragonChallengeListener implements Listener {

    @EventHandler
    public void onEnderDragonDeath(EntityDeathEvent event){
        if(event.getEntityType() == EntityType.ENDER_DRAGON){
            final RoleManager roleManager = Managers.getRoleManager();
            Pair<List<Player>, List<Player>, List<Player>> winnersAndLosers = roleManager.getSavedPlayers();
            GameEndEvent gameEndEvent = new GameEndEvent(
                    winnersAndLosers.getRunners(),
                    winnersAndLosers.getHunters(),
                    Challenge.ENDER_DRAGON
            );
            EntityDeathEvent.getHandlerList().unregister(this);
            Bukkit.getPluginManager().callEvent(gameEndEvent);
        }
    }

    @EventHandler
    public void onPlayerDeath(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player player){

            final RoleManager roleManager = Managers.getRoleManager();

            if(player.getHealth() - e.getDamage() > 1){
                return;
            }
            // player is dead
            final PlayerInGameManager playerInGameManager = Managers.getPlayerInGameManager();
            player.getWorld().strikeLightningEffect(player.getLocation());
            if(roleManager.isRunner(player)){
                playerInGameManager.runnerDead(player);
                Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " was killed by " + ChatColor.RED + e.getDamager().getName());
                roleManager.removeRunner(player);
                for(ItemStack item : player.getInventory().getContents()){
                    if(item != null && item.getType() != Material.AIR){
                        player.getWorld().dropItemNaturally(player.getLocation(), item);
                    }
                }
            } else if(roleManager.isHunter(player)){
                playerInGameManager.hunterDead(player);
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " was killed by " + ChatColor.GREEN + e.getDamager().getName());
                // drop every item in the inventory except the compass
                for(ItemStack item : player.getInventory().getContents()){
                    if(item != null && item.getType() != Material.COMPASS){
                        player.getWorld().dropItemNaturally(player.getLocation(), item);
                    }
                }
            }

            if(roleManager.getRunnersCount() == 0){
                Pair<List<Player>, List<Player>, List<Player>> winnersAndLosers = roleManager.getSavedPlayers();
                GameEndEvent gameEndEvent = new GameEndEvent(
                        winnersAndLosers.getHunters(),
                        winnersAndLosers.getRunners(),
                        Challenge.ENDER_DRAGON
                );
                PlayerDeathEvent.getHandlerList().unregister(this);
                Bukkit.getPluginManager().callEvent(gameEndEvent);
            }
        }
    }

    // add hunter's spawn location when he clicks on a bed otherwise set the spawn location to the world's spawn
    @EventHandler
    public void setHunterSpawnLocation(PlayerBedEnterEvent event){
        if(Managers.getRoleManager().isHunter(event.getPlayer())){
            Location location = event.getBed().getLocation();
            Managers.getPlayerInGameManager().setLastHunterSpawnLocation(event.getPlayer(), location);
        }
    }


    // Hunter cant drop the compass
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player p = event.getPlayer();

        if (event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            event.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You can't drop the item");
        }
    }



}
