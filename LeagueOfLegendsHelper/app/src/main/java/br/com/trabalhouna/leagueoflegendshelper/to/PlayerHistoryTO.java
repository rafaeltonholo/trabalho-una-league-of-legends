package br.com.trabalhouna.leagueoflegendshelper.to;

import java.util.List;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class PlayerHistoryTO extends BaseTO {
    private List<MatchSummaryTO> matches;

    public List<MatchSummaryTO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchSummaryTO> matches) {
        this.matches = matches;
    }
}
