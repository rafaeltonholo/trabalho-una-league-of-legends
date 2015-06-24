package br.com.trabalhouna.leagueoflegendshelper.to.staticresource;

import com.google.gson.annotations.SerializedName;

import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Created by Rafael
 *
 * @since 23/06/2015
 */
public class RuneTO extends BaseTO {
    @SerializedName("id")
    private int runeId;
    private String name;
    private String description;
    private MetaDataTO rune;

    public int getRuneId() {
        return runeId;
    }

    public void setRuneId(int runeId) {
        this.runeId = runeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MetaDataTO getRune() {
        return rune;
    }

    public void setRune(MetaDataTO rune) {
        this.rune = rune;
    }
}
