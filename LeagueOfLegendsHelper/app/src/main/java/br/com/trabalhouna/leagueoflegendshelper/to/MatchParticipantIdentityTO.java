package br.com.trabalhouna.leagueoflegendshelper.to;

/**
 * Created by Kelvin on 14/06/2015.
 */
public class MatchParticipantIdentityTO extends BaseTO {
    private int participantId;
    private PlayerTO player;

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public PlayerTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerTO player) {
        this.player = player;
    }
}
