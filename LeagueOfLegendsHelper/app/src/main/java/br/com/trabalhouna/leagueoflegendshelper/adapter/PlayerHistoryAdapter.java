package br.com.trabalhouna.leagueoflegendshelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchSummaryTO;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;

/**
 * Created by Kelvin on 11/06/2015.
 */
public class PlayerHistoryAdapter extends BaseAdapter {

    private PlayerHistoryTO playerHistory;
    private Context context;

    public PlayerHistoryAdapter(PlayerHistoryTO playerHistory, Context context) {
        this.playerHistory = playerHistory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.playerHistory.getMatches().length;
    }

    @Override
    public Object getItem(int position) {
        return this.playerHistory.getMatches()[position];
    }

    @Override
    public long getItemId(int position) {
        return this.playerHistory.getMatches()[position].getMatchId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchSummaryTO match = this.playerHistory.getMatches()[position];

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.item_match_history, null);

        //Mode
        TextView txtMatchMode = (TextView) v.findViewById(R.id.matchMode);
        txtMatchMode.setText(match.getMatchMode());

        //Duration
        TextView txtMatchDuration = (TextView) v.findViewById(R.id.matchDuration);
        txtMatchDuration.setText(String.valueOf(match.getMatchDuration()));

        //Region
        TextView txtMatchRegion = (TextView) v.findViewById(R.id.matchRegion);
        txtMatchRegion.setText(String.valueOf(match.getRegion()));

        return v;
    }
}
