package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.HashMap;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.adapter.PlayerHistoryAdapter;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.PlayerHistoryTask;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;
import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;


public class MatchHistory extends ActionBarActivity {

    private ListView matchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);

        matchHistory = (ListView) findViewById(R.id.listMatchHistory);

        updateMatchHistory();
    }

    public void updateMatchHistory() {

        PlayerHistoryTask playerHistoryTask = new PlayerHistoryTask();

        final ProgressDialog pd;
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.loadingMatchHistory));


        //TODO: PEGAR SUMMONER ID SALVO
        playerHistoryTask.callPlayerHistoryMatches(new BaseTask.OnResponseListener<PlayerHistoryTO>() {

            @Override
            public void onResponseError(int responseCode) {
                pd.dismiss();
            }

            @Override
            public void onSucess(PlayerHistoryTO model) {
                pd.dismiss();

                if (model != null) {
                    final PlayerHistoryAdapter adapter = new PlayerHistoryAdapter(model, MatchHistory.this);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            matchHistory.setAdapter(adapter);
                        }
                    });
                }
            }

            @Override
            public void onFailure(String error) {
                pd.dismiss();
            }

            @Override
            public void beforeCall() {
                pd.show();
            }

        }, "543602");
    }
}


