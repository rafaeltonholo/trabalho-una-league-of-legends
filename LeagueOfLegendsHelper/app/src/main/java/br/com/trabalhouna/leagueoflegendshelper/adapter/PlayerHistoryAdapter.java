package br.com.trabalhouna.leagueoflegendshelper.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.dao.ChampionDao;
import br.com.trabalhouna.leagueoflegendshelper.fw.Util;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchParticipantTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchSummaryTO;
import br.com.trabalhouna.leagueoflegendshelper.to.PlayerHistoryTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.ChampionTO;

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
        final MatchParticipantTO participantTO = match.getParticipants()[0];//Sempre retorna somente um item

        if (convertView == null) {
            // TODO Trocar para ViewHolder Pattern
            convertView = LayoutInflater.from(context).inflate(R.layout.item_match_history, null);
        }

        final ImageView imgCampeaoIcon = (ImageView) convertView.findViewById(R.id.imgCampeaoIcon);
        final TextView txtCampeaoNome = (TextView) convertView.findViewById(R.id.txtCampeaoNome);

        new AsyncTask<Object, Void, ChampionTO>() {
            final ChampionDao championDao = new ChampionDao(context);

            @Override
            protected ChampionTO doInBackground(Object... params) {
                ChampionTO champ = championDao.getChampionInfo(participantTO.getChampionId());
                return champ;
            }

            @Override
            protected void onPostExecute(ChampionTO champ) {
                super.onPostExecute(champ);
                if (champ != null) {
                    imgCampeaoIcon.setImageDrawable(Util.getImageFromAssets(context, "champions_icons/" + champ.getKey()
                            + ".png"));

                    txtCampeaoNome.setText(champ.getName());
                }
            }
        }.execute();


        //Player Kills
        TextView txtKills = (TextView) convertView.findViewById(R.id.kills);
        txtKills.setText(participantTO.getStats().getKillsString());

        //Player Deaths
        TextView txtDeaths = (TextView) convertView.findViewById(R.id.deaths);
        txtDeaths.setText(participantTO.getStats().getDeathsString());

        //Player Assists
        TextView txtAssists = (TextView) convertView.findViewById(R.id.assists);
        txtAssists.setText(participantTO.getStats().getAssistsString());

        //Match Creation
        TextView txtMatchCreation = (TextView) convertView.findViewById(R.id.matchCreation);
        txtMatchCreation.setText(match.getMatchCreationDateString());

        //Match Mode
        TextView txtMatchMode = (TextView) convertView.findViewById(R.id.matchMode);
        txtMatchMode.setText(match.getMatchMode());

        //Match Duration
        TextView txtMatchDuration = (TextView) convertView.findViewById(R.id.matchDuration);
        txtMatchDuration.setText(match.getMatchDurationInMinutes());

        //Match Region
        TextView txtMatchRegion = (TextView) convertView.findViewById(R.id.matchRegion);
        txtMatchRegion.setText(String.valueOf(match.getRegion()));

        return convertView;
    }
}
