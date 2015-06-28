package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.adapter.PlayerHistoryAdapter;
import br.com.trabalhouna.leagueoflegendshelper.fragments.MatchesNotFoundFragment;
import br.com.trabalhouna.leagueoflegendshelper.fw.Constants;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.PlayerHistoryTask;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;


public class MatchHistoryActivity extends ActionBarActivity {

    private ListView matchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);

        matchHistory = (ListView) findViewById(R.id.listMatchHistory);

        String summonerId = ContentManager.getInstance(MatchHistoryActivity.this).sharedPrefsRead(Constants
                .PREF_USER_ID, "");

        if (!TextUtils.isEmpty(summonerId))
            updateMatchHistory(summonerId);
    }

    public void updateMatchHistory(String summonerId) {

        PlayerHistoryTask playerHistoryTask = new PlayerHistoryTask();

        final ProgressDialog pd;
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.loadingMatchHistory));

        playerHistoryTask.callPlayerHistoryMatches(this, summonerId,
                new BaseTask.OnResponseListener<PlayerHistoryTO>() {

                    @Override
                    public void onResponseError(int responseCode) {
                        pd.dismiss();
                    }

                    @Override
                    public void onSuccess(PlayerHistoryTO object) {
                        pd.dismiss();

                        if (object != null && object.getMatches() != null && object.getMatches().length > 0) {
                            final PlayerHistoryAdapter adapter = new PlayerHistoryAdapter(object,
                                    MatchHistoryActivity.this);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    matchHistory.setAdapter(adapter);
                                }
                            });
                        } else {
                            getFragmentManager().beginTransaction().replace(android.R.id.content, new
                                    MatchesNotFoundFragment()).commit();
                        }
                    }

                    @Override
                    public void onFailure(String error, Throwable e) {
                        pd.dismiss();
                    }

                    @Override
                    public void beforeCall() {
                        pd.show();
                    }

                });
    }

    // TODO Adicionar menu de configurações e opção para verificar times da partida atual do jogador
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_match_history, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        switch (item.getItemId()) {
            case R.id.action_current_match:
                it = new Intent(this, CurrentMatchActivity.class);
                startActivity(it);
                break;
            case R.id.action_settings:
                it = new Intent(this, SettingsActivity.class);
                startActivity(it);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}


