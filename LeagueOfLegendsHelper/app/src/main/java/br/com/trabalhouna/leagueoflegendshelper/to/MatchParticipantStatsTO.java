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
    private long firstBloodKill;

    //Number of kills
    private long kills;

    public long getAssists() {return assists;}

    public void setAssists(long assists) {this.assists = assists;}

    public long getChampLevel() {return champLevel;}

    public void setChampLevel(long champLevel) {this.champLevel = champLevel;}

    public long getDeaths() {return deaths;}

    public void setDeaths(long deaths) {this.deaths = deaths;}

    public long getFirstBloodKill() {return firstBloodKill;}

    public void setFirstBloodKill(long firstBloodKill) {this.firstBloodKill = firstBloodKill;}

    public long getKills() {return kills;}

    public void setKills(long kills) {this.kills = kills;}
}
