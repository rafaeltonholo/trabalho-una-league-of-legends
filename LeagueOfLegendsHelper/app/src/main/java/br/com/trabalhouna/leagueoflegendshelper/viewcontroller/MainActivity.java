package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.Constants;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.task.BaseTask;
import br.com.trabalhouna.leagueoflegendshelper.task.SummonerTask;
import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText mEtxLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        mEtxLogin = (EditText) findViewById(R.id.etxLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                final ProgressDialog pd = new ProgressDialog(this);
                pd.setMessage("Buscando dados no servidor. Aguarde...");
                pd.setCancelable(false);
                pd.setCanceledOnTouchOutside(false);
                pd.setIndeterminate(true);

                SummonerTask task = new SummonerTask() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.show();
                            }
                        });
                    }

                    @Override
                    protected void onPostExecute(HashMap<String, SummonerTO> summonerTO) {
                        super.onPostExecute(summonerTO);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();
                            }
                        });
                    }
                };

                task.callSummonerInfo(new BaseTask.OnResponseListener<HashMap<String, SummonerTO>>() {
                    @Override
                    public void onResponseError(int responseCode) {
                        switch (responseCode) {
                            case HttpURLConnection.HTTP_BAD_REQUEST:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Bad Request",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_UNAUTHORIZED:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,
                                                "Sem autorização para executar o método",
                                                Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_NOT_FOUND:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Invocador não encontrado!",
                                                Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case 429:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, getString(R.string.riot_return_http_rate_limit),
                                                Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Erro do servidor Interno!",
                                                Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_UNAVAILABLE:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, getString(R.string.riot_return_http_unavailable),
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                                break;
                        }
                    }

                    @Override
                    public void onSucess(final HashMap<String, SummonerTO> object) {
                        Map.Entry<String, SummonerTO> entry = object.entrySet().iterator().next();
                        if (entry != null) {
                            final SummonerTO summoner = entry.getValue();

                            boolean success = ContentManager.getInstance(MainActivity.this)
                                    .sharedPrefsWrite(Constants.PREF_USER_ID,
                                            Long.toString(summoner.getId()));

                            if (success) {
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, summoner.toString(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                                Intent i = new Intent(MainActivity.this, MatchHistoryActivity.class);
                                startActivity(i);
                            }
                        }
                    }

                    @Override
                    public void onFailure(final String error) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void beforeCall() {

                    }
                }, String.valueOf(mEtxLogin.getText()));

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent it = new Intent(this, SettingsActivity.class);
                startActivity(it);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
