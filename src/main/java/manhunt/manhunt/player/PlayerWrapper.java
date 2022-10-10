package manhunt.manhunt.player;

import manhunt.manhunt.role.Role;
import org.bukkit.entity.Player;

public class PlayerWrapper {
    private final Player player;
    private final PlayerStatistics statistics;

    public PlayerWrapper(Player player, PlayerStatistics statistics) {
        this.player = player;
        this.statistics = statistics;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }
}
