package br.com.trabalhouna.leagueoflegendshelper.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.data.DbParam;
import br.com.trabalhouna.leagueoflegendshelper.data.DbType;
import br.com.trabalhouna.leagueoflegendshelper.interfaces.OnAsyncCallbackListener;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.ChampionTask;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.ChampionTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.StaticResource;

/**
 * Created by Rafael
 *
 * @since 24/06/2015
 */
public class ChampionDao extends BaseDao<ChampionTO> {
    public ChampionDao(Context context) {
        super(context, ChampionTO.class);
    }

    @Override
    public ChampionTO bindObject(Cursor cursor) {
        if (!cursor.moveToFirst()) return null;

        ChampionTO to = new ChampionTO();

        to.setChampionId(cursor.getInt(cursor.getColumnIndex("championId")));
        to.setKey(cursor.getString(cursor.getColumnIndex("key")));
        to.setName(cursor.getString(cursor.getColumnIndex("name")));
        to.setTitle(cursor.getString(cursor.getColumnIndex("title")));

        return to;
    }

    @Override
    public ContentValues setContentValues(ChampionTO to) {
        ContentValues values = new ContentValues();

        values.put("championId", to.getChampionId());
        values.put("key", to.getKey());
        values.put("name", to.getName());
        values.put("title", to.getTitle());

        return values;
    }

    public void populateDb(@NonNull final OnAsyncCallbackListener listener) {
        ChampionTask task = new ChampionTask();
        task.call(this.mContext, new BaseTask.OnResponseListener<StaticResource<ChampionTO>>() {
            @Override
            public void onResponseError(int responseCode) {
                switch (responseCode) {
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "Sem autorização para executar o método", Toast.LENGTH_LONG)
                                        .show();

                            }
                        });
                        break;
                    case 429:
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, mContext.getString(R.string.riot_return_http_rate_limit),
                                        Toast.LENGTH_LONG).show();

                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_INTERNAL_ERROR:
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "Erro do servidor Interno!", Toast.LENGTH_LONG).show();

                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_UNAVAILABLE:
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, mContext.getString(R.string.riot_return_http_unavailable),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onSuccess(StaticResource<ChampionTO> object) {
                if(object == null || object.getData() == null) {
                    listener.callback();
                    return;
                }

                int count = object.getData().entrySet().size();

                if(object.getData().entrySet().size() <= 0) return;
                if (ChampionDao.this.count() == count) {
                    listener.callback();
                    return;
                }

                for (Map.Entry<String, ChampionTO> entry : object.getData().entrySet()) {
                    ChampionDao.this.insert(entry.getValue());
                }

                listener.callback();
            }

            @Override
            public void onFailure(String error, Throwable e) {
                Log.e(ChampionDao.class + " SYNC", error, e);
            }

            @Override
            public void beforeCall() {
            }
        });
    }

    public ChampionTO getChampionInfo(long championId) {
        ChampionTO champ = null;

        List<ChampionTO> champs = this.read(new DbParam().setColumn("championId").setValue(championId).setDbType(DbType
                .INTEGER));

        if(champs.size() > 0) champ = champs.get(0);

        return champ;
    }

}
