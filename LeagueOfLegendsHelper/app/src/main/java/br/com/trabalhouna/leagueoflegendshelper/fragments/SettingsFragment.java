package br.com.trabalhouna.leagueoflegendshelper.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import br.com.trabalhouna.leagueoflegendshelper.R;

/**
 * Created by Rafael
 *
 * @since 13/06/2015
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
