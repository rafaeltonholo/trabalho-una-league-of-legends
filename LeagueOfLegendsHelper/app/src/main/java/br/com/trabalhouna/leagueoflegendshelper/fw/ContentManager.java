package br.com.trabalhouna.leagueoflegendshelper.fw;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

/**
 * Gerenciado de Conteudo que será armazenado no dispositivo.
 * Created by Rafael
 *
 * @since 13/06/2015
 */
public final class ContentManager {

    private static ContentManager sInstance;
    private SharedPreferences mSharedPrefs;

    public ContentManager(Context context) {
        this.mSharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static ContentManager getInstance(Context context) {
        if(sInstance == null) {
            synchronized (ContentManager.class) {
                sInstance = new ContentManager(context);
            }
        }

        return sInstance;
    }

    public Map<String, ?> sharedPrefsReadAll() {
        Map<String, ?> prefs = null;

        try {
            prefs = this.mSharedPrefs.getAll();
        } catch (Exception e) {
            prefs = null;
            Log.e(Constants.SHARED_PREFS_LOG_READ_ALL, e.getLocalizedMessage(), e);
        }

        return prefs;
    }

    public Boolean sharedPrefsReadBoolean(String key,
                                      Boolean defaultValue) {
        Boolean value = null;

        try {
            value = this.mSharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    public boolean sharedPrefsRead(String key, boolean defaultValue) {
        boolean value = false;

        try {
            value = this.mSharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    public String sharedPrefsRead(String key, String defaultValue) {
        String value = null;

        try {
            value = this.mSharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    public long sharedPrefsRead(String key, long defaultValue) {
        long value = Long.MIN_VALUE;

        try {
            value = this.mSharedPrefs.getLong(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    public int sharedPrefsRead(String key, int defaultValue) {
        int value = Integer.MIN_VALUE;

        try {
            value = this.mSharedPrefs.getInt(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e(Constants.SHARED_PREFS_LOG_READ, e.getLocalizedMessage(), e);
        }

        return value;
    }

    public boolean sharedPrefsWrite(String key, boolean value) {
        boolean sucess = false;

        try {
            SharedPreferences.Editor editor = this.mSharedPrefs.edit();

            editor.putBoolean(key, value);
            editor.apply();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public boolean sharedPrefsWrite(String key, String value) {
        boolean sucess = false;

        try {
            SharedPreferences.Editor editor = this.mSharedPrefs.edit();

            editor.putString(key, value);
            editor.apply();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public boolean sharedPrefsWrite(String key, int value) {
        boolean sucess = false;

        try {
            SharedPreferences.Editor editor = this.mSharedPrefs.edit();

            editor.putInt(key, value);
            editor.apply();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public boolean sharedPrefsWrite(String key, long value) {
        boolean sucess = false;

        try {
            SharedPreferences.Editor editor = this.mSharedPrefs.edit();

            editor.putLong(key, value);
            editor.apply();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e(Constants.SHARED_PREFS_LOG_WRITE, e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public boolean sharedPrefsRemove(String key) {
        boolean sucess = false;

        try {
            SharedPreferences.Editor editor = this.mSharedPrefs.edit();

            editor.remove(key);
            editor.apply();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e(Constants.SHARED_PREFS_LOG_REMOVE, e.getLocalizedMessage(), e);
        }

        return sucess;
    }
}
