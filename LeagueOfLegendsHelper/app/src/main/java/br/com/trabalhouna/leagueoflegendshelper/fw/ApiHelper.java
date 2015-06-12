package br.com.trabalhouna.leagueoflegendshelper.fw;

/**
 * Created by Rafael
 *
 * @since 08/06/2015
 */
public final class ApiHelper {

    private static final String API_KEY = "ad9c283f-3ab0-44db-9afa-38ff1df02944";

    private static String serverAddress = "{server}";
    private static final String API_URL_MATCH_HISTORY = "https://br.api.pvp.net/api/lol/{server}/v2.2/matchhistory/";

    private static String AddApiKey(String url) {
        return url + "?api_key=" + API_KEY;
    }

    /**
     * URL Padrão da API da RIOT.
     * Renomear a API o servidor, token name {server},
     * e a versão da api {version}.
     * A Versão varia de acordo com o método.
     */
    public static final String API_URL = "https://br.api.pvp.net/api/lol/{server}/v{version}/";
    public static final String API_URL_SUMMONER = "https://br.api.pvp.net/api/lol/{server}/v1.4/summoner/";
    public static final String API_URL_STATIC_DATA = "https://global.api.pvp.net/api/lol/static-data/{server}/v1.2/";


    public static String getApiUrlMatchHistory(Server server, String summonerID) {
        return AddApiKey((API_URL_MATCH_HISTORY + summonerID).replace(serverAddress, server.getValue()));
    }

    public enum Server{
        BR("br");

        private String value;
        Server(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
}
