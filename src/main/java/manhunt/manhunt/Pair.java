package manhunt.manhunt;

public class Pair<H, R, S> {
    private H hunters;
    private R runners;
    private S spectators;

    public Pair(H hunters, R runners, S spectators) {
        this.hunters = hunters;
        this.runners = runners;
        this.spectators = spectators;
    }

    public H getHunters() {
        return hunters;
    }

    public void setHunters(H hunters) {
        this.hunters = hunters;
    }

    public R getRunners() {
        return runners;
    }

    public void setRunners(R runners) {
        this.runners = runners;
    }

    public S getSpectators() {
        return spectators;
    }

    public void setSpectators(S spectators) {
        this.spectators = spectators;
    }
}
