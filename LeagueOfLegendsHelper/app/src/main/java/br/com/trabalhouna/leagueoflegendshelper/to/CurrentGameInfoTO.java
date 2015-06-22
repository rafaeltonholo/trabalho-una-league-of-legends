package br.com.trabalhouna.leagueoflegendshelper.to;

/**
 * Created by Rafael
 *
 * @since 10/06/2015
 */
public class CurrentGameInfoTO extends BaseTO {
    private MatchBannedChampionTO[] bannedChampions;
    private long gameId;
    private long gameLength;
    private String gameMode;
    private long gameQueueConfigId;
    private long gameStartTime;
    private String gameType;
    private long mapId;
    private MatchParticipantTO[] participants;
    private String platformId;

    public MatchBannedChampionTO[] getBannedChampions() {
        return bannedChampions;
    }

    public void setBannedChampions(MatchBannedChampionTO[] bannedChampions) {
        this.bannedChampions = bannedChampions;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    public void setGameQueueConfigId(long gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public MatchParticipantTO[] getParticipants() {
        return participants;
    }

    public void setParticipants(MatchParticipantTO[] participants) {
        this.participants = participants;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}
