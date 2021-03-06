package br.com.trabalhouna.leagueoflegendshelper.to;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class MatchParticipantStatsTO extends BaseTO {

    //Number of assists
    private long assists;

    //Champion level achieved
    private long champLevel;

    //Number of deaths
    private long deaths;

    //Flag indicating if participant got first blood
    private boolean firstBloodKill;

    //Number of kills
    private long kills;

    public long getAssists() {
        return assists;
    }
    public String getAssistsString() {
        return String.valueOf(assists);
    }

    public void setAssists(long assists) {
        this.assists = assists;
    }

    public long getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(long champLevel) {
        this.champLevel = champLevel;
    }

    public long getDeaths() {
        return deaths;
    }
    public String getDeathsString() {
        return String.valueOf(deaths);
    }
    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public boolean getFirstBloodKill() {
        return firstBloodKill;
    }

    public void setFirstBloodKill(boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    public long getKills() {
        return kills;
    }
    public String getKillsString() {
        return String.valueOf(kills);
    }
    public void setKills(long kills) {
        this.kills = kills;
    }
}
