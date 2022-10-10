package manhunt.manhunt.game.events;

import manhunt.manhunt.challenge.Challenge;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public class GameEndEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private List<Player> winners;
    private List<Player> losers;
    private Challenge challengeActivated;

    public GameEndEvent(List<Player> winners, List<Player> losers, Challenge challengeActivated){
        this.winners = winners;
        this.losers = losers;
        this.challengeActivated = challengeActivated;
    }

    public List<Player> getWinners() {
        return winners;
    }

    public void setWinners(List<Player> winners) {
        this.winners = winners;
    }

    public List<Player> getLosers() {
        return losers;
    }

    public void setLosers(List<Player> losers) {
        this.losers = losers;
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