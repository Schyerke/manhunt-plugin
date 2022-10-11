package manhunt.manhunt.player;

import manhunt.manhunt.Constants;
import manhunt.manhunt.inventory.InventoryObjectTriggers;
import manhunt.manhunt.persistent.FileHelper;
import manhunt.manhunt.persistent.PlayerStatistics;
import manhunt.manhunt.persistent.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static PlayerManager instance;
    public static PlayerManager getInstance() {
        if(instance == null){
            instance = new PlayerManager();
        }
        return instance;
    }
    private PlayerManager() {
    }


    private final List<PlayerWrapper> players = new ArrayList<>();
    private final List<String> playerQuitNames = new ArrayList<>();


    public void onJoin(Player player){

        player.setGameMode(GameMode.ADVENTURE);

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setExp(0);
        player.setLevel(0);

        teleportSpawn(player);
        messageJoin(player);
    }

    public void teleportSpawn(Player player){
        player.teleport(Constants.SPAWN_LOCATION);
        player.setInvulnerable(true);

        player.getInventory().clear();
        player.getInventory().addItem(InventoryObjectTriggers.getStartInventoryTrigger());
        player.getInventory().addItem(InventoryObjectTriggers.getChooseClassInventoryTrigger());
        player.getInventory().addItem(InventoryObjectTriggers.getChallengeInventoryTrigger());

        player.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
    }

    public void messageJoin(Player player){
        Bukkit.getScheduler().runTaskLater(Constants.PLUGIN, () -> {
            player.sendMessage("Welcome to Manhunt!");
            player.sendMessage("To start a game, type /start");
            player.sendMessage("To choose a class, type /chooserole");
            player.sendMessage("To see the challenges, type /challenge");
            player.sendMessage("To leave a game, type /leave");
            player.sendMessage(ChatColor.GREEN + "Have Fun!");
        }, 20);
    }

    public PlayerWrapper findPlayerWrapperByPlayer(Player player){
        for (PlayerWrapper playerWrapper : players) {
            if(playerWrapper.getPlayer().getDisplayName().equals(player.getDisplayName())){
                return playerWrapper;
            }
        }
        return null;
    }


    public List<PlayerWrapper> getPlayers() {
        return players;
    }

    public List<String> getPlayerQuitNames() {
        return playerQuitNames;
    }

    public void addPlayerQuitName(String name){
        playerQuitNames.add(name);
    }

    public void removePlayerQuitName(String name){
        playerQuitNames.remove(name);
    }

    public boolean isPlayerQuit(String name) {
        return playerQuitNames.contains(name);
    }

    public Player getPlayerByName(String playerName) {
        for(Player online : Bukkit.getOnlinePlayers()){
            if(online.getName().equals(playerName)){
                return online;
            }
        }
        return null;
    }

    public void addPlayer(PlayerWrapper playerWrapper) {
        players.add(playerWrapper);
    }

    // read from FileHelper data and find player in the json, and then create a PlayerStatistics object
    public PlayerWrapper loadPlayerWrapper(Player player) {
        PlayerWrapper playerWrapper = FileHelper.getInstance().loadPlayerStatistics(player);
        if(playerWrapper == null){
            FileHelper.getInstance().savePlayerStatistics(new PlayerWrapper(player, new PlayerStatistics(0, 0, 0, 0)));
        }
        return playerWrapper;
    }
}
