package br.com.trabalhouna.leagueoflegendshelper.to.staticresource;

/**
 * Created by Rafael
 *
 * @since 23/06/2015
 */
public class MetaDataTO {
    private boolean isRune;
    private String tier;
    private String type;

    public boolean isRune() {
        return isRune;
    }

    public void setIsRune(boolean isRune) {
        this.isRune = isRune;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return Boolean.toString(isRune) + ";" + tier + ";" + type;
    }

    public static MetaDataTO parse(String rune) {
        String[] values = rune.split(";");
        MetaDataTO metaDataTO = new MetaDataTO();
        metaDataTO.isRune = Boolean.parseBoolean(values[0]);
        metaDataTO.tier = values[1];
        metaDataTO.type = values[2];

        return metaDataTO;
    }
}
