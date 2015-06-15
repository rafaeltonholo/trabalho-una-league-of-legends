package br.com.trabalhouna.leagueoflegendshelper.task;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;

/**
 * Created by Rafael
 *
 * @since 11/06/2015
 */
public class SummonerTask extends BaseTask<HashMap<String, SummonerTO>> {
    public SummonerTask() {
        super(new TypeToken<HashMap<String, SummonerTO>>() {
        });
    }

    public void callSummonerInfo(OnResponseListener<HashMap<String, SummonerTO>> responseListner, String summonerName) {
        this.call(responseListner, ApiHelper.getApiUrlSummoner(ApiHelper.Server.BR, "by-name/", summonerName), MethodType.GET);
    }

}
