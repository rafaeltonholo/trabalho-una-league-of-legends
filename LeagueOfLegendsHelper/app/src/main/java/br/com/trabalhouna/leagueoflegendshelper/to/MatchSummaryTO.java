package br.com.trabalhouna.leagueoflegendshelper.to;

import java.util.List;

import br.com.trabalhouna.leagueoflegendshelper.fw.Util;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class MatchSummaryTO extends BaseTO {
    private long matchId;
    private long matchCreation;
    private long matchDuration;
    private String matchMode;
    private String matchType;
    private String region;
    private MatchParticipantTO[] participants;
    private MatchParticipantIdentityTO[] participantIdentities;

    public long getMatchCreation() {
        return matchCreation;
    }

    public String getMatchCreationDateString() {
        return Util.convertTime(matchCreation);
    }

    public void setMatchCreation(long matchCreation) {
        this.matchCreation = matchCreation;
    }

    public MatchParticipantIdentityTO[] getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(MatchParticipantIdentityTO[] participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public MatchParticipantTO[] getParticipants() {
        return participants;
    }

    public void setParticipants(MatchParticipantTO[] participants) {
        this.participants = participants;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getMatchDuration() {
        return matchDuration;
    }
    public String getMatchDurationInMinutes(){
        if(matchDuration != 0){
            return String.valueOf(matchDuration / 60).replace(",",":") + "m";
        }
        return "N/A";
    }

    public void setMatchDuration(long matchDuration) {
        this.matchDuration = matchDuration;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
