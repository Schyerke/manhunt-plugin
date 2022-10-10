package manhunt.manhunt.role;

import manhunt.manhunt.Pair;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoleManager {
    private static RoleManager instance;
    public static RoleManager getInstance() {
        if(instance == null){
            instance = new RoleManager();
        }
        return instance;
    }
    private RoleManager() {
    }


    private List<Player> runners = new ArrayList<>();
    private List<Player> hunters = new ArrayList<>();
    private List<Player> spectators = new ArrayList<>();

    private Pair<List<Player>, List<Player>, List<Player>> savedPlayers;

    public Pair<List<Player>, List<Player>, List<Player>> getSavedPlayers() {
        return savedPlayers;
    }

    public void setSavedPlayers(Pair<List<Player>, List<Player>, List<Player>> savedPlayers) {
        this.savedPlayers = savedPlayers;
    }

    public List<Player>     getRunners() {
        return runners;
    }

    public void setRunners(List<Player> runners) {
        this.runners = runners;
    }

    public List<Player> getHunters() {
        return hunters;
    }

    public void setHunters(List<Player> hunters) {
        this.hunters = hunters;
    }

    public List<Player> getSpectators() {
        return spectators;
    }

    public void setSpectators(List<Player> spectators) {
        this.spectators = spectators;
    }

    public boolean addRunner(Player player){
        if(!runners.contains(player)){
            hunters.remove(player);
            runners.add(player);
            return true;
        }
        return false;
    }

    public boolean addHunter(Player player){
        if(!hunters.contains(player)){
            runners.remove(player);
            hunters.add(player);
            return true;
        }
        return false;
    }

    public void addSpectator(Player player){
        spectators.add(player);
    }

    public void removeRunner(Player player){
        runners.remove(player);
    }

    public void removeHunter(Player player){
        hunters.remove(player);
    }

    public void removeSpectator(Player player){
        spectators.remove(player);
    }

    public void clearRunners(){
        runners.clear();
    }

    public void clearHunters(){
        hunters.clear();
    }

    public void clearSpectators(){
        spectators.clear();
    }

    public void clearAll(){
        clearRunners();
        clearHunters();
        clearSpectators();
        savedPlayers.getRunners().clear();
        savedPlayers.getHunters().clear();
        savedPlayers.getSpectators().clear();
    }

    public boolean isRunner(Player player){
        return savedPlayers != null && (savedPlayers.getRunners().contains(player) || runners.contains(player));
    }

    public boolean isHunter(Player player){
        return savedPlayers != null && (savedPlayers.getHunters().contains(player) || hunters.contains(player));
    }

    public boolean isSpectator(Player player){
        return savedPlayers != null && (savedPlayers.getSpectators().contains(player) || spectators.contains(player));
    }

    public boolean isPlayer(Player player){
        return isRunner(player) || isHunter(player) || isSpectator(player);
    }

    public boolean isPlayerInGame(Player player){
        return isRunner(player) || isHunter(player);
    }

    public boolean isPlayerInLobby(Player player){
        return isPlayer(player) && !isPlayerInGame(player);
    }

    public boolean isPlayerInGameOrLobby(Player player){
        return isPlayerInGame(player) || isPlayerInLobby(player);
    }

    public boolean isPlayerInGameOrSpectator(Player player){
        return isPlayerInGame(player) || isSpectator(player);
    }

    public boolean isPlayerInLobbyOrSpectator(Player player){
        return isPlayerInLobby(player) || isSpectator(player);
    }

    public int getHuntersCount() {
        int count = 0;
        for (Player player : hunters) {
            if (player.isOnline()) {
                count++;
            }
        }
        return count;
    }

    public int getRunnersCount() {
        int count = 0;
        for (Player player : runners) {
            if (player.isOnline()) {
                count++;
            }
        }
        return count;
    }

    public Role getRole(Player player) {
        if(isHunter(player)){
            return Role.HUNTER;
        } else if(isRunner(player)){
            return Role.RUNNER;
        }
        return null;
    }

    public boolean isRunner(String displayName) {
        if(savedPlayers != null){
            for (Player player : savedPlayers.getRunners()) {
                if(player.getDisplayName().equals(displayName)){
                    return true;
                }
            }
        }
        for (Player player : runners) {
            if (player.getDisplayName().equals(displayName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHunter(String displayName) {
        for (Player player : hunters) {
            if (player.getDisplayName().equals(displayName)) {
                return true;
            }
        }
        if(savedPlayers != null){
            for (Player player : savedPlayers.getHunters()) {
                if (player.getDisplayName().equals(displayName)) {
                    return true;
                }
            }
        }
        return false;
    }


    public void removeRunner(String displayName) {
        for (Player player : runners) {
            if (player.getDisplayName().equals(displayName)) {
                runners.remove(player);
                return;
            }
        }
        if(savedPlayers != null){
            for (Player player : savedPlayers.getRunners()) {
                if (player.getDisplayName().equals(displayName)) {
                    savedPlayers.getRunners().remove(player);
                    return;
                }
            }
        }
    }

    public void removeHunter(String displayName) {
        for (Player player : hunters) {
            if (player.getDisplayName().equals(displayName)) {
                hunters.remove(player);
                return;
            }
        }
        if(savedPlayers != null){
            for (Player player : savedPlayers.getHunters()) {
                if (player.getDisplayName().equals(displayName)) {
                    savedPlayers.getHunters().remove(player);
                    return;
                }
            }
        }
    }

    public void save(){
        this.savedPlayers = new Pair<>(hunters, runners, spectators);
    }

    public boolean atLeastOneHunter() {
        return hunters.iterator().hasNext();
    }

    public boolean atLeastOneRunner() {
        return runners.iterator().hasNext();
    }
}
