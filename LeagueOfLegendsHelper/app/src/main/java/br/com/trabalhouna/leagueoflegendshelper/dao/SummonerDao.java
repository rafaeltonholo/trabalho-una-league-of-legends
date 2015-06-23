package br.com.trabalhouna.leagueoflegendshelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;

/**
 * Created by Rafael
 *
 * @since 22/06/2015
 */
public class SummonerDao extends BaseDao<SummonerTO> {
    public SummonerDao(Context context) {
        super(context, SummonerTO.class);
    }

    @Override
    public SummonerTO bindObject(Cursor cursor) {
        if (!cursor.moveToFirst()) return null;

        SummonerTO to = new SummonerTO();

        to.setSummonerId(cursor.getLong(cursor.getColumnIndex("summonerId")));
        to.setName(cursor.getString(cursor.getColumnIndex("name")));
        to.setProfileIconId(cursor.getInt(cursor.getColumnIndex("profileIconId")));
        to.setSummonerLevel(cursor.getInt(cursor.getColumnIndex("summonerLevel")));
        to.setRevisionDate(cursor.getLong(cursor.getColumnIndex("revisionDate")));

        return to;
    }

    @Override
    public ContentValues setContentValues(SummonerTO to) {
        ContentValues values = new ContentValues();

        values.put("summonerId", to.getSummonerId());
        values.put("name", to.getName());
        values.put("profileIconId", to.getProfileIconId());
        values.put("summonerLevel", to.getSummonerLevel());
        values.put("revisionDate", to.getRevisionDate());

        return values;
    }
}
