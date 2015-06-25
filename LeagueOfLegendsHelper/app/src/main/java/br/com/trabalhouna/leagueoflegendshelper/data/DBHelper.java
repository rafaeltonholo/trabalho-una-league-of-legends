package br.com.trabalhouna.leagueoflegendshelper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

import br.com.trabalhouna.leagueoflegendshelper.to.SummonerTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.ChampionTO;
import br.com.trabalhouna.leagueoflegendshelper.to.staticresource.RuneTO;

/**
 * Created by Rafael
 *
 * @since 20/06/2015
 */
public final class DbHelper extends SQLiteOpenHelper {
    private static DbHelper sInstace;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "LOLHelper.db3";
    private AtomicInteger mConnections;
    private SQLiteDatabase mDatabase;

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mConnections = new AtomicInteger();
    }

    public static DbHelper getInstance(Context context) {
        synchronized (DbHelper.class) {
            if (sInstace == null) sInstace = new DbHelper(context);

            return sInstace;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openConnection() {
        if (mConnections.getAndIncrement() > 0) return this.mDatabase;

        this.mDatabase = super.getWritableDatabase();

        return this.mDatabase;
    }

    public void closeConnection() {
        if (this.mConnections.decrementAndGet() > 0) return;

        this.mDatabase.close();
        this.mDatabase = null;
    }

    private void createTables(SQLiteDatabase db) {
        String execQuery;
        String query = "CREATE TABLE IF NOT EXISTS %s (%s);";

        String queryColumns = "";

        //region [ SummonerTO Table ]
        queryColumns += "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ";
        queryColumns += "summonerId INTEGER NOT NULL, ";
        queryColumns += "name TEXT NOT NULL, ";
        queryColumns += "profileIconId INTEGER NOT NULL, ";
        queryColumns += "summonerLevel INTEGER NOT NULL, ";
        queryColumns += "revisionDate INTEGER NOT NULL";

        execQuery = String.format(query, SummonerTO.class.getSimpleName(), queryColumns);

        db.execSQL(execQuery);
        //endregion [ SummonerTO Table ]

        //region [ RuneTO Table ]
        queryColumns = "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ";
        queryColumns += "runeId INTEGER NOT NULL, ";
        queryColumns += "name TEXT NOT NULL, ";
        queryColumns += "description TEXT, ";
        queryColumns += "rune TEXT";

        execQuery = String.format(query, RuneTO.class.getSimpleName(), queryColumns);

        db.execSQL(execQuery);
        //endregion [ RuneTO Table ]

        //region [ ChampionTO Table ]
        queryColumns = "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ";
        queryColumns += "championId INTEGER NOT NULL, ";
        queryColumns += "key TEXT, ";
        queryColumns += "name TEXT NOT NULL, ";
        queryColumns += "title TEXT";

        execQuery = String.format(query, ChampionTO.class.getSimpleName(), queryColumns);

        db.execSQL(execQuery);
        //endregion [ ChampionTO Table ]

    }
}
