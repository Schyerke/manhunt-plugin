package manhunt.manhunt;

import manhunt.manhunt.challenge.ChallengeManager;
import manhunt.manhunt.game.GameManager;
import manhunt.manhunt.inventory.InventoryManager;
import manhunt.manhunt.player.PlayerInGameManager;
import manhunt.manhunt.player.PlayerManager;
import manhunt.manhunt.role.RoleManager;
import manhunt.manhunt.world.WorldManager;

public final class Managers {
    public static GameManager getGameManager() {
        return GameManager.getInstance();
    }
    public static RoleManager getRoleManager() {
        return RoleManager.getInstance();
    }
    public static PlayerManager getPlayerManager() {
        return PlayerManager.getInstance();
    }
    public static InventoryManager getInventoryManager() {
        return InventoryManager.getInstance();
    }
    public static WorldManager getWorldManager() {
        return WorldManager.getInstance();
    }
    public static ChallengeManager getChallengeManager() {
        return ChallengeManager.getInstance();
    }
    public static PlayerInGameManager getPlayerInGameManager() {
        return PlayerInGameManager.getInstance();
    }
}
