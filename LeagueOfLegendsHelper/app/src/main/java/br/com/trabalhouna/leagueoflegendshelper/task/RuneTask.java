package br.com.trabalhouna.leagueoflegendshelper.task;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.RuneTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.StaticResource;

/**
 * Created by Rafael
 *
 * @since 23/06/2015
 */
public class RuneTask extends BaseTask<StaticResource<RuneTO>> {

    public RuneTask() {
        super(new TypeToken<StaticResource<RuneTO>>() {
        });
    }

    public void call(Context context, OnResponseListener<StaticResource<RuneTO>> listener) {
        ApiHelper.Server server = ApiHelper.Server.parse(ContentManager.getInstance(context)
                .defaultSharedPrefsRead(context.getString(R.string.pref_server), "BR"));

        this.call(listener, ApiHelper.getApiUrlStaticResourceRune(server), MethodType.GET);
    }
}
