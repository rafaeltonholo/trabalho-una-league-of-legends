package br.com.trabalhouna.leagueoflegendshelper.to;

import java.util.List;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class MatchSummaryTO extends BaseTO {
    private long matchId;
    private long matchDuration;
    private String matchMode;
    private String matchType;
    private String region;
    private List<MatchParticipantTO> participants;


    public List<MatchParticipantTO> getParticipants() {return participants;}

    public void setParticipants(List<MatchParticipantTO> participants) {this.participants = participants;}

    public long getMatchId() {return matchId;}

    public void setMatchId(long matchId) {this.matchId = matchId;}

    public long getMatchDuration() {return matchDuration;}

    public void setMatchDuration(long matchDuration) {this.matchDuration = matchDuration;}

    public String getMatchMode() {return matchMode;}

    public void setMatchMode(String matchMode) {this.matchMode = matchMode;}

    public String getMatchType() {return matchType;}

    public void setMatchType(String matchType) {this.matchType = matchType;}

    public String getRegion() {return region;}

    public void setRegion(String region) {this.region = region;}
}
