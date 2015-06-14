package br.com.trabalhouna.leagueoflegendshelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchParticipantIdentityTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchParticipantTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchSummaryTO;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerTO;

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
        MatchParticipantTO participantTO = match.getParticipants()[0];//Sempre retorna somente um item

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_match_history, null);

        //Player Kills
        TextView txtKills = (TextView) view.findViewById(R.id.kills);
        txtKills.setText(participantTO.getStats().getKillsString());

        //Player Deaths
        TextView txtDeaths = (TextView)view.findViewById(R.id.deaths);
        txtDeaths.setText(participantTO.getStats().getDeathsString());

        //Player Assists
        TextView txtAssists = (TextView)view.findViewById(R.id.assists);
        txtAssists.setText(participantTO.getStats().getAssistsString());

        //Match Creation
        TextView txtMatchCreation = (TextView) view.findViewById(R.id.matchCreation);
        txtMatchCreation.setText(match.getMatchCreationDateString());

        //Match Mode
        TextView txtMatchMode = (TextView) view.findViewById(R.id.matchMode);
        txtMatchMode.setText(match.getMatchMode());

        //Match Duration
        TextView txtMatchDuration = (TextView) view.findViewById(R.id.matchDuration);
        txtMatchDuration.setText(match.getMatchDurationInMinutes());

        //Match Region
        TextView txtMatchRegion = (TextView) view.findViewById(R.id.matchRegion);
        txtMatchRegion.setText(String.valueOf(match.getRegion()));

        return view;
    }
}
