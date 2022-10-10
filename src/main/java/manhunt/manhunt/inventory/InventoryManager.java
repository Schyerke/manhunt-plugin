package manhunt.manhunt.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryManager {

    private static InventoryManager instance;
    public static InventoryManager getInstance() {
        if(instance == null){
            instance = new InventoryManager();
        }
        return instance;
    }
    private InventoryManager() {
    }


    {
        createChooseClassInventory();
        createStartInventory(0, 0);
        createHunterCompassInventory(new ArrayList<>());
    }

    public static ItemStack createItem(Material material, String name, String... lore){
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getHead(Player player){
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);

        skull.setItemMeta(skullMeta);
        return skull;
    }

    public void createChooseClassInventory(){
        Inventory inventory = Bukkit.createInventory(null, 9, InventoryTitles.CHOOSE_CLASS);
        inventory.addItem(InventoryObjectTriggers.getChooseHunterTrigger());
        inventory.addItem(InventoryObjectTriggers.getChooseRunnerTrigger());

        InventoryHolders.setChooseRoleInventory(inventory);
    }

    public void createHunterCompassInventory(List<Player> runners){
        Inventory inventory = Bukkit.createInventory(null, 18, InventoryTitles.HUNTER_COMPASS);

        // get runners' minecraft skin and create a head item with its skin and add it to the inventory
        for(Player runner : runners){
            inventory.addItem(getHead(runner));
        }
        InventoryHolders.setHunterCompassInventory(inventory);
    }

    public void createChallengeInventory(){
        Inventory inventory = Bukkit.createInventory(null, 9*3, InventoryTitles.CHALLENGE);
        inventory.addItem(InventoryObjectTriggers.getEnderDragonChallengeTrigger());
        inventory.addItem(InventoryObjectTriggers.getWitherChallengeTrigger());

        InventoryHolders.setChallengeInventory(inventory);
    }

    public void createStartInventory(int hunterCount, int runnerCount){
        Inventory inventory = Bukkit.createInventory(null, 9, InventoryTitles.START_GAME);
        inventory.addItem(InventoryObjectTriggers.getStartTrigger(hunterCount, runnerCount));

        InventoryHolders.setStartInventory(inventory);
    }
}
