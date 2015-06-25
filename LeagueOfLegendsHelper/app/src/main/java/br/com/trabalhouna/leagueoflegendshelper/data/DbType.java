package br.com.trabalhouna.leagueoflegendshelper.data;

/**
 * Created by Rafael
 *
 * @since 22/06/2015
 */
public enum DbType {
    TEXT("TEXT"),
    INTEGER("INTEGER"),
    REAL("REAL"),
    BLOB("BLOB"),
    NUMERIC("NUMERIC"),
    DATE("NUMERIC"),
    DATETIME("NUMERIC");

    private String trueType;

    DbType(String trueType) {
        this.trueType = trueType;
    }

    public String getTrueType() {
        return trueType;
    }
}