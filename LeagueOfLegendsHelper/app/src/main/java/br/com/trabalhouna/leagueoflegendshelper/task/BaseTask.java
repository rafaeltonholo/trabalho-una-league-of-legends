package br.com.trabalhouna.leagueoflegendshelper.task;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.trabalhouna.leagueoflegendshelper.fw.Util;
import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Classe base de Task. para fazer chamadas assincronas via webservice.
 * Created by Rafael
 *
 * @since 11/06/2015
 */
public abstract class BaseTask<T extends BaseTO> extends AsyncTask<String, Void, T> {
    private Class<T> mClazz;
    private OnResponseListner<T> mResponseListner;

    public interface OnResponseListner<T> {
        /**
         * Método para tratar erro de resposta do servidor.
         * Será muito utilizado devido ao retorno do serviço REST da API da RIOT
         * @param responseCode -
         */
        void onResponseError(int responseCode);

        /**
         * Caso o código de resposta do servidor seja OK(200),
         * retorna o modelo já parseado.
         * @param model - Modelo parseado do JSON de retorno
         */
        void onSucess(T model);

        /**
         * Caso haja algum erro por exceção, deverá ser tratado a forma de exibição aqui
         * @param error - mensagem da exceção
         */
        void onFailure(String error);
    }

    public enum MethodType {
        POST,
        GET;

        public static MethodType getType(String name) {
            if (name.toLowerCase().equals("POST")) {
                return POST;
            } else if (name.toLowerCase().equals("GET")) {
                return GET;
            } else {
                return null;
            }
        }
    }

    public BaseTask(Class<T> clazz) {
        this.mClazz = clazz;
    }

    /**
     * @param responseListner - listner para retorno do webservice.
     * @param url             - URL que será utilizada
     * @param methodType      - Tipo de Chamada {POST, GET}
     */
    public final void call(OnResponseListner<T> responseListner, String url, MethodType methodType) {
        this.mResponseListner = responseListner;
        this.execute(url, methodType.toString());
    }

    @Override
    protected T doInBackground(String... params) {
        T model = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(params[1]);
            http.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            int responseCode = http.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                InputStream i = new BufferedInputStream(http.getInputStream());
                String result = Util.readString(i);

                model = T.createByJson(result, mClazz);
            } else {
                if (this.mResponseListner != null) this.mResponseListner.onResponseError(responseCode);
            }
        } catch (Exception e) {
            model = null;
            if (this.mResponseListner != null) this.mResponseListner.onFailure(e.getMessage());
        }

        if (this.mResponseListner != null) this.mResponseListner.onSucess(model);

        return model;
    }
}
