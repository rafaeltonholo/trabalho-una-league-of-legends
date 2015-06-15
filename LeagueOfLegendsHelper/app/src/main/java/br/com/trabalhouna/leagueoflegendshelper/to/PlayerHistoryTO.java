package br.com.trabalhouna.leagueoflegendshelper.to;


/**
 * Created by Kelvin on 11/06/2015.
 */
public class PlayerHistoryTO extends BaseTO {
    private MatchSummaryTO[] matches;

    public MatchSummaryTO[] getMatches() {
        return matches;
    }

    public void setMatches(MatchSummaryTO[] matches) {
        this.matches = matches;
    }
}
