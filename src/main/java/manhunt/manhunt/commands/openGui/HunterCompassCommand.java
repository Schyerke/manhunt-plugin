package manhunt.manhunt.commands.openGui;

import manhunt.manhunt.inventory.InventoryHolders;
import manhunt.manhunt.role.RoleManager;
import manhunt.manhunt.inventory.InventoryManager;
import manhunt.manhunt.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HunterCompassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof org.bukkit.entity.Player){
            Player player = (Player) commandSender;
            RoleManager roleManager = Managers.getRoleManager();
            if(roleManager.isHunter(player)){
                InventoryManager inventoryManager = Managers.getInventoryManager();
                inventoryManager.createHunterCompassInventory(roleManager.getRunners());
                player.openInventory(InventoryHolders.getHunterCompassInventory());
                return true;
            }
            player.sendMessage("You are not a hunter");
            return true;
        }
        if(commandSender instanceof org.bukkit.command.ConsoleCommandSender){
            commandSender.sendMessage("You can't use this command in the console");
            return true;
        }
        return false;
    }
}
