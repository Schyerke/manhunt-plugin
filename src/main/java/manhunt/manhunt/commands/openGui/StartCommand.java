package manhunt.manhunt.commands.openGui;

import manhunt.manhunt.Managers;
import manhunt.manhunt.inventory.InventoryHolders;
import manhunt.manhunt.inventory.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            InventoryManager inventoryManager = Managers.getInventoryManager();
            inventoryManager.createStartInventory(0, 0);
            player.openInventory(InventoryHolders.getStartInventory());
            return true;
        }
        if(commandSender instanceof ConsoleCommandSender){
            commandSender.sendMessage("You can't use this command in the console");
            return true;
        }
        return false;
    }
}
