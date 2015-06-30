package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.adapter.MatchParticipantAdapter;
import br.com.trabalhouna.leagueoflegendshelper.dao.SummonerDao;
import br.com.trabalhouna.leagueoflegendshelper.data.DbParam;
import br.com.trabalhouna.leagueoflegendshelper.data.DbType;
import br.com.trabalhouna.leagueoflegendshelper.fw.Constants;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.fw.Util;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.CurrentGameTask;
import br.com.trabalhouna.leagueoflegendshelper.task.SummonerTask;
import br.com.trabalhouna.leagueoflegendshelper.to.CurrentGameInfoTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchParticipantTO;
import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;

public class CurrentMatchActivity extends ActionBarActivity {
    private ListView mLstCurrentMatchTeamOne;
    private ListView mLstCurrentMatchTeamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_match);
        mLstCurrentMatchTeamOne = (ListView) findViewById(R.id.lstCurrentMatchTeamOne);
        mLstCurrentMatchTeamTwo = (ListView) findViewById(R.id.lstCurrentMatchTeamTwo);
        String summonerId = ContentManager.getInstance(CurrentMatchActivity.this).sharedPrefsRead(Constants
                .PREF_USER_ID, "");

        if (!TextUtils.isEmpty(summonerId))
            updateCurrentMatch(summonerId);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_current_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }

    private void updateCurrentMatch(String summonerId) {
        final SummonerDao summonerDao = new SummonerDao(this);
        final SummonerTask summonerTask = new SummonerTask();
        final ProgressDialog pd;
        pd = new ProgressDialog(this);
        List<SummonerTO> summoners = summonerDao.read(new DbParam().setColumn("summonerId").setValue(summonerId)
                .setDbType(DbType.INTEGER));

        final SummonerTO summoner = summoners.get(0);

        pd.setMessage(String.format(getString(R.string.loadingCurrentMatch), summoner.getName()));
        CurrentGameTask task = new CurrentGameTask();
        task.call(this, summoner.getSummonerId(), new BaseTask.OnResponseListener<CurrentGameInfoTO>() {
            @Override
            public void onResponseError(int responseCode) {
                pd.dismiss();
                switch (responseCode) {
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        CurrentMatchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CurrentMatchActivity.this, "Bad Request",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        CurrentMatchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CurrentMatchActivity.this,
                                        "Sem autorização para executar o método",
                                        Toast.LENGTH_LONG).show();

                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        CurrentMatchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CurrentMatchActivity.this,
                                        "Invocador não encontrado!",
                                        Toast.LENGTH_LONG).show();

                            }
                        });
                        break;
                    case 429:
                        CurrentMatchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CurrentMatchActivity.this,
                                        getString(R.string.riot_return_http_rate_limit),
                                        Toast.LENGTH_LONG).show();

                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_INTERNAL_ERROR:
                        CurrentMatchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CurrentMatchActivity.this,
                                        "Erro do servidor Interno!",
                                        Toast.LENGTH_LONG).show();

                            }
                        });
                        break;
                    case HttpURLConnection.HTTP_UNAVAILABLE:
                        CurrentMatchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CurrentMatchActivity.this,
                                        getString(R.string.riot_return_http_unavailable),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onSuccess(CurrentGameInfoTO object) {
                if (object == null) return;

                String summonerNames = "";

                boolean first = true;
                for (MatchParticipantTO matchParticipant : object.getParticipants()) {
                    summonerNames += (first ? "" : ",") + matchParticipant.getSummonerName();
                    first = false;
                }

                HashMap<String, SummonerTO> summoners = summonerTask.callSync(CurrentMatchActivity.this, summonerNames);
                for (Map.Entry<String, SummonerTO> entry : summoners.entrySet()) {
                    SummonerTO summ = entry.getValue();
                    int count = summonerDao.count(new DbParam().setColumn("summonerId").setValue(summ.getSummonerId())
                            .setDbType(DbType.INTEGER));

                    if (count > 0) continue;

                    summonerDao.insert(summ);
                }

                final MatchParticipantAdapter adapter = new MatchParticipantAdapter(CurrentMatchActivity.this, Arrays
                        .asList(object.getParticipants()).subList(0, 4));

                final MatchParticipantAdapter adapterTwo = new MatchParticipantAdapter(CurrentMatchActivity.this, Arrays
                        .asList(object.getParticipants()).subList(4, 9));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLstCurrentMatchTeamOne.setAdapter(adapter);
                        Util.measureListView(mLstCurrentMatchTeamOne, 0);
                        mLstCurrentMatchTeamTwo.setAdapter(adapterTwo);
                        Util.measureListView(mLstCurrentMatchTeamTwo, 100);
                    }
                });

                pd.dismiss();
            }

            @Override
            public void onFailure(String error, @Nullable Throwable e) {

                pd.dismiss();
            }

            @Override
            public void beforeCall() {
                pd.show();
            }
        });
    }
}
