package manhunt.manhunt.inventory.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodLevelChangeListener implements Listener {

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent event){
        Player player = (Player) event.getEntity();
        if(player.getWorld() == Bukkit.getWorld("world")){
            event.setCancelled(true);
        }
    }
}
