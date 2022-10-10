package manhunt.manhunt.challenge;

import manhunt.manhunt.Constants;
import manhunt.manhunt.challenge.listeners.EnderDragonChallengeListener;
import manhunt.manhunt.challenge.listeners.WitherChallengeListener;

public class ChallengeExecutor {
    private ChallengeExecutor(){}

    public static void execute(Challenge challenge){
        switch(challenge){
            case WITHER:
                witherChallenge();
                break;
            default:
                enderDragonChallenge();
        }
    }

    private static void witherChallenge() {
        Constants.PLUGIN.getServer().getPluginManager().registerEvents(new WitherChallengeListener(), Constants.PLUGIN);
    }

    private static void enderDragonChallenge(){
        Constants.PLUGIN.getServer().getPluginManager().registerEvents(new EnderDragonChallengeListener(), Constants.PLUGIN);
    }
}
