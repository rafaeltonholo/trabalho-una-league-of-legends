package br.com.trabalhouna.leagueoflegendshelper.fw;

import android.content.SharedPreferences;

/**
 * Classe para armazenamento de constantes
 * Created by Rafael
 *
 * @since 14/06/2015
 */
public final class Constants {
    public static final String SHARED_PREFS_NAME = "LOLHelperPrefs";
    public static final String SHARED_PREFS_LOG_WRITE = SharedPreferences.class.getName().concat(".write");
    public static final String SHARED_PREFS_LOG_REMOVE = SharedPreferences.class.getName().concat(".remove");
    public static final String SHARED_PREFS_LOG_READ_ALL = SharedPreferences.class.getName().concat(".readAll");
    public static final String SHARED_PREFS_LOG_READ = SharedPreferences.class.getName().concat(".read");

    public static final String PREF_USER_ID = "USER_ID";
}
