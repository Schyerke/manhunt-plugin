package manhunt.manhunt.inventory;

import manhunt.manhunt.Managers;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InventoryHolders {
    private static final InventoryManager inventoryManager = Managers.getInventoryManager();

    private static Inventory chooseRoleInventory;
    private static Inventory hunterCompassInventory;
    private static Inventory startInventory;
    private static Inventory challengeInventory;

    public static Inventory getChooseRoleInventory() {
        if(chooseRoleInventory == null){
            inventoryManager.createChooseClassInventory();
        }
        return chooseRoleInventory;
    }

    public static void setChooseRoleInventory(Inventory chooseRoleInventory) {
        InventoryHolders.chooseRoleInventory = chooseRoleInventory;
    }

    public static Inventory getHunterCompassInventory() {
        if(hunterCompassInventory == null){
            inventoryManager.createHunterCompassInventory(new ArrayList<>(0));
        }
        return hunterCompassInventory;
    }

    public static void setHunterCompassInventory(Inventory hunterCompassInventory) {
        InventoryHolders.hunterCompassInventory = hunterCompassInventory;
    }

    public static Inventory getStartInventory() {
        if(startInventory == null){
            inventoryManager.createStartInventory(0, 0);
        }
        return startInventory;
    }

    public static void setStartInventory(Inventory startInventory) {
        InventoryHolders.startInventory = startInventory;
    }

    public static void setChallengeInventory(Inventory inventory) {
        InventoryHolders.challengeInventory = inventory;
    }

    public static Inventory getChallengeInventory() {
        if(challengeInventory == null){
            inventoryManager.createChallengeInventory();
        }
        return challengeInventory;
    }
}
