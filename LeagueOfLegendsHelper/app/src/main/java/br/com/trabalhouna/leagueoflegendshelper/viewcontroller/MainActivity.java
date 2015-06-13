package br.com.trabalhouna.leagueoflegendshelper.viewcontroller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.HashMap;

import br.com.trabalhouna.leagueoflegendshelper.R;
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

                task.callSummonerInfo(new BaseTask.OnResponseListner<HashMap<String, SummonerTO>>() {
                    @Override
                    public void onResponseError(int responseCode) {
                        switch (responseCode) {
                            case HttpURLConnection.HTTP_BAD_REQUEST:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Bad Request", Toast.LENGTH_LONG).show();
                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_UNAUTHORIZED:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Sem autorização para executar o método", Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_NOT_FOUND:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Invocador não encontrado!", Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case 429:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Rate limit exceeded!", Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Erro do servidor Interno!", Toast.LENGTH_LONG).show();

                                    }
                                });
                                break;
                            case HttpURLConnection.HTTP_UNAVAILABLE:
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Servidor indisponível!", Toast.LENGTH_LONG).show();
                                    }
                                });
                                break;
                        }
                    }

                    @Override
                    public void onSucess(final HashMap<String, SummonerTO> model) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, model.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
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
                }, String.valueOf(mEtxLogin.getText()));

                break;
        }
    }
}
