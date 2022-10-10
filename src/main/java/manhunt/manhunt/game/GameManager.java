package manhunt.manhunt.game;

import manhunt.manhunt.Constants;
import manhunt.manhunt.Managers;
import manhunt.manhunt.challenge.ChallengeManager;
import manhunt.manhunt.game.events.GameStartEvent;
import manhunt.manhunt.inventory.InventoryObjectTriggers;
import manhunt.manhunt.role.Role;
import manhunt.manhunt.role.RoleManager;
import manhunt.manhunt.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

import static manhunt.manhunt.Constants.INVINCIBILITY_TIME_SECONDS;

public class GameManager {
    private static GameManager instance;
    public static GameManager getInstance() {
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }
    private GameManager() {
    }

    private final RoleManager roleManager = Managers.getRoleManager();
    private final WorldManager worldManager = Managers.getWorldManager();
    private final ChallengeManager challengeManager = Managers.getChallengeManager();

    public void startGame() {
        roleManager.save();
        boolean ok = true;
        for(Player player : Bukkit.getOnlinePlayers()){
            Role playerRole = roleManager.getRole(player);
            String role = "";
            if(playerRole == Role.HUNTER){
                role = player.getName() + " is a " + ChatColor.GREEN + " hunter";
            }
            else if(playerRole == Role.RUNNER){
                role = player.getName() + " is a " + ChatColor.GREEN + " runner";
            }
            else {
                role = ChatColor.RED + player.getName() + " NO CLASS CHOSEN";
                ok = false;
            }
            Bukkit.broadcastMessage(role);
        }
        if(!roleManager.atLeastOneHunter()){
            Bukkit.broadcastMessage(ChatColor.RED + "Not enough hunters");
            ok = false;
        }
        if(!roleManager.atLeastOneRunner()){
            Bukkit.broadcastMessage(ChatColor.RED + "Not enough runners");
            ok = false;
        }
        if(!ok){
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle("Game starting!", "You are a " + roleManager.getRole(player).name(), 10, 70, 20);
            player.closeInventory();
        }
        List<Player> hunters = roleManager.getHunters();
        List<Player> runners = roleManager.getRunners();

        challengeManager.init();
        worldManager.init();
        playerInit(hunters, runners);

        GameStartEvent gameStartEvent = new GameStartEvent(hunters, runners, challengeManager.getActiveChallenge());
        Bukkit.getPluginManager().callEvent(gameStartEvent);

        Bukkit.broadcastMessage("Game started!");
    }

    public void endGame() {
        for(Player player : Bukkit.getOnlinePlayers()){
            Managers.getPlayerManager().onJoin(player);
        }
        roleManager.clearAll();
        Bukkit.broadcastMessage("Game ended!");
    }

    private void playerInit(List<Player> hunters, List<Player> runners) {
        World currentWorld = worldManager.getCurrentOverWorld();

        for(Player player : Bukkit.getOnlinePlayers()){
            player.setInvulnerable(false);
            player.getInventory().clear();
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setSaturation(20);
            player.setExp(0);
            player.setLevel(0);
            player.teleport(currentWorld.getSpawnLocation());
        }

        for(Player player : hunters){
            player.getInventory().addItem(InventoryObjectTriggers.getHunterCompassInventoryTrigger());
            player.setPlayerListName("§c" + player.getName());
        }
        for(Player player : runners){
            player.setInvulnerable(true);
            player.setPlayerListName("§a" + player.getName());
            player.sendTitle("You have " + INVINCIBILITY_TIME_SECONDS + " seconds of invincibility", "", 10, 70, 20);
        }

        Bukkit.getScheduler().runTaskLater(Constants.PLUGIN, () -> {
            for(Player player : runners){
                player.setInvulnerable(false);
            }
            Bukkit.broadcastMessage("Invincibility ended!");
        }, 20*INVINCIBILITY_TIME_SECONDS + 35);
    }


}
