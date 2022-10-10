package manhunt.manhunt.game.events;

import manhunt.manhunt.challenge.Challenge;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public class GameStartEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private List<Player> hunters;
    private List<Player> runners;
    private Challenge challengeActivated;

    public GameStartEvent(List<Player> hunters, List<Player> runners, Challenge challengeActivated){
        this.hunters = hunters;
        this.runners = runners;
        this.challengeActivated = challengeActivated;
    }

    public List<Player> getHunters() {
        return hunters;
    }

    public void setHunters(List<Player> hunters) {
        this.hunters = hunters;
    }

    public List<Player> getRunners() {
        return runners;
    }

    public void setRunners(List<Player> runners) {
        this.runners = runners;
    }

    public Challenge getChallengeActivated() {
        return challengeActivated;
    }

    public void setChallengeActivated(Challenge challengeActivated) {
        this.challengeActivated = challengeActivated;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
