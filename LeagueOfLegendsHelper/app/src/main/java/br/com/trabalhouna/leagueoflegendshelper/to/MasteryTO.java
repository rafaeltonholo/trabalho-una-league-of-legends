package br.com.trabalhouna.leagueoflegendshelper.to;

/**
 * Created by Rafael
 *
 * @since 10/06/2015
 */
public class MasteryTO extends BaseTO {
    private long masteryId;
    private int rank;

    public long getMasteryId() {
        return masteryId;
    }

    public void setMasteryId(long masteryId) {
        this.masteryId = masteryId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
