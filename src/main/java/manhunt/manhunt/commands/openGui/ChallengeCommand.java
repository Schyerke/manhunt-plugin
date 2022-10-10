package manhunt.manhunt.commands.openGui;

import manhunt.manhunt.inventory.InventoryHolders;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ChallengeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.openInventory(InventoryHolders.getChallengeInventory());
            return true;
        }
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage("You can't use this command in the console");
            return true;
        }
        return false;
    }
}
