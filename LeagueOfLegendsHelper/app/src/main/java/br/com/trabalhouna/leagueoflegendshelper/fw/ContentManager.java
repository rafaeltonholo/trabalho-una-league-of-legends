package br.com.trabalhouna.leagueoflegendshelper.fw;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;

import br.com.trabalhouna.leagueoflegendshelper.data.LocalDatabase;

/**
 * Gerenciador de conteudo que gerenciará o que será armazenado no dispositivo.
 * Created by Rafael
 *
 * @since 13/06/2015
 */
public final class ContentManager {

    private static ContentManager sInstance;
    private SharedPreferences mSharedPrefs;
    private SharedPreferences mDefaultSharedPrefs;
    public LocalDatabase localDatabase;

    public ContentManager(Context context) {
        this.mSharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        this.mDefaultSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.localDatabase = new LocalDatabase();
    }

    public static ContentManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (ContentManager.class) {
                sInstance = new ContentManager(context);
            }
        }

        return sInstance;
    }

    /**
     * Retorna toda shared preference.
     *
     * @return Map contendo chave valor
     */
    public Map<String, ?> sharedPrefsReadAll() {
        return this.sharedPrefsReadAll(this.mSharedPrefs);
    }

    /**
     * Retorna toda default shared preference.
     *
     * @return Map contendo chave valor
     */
    public Map<String, ?> defaultSharedPrefsReadAll() {
        return this.sharedPrefsReadAll(this.mDefaultSharedPrefs);
    }

    private Map<String, ?> sharedPrefsReadAll(SharedPreferences sharedPrefs) {
        Map<String, ?> prefs = null;

        try {
            prefs = sharedPrefs.getAll();
        } catch (Exception e) {
            prefs = null;
            Log.e(Constants.SHARED_PREFS_LOG_READ_ALL, e.getLocalizedMessage(), e);
        }

        return prefs;
    }

    /**
     * Solicita ao Default Shared Preference o valor booleano de um item em sua lista a partir de
     * uma key
     *
     * @param key          - key do elemento solicitado
     * @param defaultValue - valor default caso não haja
     * @return Elemento solicitado ou valor default
     */
    public boolean sharedPrefsRead(String key, boolean defaultValue) {
        return prefsRead(this.mSharedPrefs, key, defaultValue);
    }

    /**
     * Solicita ao Default Shared Preference o valor booleano de um item em sua lista a partir de
     * uma key
     *
     * @param key          - key do elemento solicitado
     * @param defaultValue - valor default caso não haja
     * @return Elemento solicitado ou valor default
     */
    public boolean defaultSharedPrefsRead(String key, boolean defaultValue) {
        return prefsRead(this.mDefaultSharedPrefs, key, defaultValue);
    }

    private boolean prefsRead(SharedPreferences sharedPrefs, String key, boolean defaultValue) {
        boolean value;

        try {
            value = sharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    /**
     * Solicita ao SharedPreference um elemento do tipo String
     *
     * @param key          - Chave do elemento desejado
     * @param defaultValue - Valor default caso não exista elemento
     * @return Elemento solicitado ou valor default
     */
    public String sharedPrefsRead(String key, String defaultValue) {
        return this.prefsRead(this.mSharedPrefs, key, defaultValue);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo String
     *
     * @param key          - Chave do elemento desejado
     * @param defaultValue - Valor default caso não exista elemento
     * @return Elemento solicitado ou valor default
     */
    public String defaultSharedPrefsRead(String key, String defaultValue) {
        return this.prefsRead(this.mDefaultSharedPrefs, key, defaultValue);
    }

    private String prefsRead(SharedPreferences sharedPrefs, String key, String defaultValue) {
        String value;

        try {
            value = sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    /**
     * Solicita ao SharedPreference um elemento do tipo Long
     *
     * @param key          - Chave do elemento desejado
     * @param defaultValue - Valor default caso não exista elemento
     * @return Elemento solicitado ou valor default
     */
    public long sharedPrefsRead(String key, long defaultValue) {
        return prefsRead(this.mSharedPrefs, key, defaultValue);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo Long
     *
     * @param key          - Chave do elemento desejado
     * @param defaultValue - Valor default caso não exista elemento
     * @return Elemento solicitado ou valor default
     */
    public long defaultSharedPrefsRead(String key, long defaultValue) {
        return prefsRead(this.mDefaultSharedPrefs, key, defaultValue);
    }

    private long prefsRead(SharedPreferences sharedPrefs, String key, long defaultValue) {
        long value;

        try {
            value = sharedPrefs.getLong(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    /**
     * Solicita ao SharedPreference um elemento do tipo Integer
     *
     * @param key          - Chave do elemento desejado
     * @param defaultValue - Valor default caso não exista elemento
     * @return Elemento solicitado ou valor default
     */
    public int sharedPrefsRead(String key, int defaultValue) {
        return prefsRead(this.mSharedPrefs, key, defaultValue);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo Integer
     *
     * @param key          - Chave do elemento desejado
     * @param defaultValue - Valor default caso não exista elemento
     * @return Elemento solicitado ou valor default
     */
    public int defaultSharedPrefsRead(String key, int defaultValue) {
        return prefsRead(this.mDefaultSharedPrefs, key, defaultValue);
    }

    private int prefsRead(SharedPreferences sharedPrefs, String key, int defaultValue) {
        int value;

        try {
            value = sharedPrefs.getInt(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    /**
     * Escreve no SharedPreference um elemento do tipo Boolean
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean sharedPrefsWrite(String key, boolean value) {
        return prefsWrite(this.mSharedPrefs, key, value);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo Boolean
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean defaultSharedPrefsWrite(String key, boolean value) {
        return prefsWrite(this.mDefaultSharedPrefs, key, value);
    }

    private boolean prefsWrite(SharedPreferences sharedPreferences, String key, boolean value) {
        boolean success;

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean(key, value);
            editor.apply();

            success = true;
        } catch (Exception e) {
            success = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return success;
    }

    /**
     * Escreve no SharedPreference um elemento do tipo String
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean sharedPrefsWrite(String key, String value) {
        return prefsWrite(this.mSharedPrefs, key, value);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo String
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean defaultSharedPrefsWrite(String key, String value) {
        return prefsWrite(this.mDefaultSharedPrefs, key, value);
    }

    private boolean prefsWrite(SharedPreferences sharedPreferences, String key, String value) {
        boolean success;

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(key, value);
            editor.apply();

            success = true;
        } catch (Exception e) {
            success = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return success;
    }

    /**
     * Escreve no SharedPreference um elemento do tipo Integer
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean sharedPrefsWrite(String key, int value) {
        return prefsWrite(this.mSharedPrefs, key, value);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo Integer
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean defaultSharedPrefsWrite(String key, int value) {
        return prefsWrite(this.mDefaultSharedPrefs, key, value);
    }

    public boolean prefsWrite(SharedPreferences sharedPreferences, String key, int value) {
        boolean success;

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(key, value);
            editor.apply();

            success = true;
        } catch (Exception e) {
            success = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return success;
    }

    /**
     * Escreve no SharedPreference um elemento do tipo Integer
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean sharedPrefsWrite(String key, long value) {
        return prefsWrite(this.mSharedPrefs, key, value);
    }

    /**
     * Solicita ao DefaultSharedPreference um elemento do tipo Integer
     *
     * @param key   - Chave do elemento
     * @param value - Valor do elemento
     * @return Se teve successo na escrita do elemento
     */
    public boolean defaultSharedPrefsWrite(String key, long value) {
        return prefsWrite(this.mDefaultSharedPrefs, key, value);
    }

    public boolean prefsWrite(SharedPreferences sharedPreferences, String key, long value) {
        boolean success = false;

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putLong(key, value);
            editor.apply();

            success = true;
        } catch (Exception e) {
            success = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return success;
    }

    /**
     * Remove um elemento do SharedPreference
     * @param key - Chave do elemento removido
     * @return Retorna se obteve sucesso na remorção do elemento
     */
    public boolean sharedPrefesRemove(String key) {
        return this.prefsRemove(this.mSharedPrefs, key);
    }

    /**
     * Remove um elemento do DefaultSharedPreference
     * @param key - Chave do elemento removido
     * @return Retorna se obteve sucesso na remorção do elemento
     */
    public boolean defaultSharedPrefs(String key) {
        return this.prefsRemove(this.mDefaultSharedPrefs, key);
    }

    private boolean prefsRemove(SharedPreferences sharedPreferences, String key) {
        boolean success = false;

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.remove(key);
            editor.apply();

            success = true;
        } catch (Exception e) {
            success = false;
            Log.e(Constants.SHARED_PREFS_LOG_REMOVE, e.getLocalizedMessage(), e);
        }

        return success;
    }
}
