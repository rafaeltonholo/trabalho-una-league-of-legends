package br.com.trabalhouna.leagueoflegendshelper.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.dao.ChampionDao;
import br.com.trabalhouna.leagueoflegendshelper.dao.RuneDao;
import br.com.trabalhouna.leagueoflegendshelper.data.DbParam;
import br.com.trabalhouna.leagueoflegendshelper.fw.Util;
import br.com.trabalhouna.leagueoflegendshelper.to.CurrentGameInfoTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchParticipantTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchRuneTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.ChampionTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.RuneTO;

/**
 * Created by Rafael
 *
 * @since 19/06/2015
 */
public class MatchParticipantAdapter extends BaseAdapter {
    private Context mContext;
    private List<MatchParticipantTO> mList;
    private RuneDao mRuneDao;

    public MatchParticipantAdapter(Context context, List<MatchParticipantTO> list) {
        this.mContext = context;
        this.mList = list;
        this.mRuneDao = new RuneDao(context);
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public MatchParticipantTO getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mList.get(position).getSummonerId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.actual_match_list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.imgCampeaoIcon = (ImageView) convertView.findViewById(R.id.imgCampeaoIcon);
            viewHolder.txtCampeaoNome = (TextView) convertView.findViewById(R.id.txtCampeaoNome);
            viewHolder.txtCampeaoQtdeJogos = (TextView) convertView.findViewById(R.id.txtCampeaoQtdeJogos);
            viewHolder.txtInvocadorNome = (TextView) convertView.findViewById(R.id.txtInvocadorNome);
            viewHolder.txtInvocadorElo = (TextView) convertView.findViewById(R.id.txtInvocadorElo);
            viewHolder.txtInvocadorKillRatio = (TextView) convertView.findViewById(R.id.txtInvocadorKillRatio);
            viewHolder.txtInvocadorMaestria = (TextView) convertView.findViewById(R.id.txtInvocadorMaestria);
            viewHolder.txtInvocadorRunas = (TextView) convertView.findViewById(R.id.txtInvocadorRunas);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final MatchParticipantTO participant = getItem(position);

        new AsyncTask<Object, Void, ChampionTO>() {
            final ChampionDao championDao = new ChampionDao(mContext);

            @Override
            protected ChampionTO doInBackground(Object... params) {
                ChampionTO champ = championDao.getChampionInfo(participant.getChampionId());
                return champ;
            }

            @Override
            protected void onPostExecute(ChampionTO champ) {
                super.onPostExecute(champ);
                if (champ != null) {
                    viewHolder.imgCampeaoIcon.setImageDrawable(Util.getImageFromAssets(mContext, "champions_icons/" +
                            champ.getKey()
                            + ".png"));

                    viewHolder.txtCampeaoNome.setText(champ.getName());
                }
            }
        }.execute();

        viewHolder.txtInvocadorNome.setText(participant.getSummonerName());

        MatchRuneTO[] runes = participant.getRunes();
        if(runes == null || runes.length == 0) viewHolder.txtInvocadorRunas.setText("Sem Runas");
        else {
            StringBuilder sbRunes = new StringBuilder();

            for (MatchRuneTO entry : runes) {
                List<RuneTO> runeList = mRuneDao.read(new DbParam().setColumn("runeId").setValue(entry.getRuneId()));
                if(runeList == null || runeList.isEmpty()) continue;

                sbRunes.append(entry.getCount()).append("x ").append(runeList.get(0).getDescription()).append("\n");
            }

            viewHolder.txtInvocadorRunas.setText(sbRunes.toString());
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imgCampeaoIcon;
        TextView txtCampeaoNome;
        TextView txtCampeaoQtdeJogos;
        TextView txtInvocadorNome;
        TextView txtInvocadorElo;
        TextView txtInvocadorKillRatio;
        TextView txtInvocadorMaestria;
        TextView txtInvocadorRunas;
    }
}
