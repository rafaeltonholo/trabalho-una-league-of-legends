package br.com.trabalhouna.leagueoflegendshelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.trabalhouna.leagueoflegendshelper.R;
import br.com.trabalhouna.leagueoflegendshelper.to.CurrentGameInfoTO;
import br.com.trabalhouna.leagueoflegendshelper.to.MatchParticipantTO;

/**
 * Created by Rafael
 *
 * @since 19/06/2015
 */
public class MatchParticipantAdapter extends BaseAdapter {
    private Context mContext;
    private List<MatchParticipantTO> mList;

    public MatchParticipantAdapter(Context context, List<MatchParticipantTO> list) {
        this.mContext = context;
        this.mList = list;
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
        ViewHolder viewHolder;

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

        MatchParticipantTO participant = getItem(position);
        //info.getP
//        viewHolder.imgCampeaoIcon
        viewHolder.txtCampeaoNome.setText(Long.toString(participant.getChampionId()));
//        viewHolder.txtCampeaoQtdeJogos
        viewHolder.txtInvocadorNome.setText(participant.getSummonerName());
//        viewHolder.txtInvocadorElo
//        viewHolder.txtInvocadorKillRatio
//        viewHolder.txtInvocadorMaestria
//        viewHolder.txtInvocadorRunas

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
