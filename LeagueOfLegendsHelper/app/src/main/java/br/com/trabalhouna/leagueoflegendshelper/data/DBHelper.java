package br.com.trabalhouna.leagueoflegendshelper.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Created by Rafael
 *
 * @since 20/06/2015
 */
public final class DBHelper extends SQLiteOpenHelper {
    private static DBHelper sInstace;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "";
    private AtomicInteger mConnections;
    private SQLiteDatabase mDatabase;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mConnections = new AtomicInteger();
    }

    public static DBHelper getInstance(Context context) {
        synchronized (DBHelper.class) {
            if(sInstace == null) sInstace = new DBHelper(context);

            return sInstace;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openConnection() {
        if(mConnections.getAndIncrement() > 0) return this.mDatabase;

        this.mDatabase = this.getWritableDatabase();

        return this.mDatabase;
    }

    public void closeConnection() {
        if(this.mConnections.decrementAndGet() >= 0) return;

        this.mDatabase.close();
        this.mDatabase = null;
    }

    private void createTables() {
        String query = "CREATE TABLE IF NOT EXISTS %s (%s);";

        String queryColumns = "";


    }
}
