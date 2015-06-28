package br.com.trabalhouna.leagueoflegendshelper.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.trabalhouna.leagueoflegendshelper.R;

/**
 * Created by Rafael
 *
 * @since 27/06/2015
 */
public class MatchesNotFoundFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.matches_not_found_fragment, container, false);
    }
}
