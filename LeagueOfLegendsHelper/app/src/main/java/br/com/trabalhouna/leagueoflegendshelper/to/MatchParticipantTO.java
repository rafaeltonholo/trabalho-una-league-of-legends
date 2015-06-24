package br.com.trabalhouna.leagueoflegendshelper.to;

/**
 * Created by Rafael
 *
 * @since 10/06/2015
 */
public class MatchParticipantTO extends BaseTO {
    private boolean bot;
    private long championId;
    private MasteryTO[] masteries;
    private long profileIconId;
    private MatchRuneTO[] runes;
    private long spell1Id;
    private long spell2Id;
    private long summonerId;
    private String summonerName;
    private long teamId;
    private MatchParticipantStatsTO stats;


    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public MasteryTO[] getMasteries() {
        return masteries;
    }

    public void setMasteries(MasteryTO[] masteries) {
        this.masteries = masteries;
    }

    public long getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(long profileIconId) {
        this.profileIconId = profileIconId;
    }

    public MatchRuneTO[] getRunes() {
        return runes;
    }

    public void setRunes(MatchRuneTO[] runes) {
        this.runes = runes;
    }

    public long getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(long spell1Id) {
        this.spell1Id = spell1Id;
    }

    public long getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(long spell2Id) {
        this.spell2Id = spell2Id;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public MatchParticipantStatsTO getStats() {
        return stats;
    }

    public void setStats(MatchParticipantStatsTO stats) {
        this.stats = stats;
    }
}
