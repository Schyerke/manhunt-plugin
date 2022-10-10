package manhunt.manhunt.player;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

public class PlayerInGameManager {
    private static PlayerInGameManager instance;
    public static PlayerInGameManager getInstance() {
        if(instance == null){
            instance = new PlayerInGameManager();
        }
        return instance;
    }
    private PlayerInGameManager() {
    }

    private final HashMap<Player, Location> lastHunterSpawnLocation = new HashMap<>();

    public void setLastHunterSpawnLocation(Player player, Location location){
        lastHunterSpawnLocation.put(player, location);
    }

    public void runnerDead(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.setInvulnerable(true);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
    }

    public void hunterDead(Player player) {
        player.teleport(Objects.requireNonNullElseGet(lastHunterSpawnLocation.get(player),
                () -> player.getWorld().getSpawnLocation()
        ));

        player.setHealth(20);
        player.setFoodLevel(20);
        player.sendMessage("You have been revived");
        player.setExp(0);
        player.setLevel(0);
        player.setSaturation(20);
    }
}
