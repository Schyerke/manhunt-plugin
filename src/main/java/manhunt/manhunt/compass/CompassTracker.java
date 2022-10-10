package manhunt.manhunt.compass;

import manhunt.manhunt.Constants;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CompassTracker {
    private Player runner;
    private  Player hunter;
    private int compassTrackingTaskId;

    private static CompassTracker instance;
    public static CompassTracker getInstance() {
        if(instance == null){
            instance = new CompassTracker();
        }
        return instance;
    }

    private CompassTracker(){
    }

    public Player getHunter() {
        return hunter;
    }

    public Player getRunner() {
        return runner;
    }

    public void stop(){
        Bukkit.getScheduler().cancelTask(this.compassTrackingTaskId);
    }

    public void startTracking(Player target, Player player){
        if(player == null || target == null){
            return;
        }
        try{
            stop();
        }
        catch (Exception e){
            System.out.println("Compass tracker was not running");
        }
        this.hunter = player;
        this.runner = target;
        this.compassTrackingTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Constants.PLUGIN, () -> {
            hunter.setCompassTarget(runner.getLocation());
        }, 0, 20);
    }
}
