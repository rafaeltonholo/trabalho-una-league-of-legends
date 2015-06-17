package br.com.trabalhouna.leagueoflegendshelper.task;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
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

    public void callSummonerInfo(Context context, String summonerName,
                                 OnResponseListener<HashMap<String, SummonerTO>> responseListner) {

        ApiHelper.Server server = ApiHelper.Server.parse(ContentManager.getInstance(context)
                .defaultSharedPrefsRead(context.getString(R.string.pref_server), "BR"));

        this.call(responseListner,
                ApiHelper.getApiUrlSummoner(server, "by-name/", summonerName), MethodType.GET);
    }

}
