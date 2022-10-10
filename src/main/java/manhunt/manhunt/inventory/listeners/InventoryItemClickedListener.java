package manhunt.manhunt.inventory.listeners;

import manhunt.manhunt.Managers;
import manhunt.manhunt.challenge.Challenge;
import manhunt.manhunt.compass.CompassTracker;
import manhunt.manhunt.game.GameManager;
import manhunt.manhunt.inventory.InventoryHolders;
import manhunt.manhunt.inventory.InventoryObjectTriggers;
import manhunt.manhunt.player.PlayerManager;
import manhunt.manhunt.role.RoleManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

public class InventoryItemClickedListener implements Listener {
    private final RoleManager roleManager = Managers.getRoleManager();
    private final GameManager gameManager = Managers.getGameManager();

    // on challenge inventory click
    @EventHandler
    public void onChallengeInventoryClick(InventoryClickEvent event){
        if(event.getInventory().equals(InventoryHolders.getChallengeInventory())){
            event.setCancelled(true);
            if(event.getCurrentItem() != null){
                ItemStack item = event.getCurrentItem();
                Challenge challenge = null;
                if(item.equals(InventoryObjectTriggers.getEnderDragonChallengeTrigger())){
                    challenge = Challenge.ENDER_DRAGON;
                }
                else if(item.equals(InventoryObjectTriggers.getWitherChallengeTrigger())){
                    challenge = Challenge.WITHER;
                }
                challenge = challenge == null ? Challenge.ENDER_DRAGON : challenge;
                Bukkit.broadcastMessage("Challenge set to " + challenge.name());
                Managers.getChallengeManager().setActiveChallenge(challenge);
            }
        }
    }


    // on choose role inventory click (preventing getting items and fire event)
    @EventHandler
    public void onChooseRoleInventoryClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player && event.getClickedInventory() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getClickedInventory().equals(InventoryHolders.getChooseRoleInventory())) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                if (item != null) {
                    if (item.equals(InventoryObjectTriggers.getChooseHunterTrigger())) {
                        if(roleManager.addHunter(player)){
                            updateStartInventory();
                            player.sendMessage("You are now a hunter");
                        } else {
                            player.sendMessage("You are already a hunter");
                        }
                    }
                    if (item.equals(InventoryObjectTriggers.getChooseRunnerTrigger())) {
                        if(roleManager.addRunner(player)){
                            updateStartInventory();
                            player.sendMessage("You are now a runner");
                        } else {
                            player.sendMessage("You are already a runner");
                        }
                    }
                }
            }
        }
    }

    // re-load start inventory to every viewer
    private void updateStartInventory(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getOpenInventory().getTopInventory().equals(InventoryHolders.getStartInventory())){
                player.getOpenInventory().getTopInventory().setContents(InventoryHolders.getStartInventory().getContents());
            }
        }
    }


    // on hunter compass inventory click (preventing getting items and fire event)
    @EventHandler
    public void onHunterCompassInventoryClick(InventoryClickEvent event){
        if(event.getInventory().equals(InventoryHolders.getHunterCompassInventory())){
            event.setCancelled(true);
            final ItemStack itemClicked = event.getCurrentItem();
            if (itemClicked == null || itemClicked.getType().isAir()) {
                return;
            }
            final PlayerManager playerManager = Managers.getPlayerManager();
            final CompassTracker compassTracker = CompassTracker.getInstance();

            Player playerWhoClicked = (Player) event.getWhoClicked();

            playerWhoClicked.closeInventory();

            ItemMeta itemMeta = itemClicked.getItemMeta();
            PlayerProfile playerProfile = ((SkullMeta)itemMeta).getOwnerProfile();
            String playerName = playerProfile.getName();

            Player player = playerManager.getPlayerByName(playerName);
            compassTracker.startTracking(player, playerWhoClicked);
        }

    }



    // on start inventory click (preventing getting items and fire event)
    @EventHandler
    public void onStartInventoryClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player && event.getClickedInventory() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getClickedInventory().equals(InventoryHolders.getStartInventory())) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                if (item != null) {
                    if (item.equals(InventoryObjectTriggers.getStartTrigger(
                            roleManager.getHuntersCount(),
                            roleManager.getRunnersCount()
                    ))) {
                        gameManager.startGame();
                    }
                }
            }
        }
    }
}
