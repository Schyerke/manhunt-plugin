package manhunt.manhunt.commands.openGui;

import manhunt.manhunt.inventory.InventoryObjectTriggers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetHunterCompassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            player.getInventory().addItem(InventoryObjectTriggers.getHunterCompassInventoryTrigger());
            return true;
        }
        return false;
    }
}
