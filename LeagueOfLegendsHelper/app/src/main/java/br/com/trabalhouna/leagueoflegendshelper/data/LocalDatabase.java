package br.com.trabalhouna.leagueoflegendshelper.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Created by Rafael
 *
 * @since 20/06/2015
 */
public final class LocalDatabase {
    private static final int DB_VERSION = 1;
    private static final String DB_PATH = "";
    private AtomicInteger mConnections;
    private SQLiteDatabase mDatabase;

    public LocalDatabase() {
        this.mConnections = new AtomicInteger();
    }

    public LocalDatabase openConnection() {
        if(mConnections.getAndIncrement() > 1) return this;

        this.mDatabase = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null);

        return this;
    }

    public long insert(String table, ContentValues values) {
        return this.mDatabase.insertOrThrow(table, null, values);
    }

    public int update(String table, ContentValues values, String where, String[] whereValues) {
        return this.mDatabase.update(table, values, where, whereValues);
    }

    public int delete(String table, String where, String[] whereValues) {
        return this.mDatabase.delete(table, where, whereValues);
    }

    public Cursor read(String query) {
        return this.mDatabase.rawQuery(query, null);
    }

    public Cursor read(String query, String[] paramsValues) {
        return this.mDatabase.rawQuery(query, paramsValues);
    }


    public void closeConnection() {
        if(this.mConnections.decrementAndGet() > 0) return;

        this.mDatabase.close();
        this.mDatabase = null;
    }
}
