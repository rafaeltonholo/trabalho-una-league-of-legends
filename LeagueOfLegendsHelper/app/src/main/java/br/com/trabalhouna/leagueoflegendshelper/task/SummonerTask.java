package br.com.trabalhouna.leagueoflegendshelper.task;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import br.com.trabalhouna.leagueoflegendshelper.fw.Constant;
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

    public void callSummonerInfo(OnResponseListner<HashMap<String, SummonerTO>> responseListner, String summonerName) {
        this.call(responseListner, Constant.API_URL_SUMMONER.replace("{server}", "br").concat("by-name/").concat(summonerName).concat("?api_key=").concat(Constant.API_KEY), MethodType.GET);
    }

}
