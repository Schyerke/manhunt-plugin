package manhunt.manhunt.inventory.listeners;

import manhunt.manhunt.Managers;
import manhunt.manhunt.inventory.InventoryHolders;
import manhunt.manhunt.inventory.InventoryManager;
import manhunt.manhunt.inventory.InventoryNames;
import manhunt.manhunt.role.RoleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryOpenListener implements Listener {
    private final RoleManager roleManager = Managers.getRoleManager();
    private final InventoryManager inventoryManager = Managers.getInventoryManager();
    //on start inventory open
    @EventHandler
    public void onStartInventoryOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item==null)return;
        if(item.getItemMeta().getDisplayName().equals(InventoryNames.START_INVENTORY_TRIGGER)){
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                inventoryManager.createStartInventory(roleManager.getHuntersCount(), roleManager.getRunnersCount());
                player.openInventory(InventoryHolders.getStartInventory());
            }
        }
    }

    // on choose role inventory open
    @EventHandler
    public void onChooseRoleInventoryOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item==null)return;
        if(item.getItemMeta().getDisplayName().equals(InventoryNames.CHOOSE_CLASS_INVENTORY_TRIGGER)){
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                inventoryManager.createChooseClassInventory();
                player.openInventory(InventoryHolders.getChooseRoleInventory());
            }
        }
    }

    // on challenge inventory open
    @EventHandler
    public void onChallengeInventoryOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item==null)return;
        if(item.getItemMeta().getDisplayName().equals(InventoryNames.CHALLENGE_INVENTORY_TRIGGER)){
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                inventoryManager.createChallengeInventory();
                player.openInventory(InventoryHolders.getChallengeInventory());
            }
        }
    }

    // on hunter compass inventory open
    @EventHandler
    public void onHunterCompassInventoryOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item==null)return;
        if(item.getItemMeta().getDisplayName().equals(InventoryNames.HUNTER_COMPASS_INVENTORY_TRIGGER)){
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                inventoryManager.createHunterCompassInventory(roleManager.getRunners());
                player.openInventory(InventoryHolders.getHunterCompassInventory());
            }
        }
    }
}
