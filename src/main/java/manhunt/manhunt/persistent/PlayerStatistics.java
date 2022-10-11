package manhunt.manhunt.persistent;

public class PlayerStatistics {
    private int kills;
    private int deaths;
    private int wins;
    private int losses;

    public PlayerStatistics(int kills, int deaths, int wins, int losses) {
        this.kills = kills;
        this.deaths = deaths;
        this.wins = wins;
        this.losses = losses;
    }

    public int getKdRatio() {
        return kills / deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
