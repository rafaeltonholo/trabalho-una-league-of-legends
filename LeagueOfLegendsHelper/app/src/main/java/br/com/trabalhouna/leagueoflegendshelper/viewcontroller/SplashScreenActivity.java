package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.dao.RuneDao;

public class SplashScreenActivity extends ActionBarActivity {
    TextView mTxtLoading;
    int mTotalLoad = 1;
    int mLoaded = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mTxtLoading = (TextView)findViewById(R.id.txtLoading);
        RuneDao runeDao = new RuneDao(this);
        runeDao.populateDb();
    }

    private void updateStatus() {
        mTxtLoading.setText(String.format("Carregando %d/%d", mLoaded, mTotalLoad));
    }
}
