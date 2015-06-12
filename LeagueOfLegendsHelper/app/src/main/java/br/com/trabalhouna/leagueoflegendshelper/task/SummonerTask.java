package br.com.trabalhouna.leagueoflegendshelper.task;

import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;

/**
 * Created by Rafael
 *
 * @since 11/06/2015
 */
public class SummonerTask extends BaseTask<SummonerTO> {
    public SummonerTask() {
        super(SummonerTO.class);
    }

    public void callSummonerInfo(OnResponseListener<SummonerTO> responseListner, String summonerName) {
        this.call(responseListner, ApiHelper.API_URL_SUMMONER.replace("{server}", "br") + summonerName, MethodType.POST);
    }

}
