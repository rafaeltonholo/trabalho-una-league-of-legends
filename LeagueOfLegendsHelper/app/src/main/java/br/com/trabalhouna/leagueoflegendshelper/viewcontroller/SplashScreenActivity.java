package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.dao.ChampionDao;
import br.com.trabalhouna.leagueoflegendshelper.dao.RuneDao;
import br.com.trabalhouna.leagueoflegendshelper.interfaces.OnAsyncCallbackListener;

public class SplashScreenActivity extends ActionBarActivity {
    TextView mTxtLoading;
    int mTotalLoad = 2;
    int mLoaded = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mTxtLoading = (TextView)findViewById(R.id.txtLoading);
        updateStatus();
        RuneDao runeDao = new RuneDao(this);

        OnAsyncCallbackListener callback = new OnAsyncCallbackListener() {
            @Override
            public void callback() {
                mLoaded++;
                SplashScreenActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateStatus();
                    }
                });
            }
        };

        runeDao.populateDb(callback);

        new ChampionDao(this).populateDb(callback);

    }

    private void updateStatus() {
        mTxtLoading.setText(String.format("Carregando %d/%d", mLoaded, mTotalLoad));
        if(mLoaded == mTotalLoad) {
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }
}
