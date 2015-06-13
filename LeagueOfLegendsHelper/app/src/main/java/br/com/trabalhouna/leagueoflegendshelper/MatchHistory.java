package br.com.trabalhouna.leagueoflegendshelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import br.com.trabalhouna.leagueoflegendshelper.adapter.PlayerHistoryAdapter;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.PlayerHistoryTask;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;


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

        //TODO: PEGAR SUMMONER ID SALVO
        playerHistoryTask.callPlayerHistoryMatches(new MatchHistoryListener(this), "543602");
    }

    public class MatchHistoryListener implements BaseTask.OnResponseListener<PlayerHistoryTO> {

        private ProgressDialog pd;

        public MatchHistoryListener(Context context) {
            pd = new ProgressDialog(context);
            pd.setMessage(getString(R.string.loadingMatchHistory));
        }

        @Override
        public void onResponseError(int responseCode) {
            pd.hide();
        }

        @Override
        public void onSucess(PlayerHistoryTO model) {
            pd.hide();

            if (model != null) {

                PlayerHistoryAdapter adapter = new PlayerHistoryAdapter(model, MatchHistory.this);
                matchHistory.setAdapter(adapter);

            }
        }

        @Override
        public void onFailure(String error) {
            pd.hide();

        }

        @Override
        public void beforeCall() {
            pd.show();
        }
    }
}


