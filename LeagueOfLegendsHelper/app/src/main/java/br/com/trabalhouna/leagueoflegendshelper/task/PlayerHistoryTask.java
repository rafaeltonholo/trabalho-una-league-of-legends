package br.com.trabalhouna.leagueoflegendshelper.task;

import android.content.Context;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class PlayerHistoryTask extends BaseTask<PlayerHistoryTO> {

    public PlayerHistoryTask() {
        super(PlayerHistoryTO.class);
    }

    public void callPlayerHistoryMatches(Context context, String summonerID,
                                         OnResponseListener<PlayerHistoryTO> responseListner) {

        ApiHelper.Server server = ApiHelper.Server.parse(ContentManager.getInstance(context)
                .defaultSharedPrefsRead(context.getString(R.string.pref_server), "BR"));

        this.call(responseListner, ApiHelper.getApiUrlMatchHistory(server, summonerID),
                MethodType.GET);
    }

}
