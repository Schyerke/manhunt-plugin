package manhunt.manhunt;

import manhunt.manhunt.commands.LeaveCommand;
import manhunt.manhunt.commands.openGui.*;
import manhunt.manhunt.game.listeners.GameEndListener;
import manhunt.manhunt.game.listeners.GameStartListener;
import manhunt.manhunt.inventory.listeners.InventoryItemClickedListener;
import manhunt.manhunt.inventory.listeners.InventoryOpenListener;
import manhunt.manhunt.inventory.listeners.PlayerFoodLevelChangeListener;
import manhunt.manhunt.player.listeners.PlayerJoinListener;
import manhunt.manhunt.world.listeners.PlayerTravelWorldsListener;
import manhunt.manhunt.player.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Manhunt extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void init(){
        listenersInit();
        commandsInit();
    }

    private void commandsInit(){
        getCommand("start").setExecutor(new StartCommand());
        getCommand("chooseRole").setExecutor(new ChooseClassCommand());
        getCommand("hunterCompass").setExecutor(new HunterCompassCommand());
        getCommand("leave").setExecutor(new LeaveCommand());
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("gethuntercompass").setExecutor(new GetHunterCompassCommand());
    }

    private void listenersInit(){
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryItemClickedListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTravelWorldsListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenListener(), this);
        getServer().getPluginManager().registerEvents(new GameEndListener(), this);
        getServer().getPluginManager().registerEvents(new GameStartListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFoodLevelChangeListener(), this);
    }
}
