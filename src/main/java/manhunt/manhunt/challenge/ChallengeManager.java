package manhunt.manhunt.challenge;

public class ChallengeManager {
    private static ChallengeManager instance;
    public static ChallengeManager getInstance() {
        if(instance == null){
            instance = new ChallengeManager();
        }
        return instance;
    }
    private ChallengeManager() {
    }

    private Challenge activeChallenge;

    public void init(){
        ChallengeExecutor.execute(getActiveChallenge());
    }


    public Challenge getActiveChallenge() {
        if(activeChallenge == null){
            this.activeChallenge = Challenge.ENDER_DRAGON;
        }
        return activeChallenge;
    }

    public void setActiveChallenge(Challenge activeChallenge) {
        this.activeChallenge = activeChallenge;
    }
}
