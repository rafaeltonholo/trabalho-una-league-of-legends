package br.com.trabalhouna.leagueoflegendshelper.to;

/**
 * Created by Rafael
 *
 * @since 10/06/2015
 */
public class RuneTO extends BaseTO {
    private long runeId;
    private int count;

    public long getRuneId() {
        return runeId;
    }

    public void setRuneId(long runeId) {
        this.runeId = runeId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
