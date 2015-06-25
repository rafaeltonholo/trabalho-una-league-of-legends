package br.com.trabalhouna.leagueoflegendshelper.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.interfaces.OnAsyncCallbackListener;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.RuneTask;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.MetaDataTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.RuneTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.StaticResource;

/**
 * Created by Rafael
 *
 * @since 23/06/2015
 */
public class RuneDao extends BaseDao<RuneTO> {
    public RuneDao(Context context) {
        super(context, RuneTO.class);
    }

    @Override
    public RuneTO bindObject(Cursor cursor) {
        if (!cursor.moveToFirst()) return null;

        RuneTO to = new RuneTO();

        to.setRuneId(cursor.getInt(cursor.getColumnIndex("runeId")));
        to.setName(cursor.getString(cursor.getColumnIndex("name")));
        to.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        to.setRune(MetaDataTO.parse(cursor.getString(cursor.getColumnIndex("rune"))));

        return to;
    }

    @Override
    public ContentValues setContentValues(RuneTO to) {
        ContentValues values = new ContentValues();

        values.put("runeId", to.getRuneId());
        values.put("name", to.getName());
        values.put("description", to.getDescription());

        if(to.getRune() != null) values.put("rune", to.getRune().toString());

        return values;
    }

    public void populateDb(@NonNull final OnAsyncCallbackListener listener) {
        RuneTask task = new RuneTask();
        task.call(this.mContext, new BaseTask.OnResponseListener<StaticResource<RuneTO>>() {
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
            public void onSucess(StaticResource<RuneTO> object) {
                int count = object.getData().entrySet().size();

                if(object.getData().entrySet().size() <= 0) return;
                if (RuneDao.this.count() == count) {
                    listener.callback();
                    return;
                }

                for (Map.Entry<String, RuneTO> entry : object.getData().entrySet()) {
                    RuneDao.this.insert(entry.getValue());
                }

                listener.callback();
            }

            @Override
            public void onFailure(String error, Throwable e) {
                Log.e(RuneDao.class + " SYNC", error, e);
            }

            @Override
            public void beforeCall() {
            }
        });
    }
}
