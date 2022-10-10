package manhunt.manhunt.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static manhunt.manhunt.inventory.InventoryManager.createItem;

public class InventoryObjectTriggers {
    public static ItemStack getChooseHunterTrigger() {
        return createItem(Material.IRON_SWORD, InventoryNames.CHOOSE_CLASS_HUNTER_TRIGGER, "Kill the runner!");
    }

    public static ItemStack getChooseRunnerTrigger() {
        return createItem(Material.IRON_BOOTS, InventoryNames.CHOOSE_CLASS_RUNNER_TRIGGER, "Run from the hunter!");
    }

    public static ItemStack getStartTrigger(int hunterCount, int runnerCount) {
        return createItem(Material.DIAMOND_AXE, InventoryNames.START_GAME_TRIGGER, "Start the game!", "Hunters: " + hunterCount, "Runners: " + runnerCount);
    }

    public static ItemStack getEnderDragonChallengeTrigger() {
        return createItem(Material.DRAGON_HEAD, InventoryNames.ENDER_DRAGON_CHALLENGE_TRIGGER, "Kill the Ender Dragon!");
    }

    public static ItemStack getWitherChallengeTrigger() {
        return createItem(Material.WITHER_SKELETON_SKULL, InventoryNames.WITHER_CHALLENGE_TRIGGER, "Kill the Wither!");
    }

    public static ItemStack getHunterCompassInventoryTrigger() {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName(InventoryNames.HUNTER_COMPASS_INVENTORY_TRIGGER);
        compass.setItemMeta(compassMeta);
        return compass;
    }

    public static ItemStack getChallengeInventoryTrigger() {
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = book.getItemMeta();
        bookMeta.setDisplayName(InventoryNames.CHALLENGE_INVENTORY_TRIGGER);
        book.setItemMeta(bookMeta);
        return book;
    }

    public static ItemStack getStartInventoryTrigger() {
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
        diamondSwordMeta.setDisplayName(InventoryNames.START_INVENTORY_TRIGGER);
        diamondSword.setItemMeta(diamondSwordMeta);
        return diamondSword;
    }

    public static ItemStack getChooseClassInventoryTrigger() {
        ItemStack diamondPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta diamondPickaxeMeta = diamondPickaxe.getItemMeta();
        diamondPickaxeMeta.setDisplayName(InventoryNames.CHOOSE_CLASS_INVENTORY_TRIGGER);
        diamondPickaxe.setItemMeta(diamondPickaxeMeta);
        return diamondPickaxe;
    }

}
