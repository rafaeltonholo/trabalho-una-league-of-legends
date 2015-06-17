package br.com.trabalhouna.leagueoflegendshelper.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.fw.ApiHelper;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;

/**
 * Created by Rafael
 *
 * @since 13/06/2015
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        String prefServerKey = getString(R.string.pref_server);

        Preference pref = findPreference(prefServerKey);
        pref.setSummary(ApiHelper.Server.parse(ContentManager
                .getInstance(getActivity().getApplicationContext())
                .defaultSharedPrefsRead(prefServerKey, "BR")).getDescription());

        this.getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);

        if (pref instanceof ListPreference) {
            pref.setSummary(ApiHelper.Server.parse(
                    String.valueOf(((ListPreference) pref).getValue())).getDescription());
        }
    }
}
