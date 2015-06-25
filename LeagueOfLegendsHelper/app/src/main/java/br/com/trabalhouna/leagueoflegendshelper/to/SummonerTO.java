package br.com.trabalhouna.leagueoflegendshelper.to;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rafael
 *
 * @since 10/06/2015
 */
public class SummonerTO extends BaseTO {
    @SerializedName("id")
    private long summonerId;
    private String name;
    private int profileIconId;
    private int summonerLevel;
    private long revisionDate;

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long id) {
        this.summonerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    @Override
    public String toString() {
        return String.format("SummonerName: %s, Level: %d, ID: %d", this.name, this.summonerLevel, this.summonerId);
    }
}
