package br.com.trabalhouna.leagueoflegendshelper.task;

import br.com.trabalhouna.leagueoflegendshelper.fw.Constant;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class PlayerHistoryTask extends BaseTask<PlayerHistoryTO> {

    public PlayerHistoryTask() {
        super(PlayerHistoryTO.class);
    }

    public void callPlayerHistoryMatches(OnResponseListener<PlayerHistoryTO> responseListner, String summonerID) {
        this.call(responseListner, Constant.API_URL_MATCH_HISTORY.replace("{server}", "br") + summonerID, MethodType.GET);
    }

}
