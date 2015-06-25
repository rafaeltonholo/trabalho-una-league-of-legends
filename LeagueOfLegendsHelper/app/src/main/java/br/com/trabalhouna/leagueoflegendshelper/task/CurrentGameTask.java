package br.com.trabalhouna.leagueoflegendshelper.task;

import android.content.Context;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.to.CurrentGameInfoTO;

/**
 * Created by Rafael
 *
 * @since 17/06/2015
 */
public class CurrentGameTask extends BaseTask<CurrentGameInfoTO> {
    public CurrentGameTask() {
        super(CurrentGameInfoTO.class);
    }

    public void call(Context context, long summonerId, OnResponseListener<CurrentGameInfoTO> responseListener) {
        ApiHelper.Server server = ApiHelper.Server.parse(ContentManager.getInstance(context)
                .defaultSharedPrefsRead(context.getString(R.string.pref_server), "BR"));

        super.call(responseListener, ApiHelper.getApiUrlCurrentGame(server, summonerId), MethodType.GET);
    }
}
