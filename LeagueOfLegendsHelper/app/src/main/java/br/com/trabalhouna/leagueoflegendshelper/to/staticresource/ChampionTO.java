package br.com.trabalhouna.leagueoflegendshelper.to.staticresource;

import com.google.gson.annotations.SerializedName;

import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Created by Rafael
 *
 * @since 23/06/2015
 */
public class ChampionTO extends BaseTO {
    @SerializedName("id")
    private int championId;
    private String key;
    private String name;
    private String title;

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
