package br.com.trabalhouna.leagueoflegendshelper.task;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.ChampionTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.StaticResource;

/**
 * Created by Rafael
 *
 * @since 24/06/2015
 */
public class ChampionTask extends BaseTask<StaticResource<ChampionTO>> {
    public ChampionTask() {
        super(new TypeToken<StaticResource<ChampionTO>>() {
        });
    }

    public void call(Context context, OnResponseListener<StaticResource<ChampionTO>> listener) {
        ApiHelper.Server server = ApiHelper.Server.parse(ContentManager.getInstance(context)
                .defaultSharedPrefsRead(context.getString(R.string.pref_server), "BR"));

        this.call(listener, ApiHelper.getApiUrlStaticResourceChampion(server), MethodType.GET);
    }
}
