package br.com.trabalhouna.leagueoflegendshelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.trabalhouna.leagueoflegendshelper.data.DbOperations;
import br.com.trabalhouna.leagueoflegendshelper.data.DbParam;
import br.com.trabalhouna.leagueoflegendshelper.fw.ContentManager;
import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Created by Rafael
 *
 * @since 22/06/2015
 */
public abstract class BaseDao<T extends BaseTO> implements DbOperations<T> {
    private final String SELECT_TAG;
    private final String INSERT_TAG;
    private final String UPDATE_TAG;
    private final String DELETE_TAG;

    protected Context mContext;
    private Class<T> mClazz;

    public BaseDao(Context context, Class<T> mClazz) {
        this.mContext = context;
        this.mClazz = mClazz;
        this.SELECT_TAG = mClazz.getName() + " SELECT";
        this.INSERT_TAG = mClazz.getName() + " INSERT";
        this.UPDATE_TAG = mClazz.getName() + " UPDATE";
        this.DELETE_TAG = mClazz.getName() + " DELETE";
    }

    @Override
    public final int count() {
        return count(new DbParam[0]);
    }

    @Override
    public final int count(DbParam... filters) {
        String query = "SELECT COUNT(1) FROM ".concat(this.mClazz.getSimpleName());
        String whereClause = "";
        String[] whereValues;

        if (filters.length > 0) {
            boolean first = true;
            List<String> values = new ArrayList<>();
            for (DbParam param : filters) {
                whereClause += (first ? "" : " AND ") + param;
                values.add(param.getValue());

                if (first) first = false;
            }

            query = query.concat(" WHERE ").concat(whereClause);
            whereValues = values.toArray(new String[values.size()]);
        } else {
            whereValues = null;
        }

        Cursor cursor = null;

        try {
            cursor = ContentManager.getInstance(mContext).localDatabase.openConnection().rawQuery(query, whereValues);
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(mClazz.getName(), e.getMessage(), e);
        } finally {
            ContentManager.getInstance(mContext).localDatabase.closeConnection();
            if (cursor != null) cursor.close();
        }

        return 0;
    }

    @Override
    public T read(int id) {
        String[] attrs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        T to = null;

        try {
            cursor = ContentManager.getInstance(mContext).localDatabase.openConnection().query(mClazz.getSimpleName()
                    , null, "_id = ?", attrs, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();
            else
                return null;

            if (cursor.getCount() <= 0)
                return null;

            to = this.bindObject(cursor);

        } catch (SQLException ex) {
            Log.d(SELECT_TAG, ex.getMessage(), ex.getCause());
            throw ex;
        } finally {
            if (cursor != null)
                cursor.close();

            ContentManager.getInstance(mContext).localDatabase.closeConnection();
        }
        return to;
    }

    @Override
    public List<T> read(DbParam... filters) {
        Cursor cursor = null;
        List<T> toList = new ArrayList<>();

        try {
            String query = "SELECT * FROM ".concat(mClazz.getSimpleName());
            String whereClause = "";
            String[] whereValues;

            if (filters.length > 0) {
                boolean first = true;
                List<String> values = new ArrayList<>();
                for (DbParam param : filters) {
                    whereClause += (first ? "" : " AND ") + param;
                    values.add(param.getValue());

                    if (first) first = false;
                }

                query = query.concat(" WHERE ").concat(whereClause);
                whereValues = values.toArray(new String[values.size()]);
            } else {
                whereValues = null;
            }


            cursor = ContentManager.getInstance(mContext).localDatabase.openConnection().rawQuery(query, whereValues);

            while (cursor.moveToNext()) toList.add(this.bindObject(cursor));
        } catch (SQLException ex) {
            Log.d(SELECT_TAG, ex.getMessage(), ex.getCause());
            throw ex;
        } finally {
            if (cursor != null)
                cursor.close();

            ContentManager.getInstance(mContext).localDatabase.closeConnection();
        }

        return toList;
    }

    @Override
    public List<T> read() {
        Cursor cursor = null;
        List<T> toList = new ArrayList<>();

        try {
            cursor = ContentManager.getInstance(mContext).localDatabase.openConnection().query(mClazz.getSimpleName()
                    , null, null, null, null, null, null);

            while (cursor.moveToNext()) toList.add(this.bindObject(cursor));
        } catch (SQLException ex) {
            Log.d(SELECT_TAG, ex.getMessage(), ex.getCause());
            throw ex;
        } finally {
            if (cursor != null)
                cursor.close();

            ContentManager.getInstance(mContext).localDatabase.closeConnection();
        }

        return toList;
    }

    @Override
    public final long insert(T to) {
        long result = 0;

        try {
            ContentValues values = setContentValues(to);
            result = ContentManager.getInstance(mContext).localDatabase.openConnection().insert(mClazz.getSimpleName(),
                    null, values);
        } catch (SQLException e) {
            Log.e(INSERT_TAG, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            Log.e(INSERT_TAG, "[ Unexpected Exception ]" + e.getMessage(), e);
            throw e;
        } finally {
            ContentManager.getInstance(mContext).localDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public final long update(T to) {
        long result = 0;

        ContentValues values = this.setContentValues(to);

        String whereClause = "_id=?";
        String[] whereArgs = new String[]{String.valueOf(to.getId())};

        try {
            result = ContentManager.getInstance(mContext).localDatabase.openConnection().update(mClazz.getSimpleName(),
                    values, whereClause, whereArgs);
        } catch (SQLException e) {
            Log.e(UPDATE_TAG, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            Log.e(INSERT_TAG, "[ Unexpected Exception ]" + e.getMessage(), e);
            throw e;
        } finally {
            ContentManager.getInstance(mContext).localDatabase.closeConnection();
        }

        return result;
    }

    @Override
    public final long delete(T to) {
        long result = 0;
        try {
            String whereClause = "_id=?";
            String[] whereArgs = new String[]{String.valueOf(to.getId())};
            result = ContentManager.getInstance(mContext).localDatabase.openConnection().delete(mClazz.getSimpleName(),
                    whereClause, whereArgs);
        } catch (SQLException e) {
            Log.e(DELETE_TAG, e.getMessage() + "\n" + e.getCause());
            throw e;
        } catch (Exception e) {
            Log.e(INSERT_TAG, "[ Unexpected Exception ]" + e.getMessage(), e);
            throw e;
        } finally {
            ContentManager.getInstance(mContext).localDatabase.closeConnection();
        }

        return result;
    }
}
