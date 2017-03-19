package com.bernardino.frustratelife.persistence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bernardino.frustratelife.Constants;
import com.bernardino.frustratelife.persistence.DatabaseHelper;
import com.bernardino.frustratelife.persistence.bean.Frustration;
import com.bernardino.frustratelife.persistence.table.TbFrustration;
import com.bernardino.frustratelife.persistence.table.TbUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 14/03/2017.
 */

public class DaoFrustration {
    public static void insertFrustration(Context context, String title, String description, String whatToDo) throws Exception {
        Log.i(Constants.TAG_LOG, "insertFrustration");

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = dbHelper.getReadableDatabase();

            ContentValues frustrationInsert = new ContentValues();
            frustrationInsert.put(TbFrustration.COL_TITLE.getName(), title);
            frustrationInsert.put(TbFrustration.COL_DESCRIPTION.getName(), description);
            frustrationInsert.put(TbFrustration.COL_WHAT_TO_DO.getName(), whatToDo);
            db.insert(TbFrustration.TABLE_NAME.getName(), null, frustrationInsert);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) c.close();
            if (db != null) db.close();
        }
    }

    public static List<Frustration> getListFrustration(Context context) throws Exception {
        List<Frustration> listFrustration = new ArrayList<Frustration>();

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = dbHelper.getReadableDatabase();
            String[] columns = new String[]{
                    TbFrustration.COL_ID.getName(),
                    TbFrustration.COL_TITLE.getName(),
                    TbFrustration.COL_DESCRIPTION.getName(),
                    TbFrustration.COL_WHAT_TO_DO.getName()};

            c = db.query(TbFrustration.TABLE_NAME.getName(), columns, null, null, null, null, null, null);

            while (c.moveToNext()) {
                Frustration frustration = new Frustration();

                frustration.setIdFrustration(c.getInt(c.getColumnIndex(TbFrustration.COL_ID.getName())));
                frustration.setTitle(c.getString(c.getColumnIndex(TbFrustration.COL_TITLE.getName())));
                frustration.setDescription(c.getString(c.getColumnIndex(TbFrustration.COL_DESCRIPTION.getName())));
                frustration.setWhatToDo(c.getString(c.getColumnIndex(TbFrustration.COL_WHAT_TO_DO.getName())));

                listFrustration.add(frustration);
            }
        } catch (Exception e) {
            Log.e("Select frustration", "Erro getListFrustration", e);
            e.printStackTrace();
        } finally {
            if (c != null) c.close();
            if (db != null) db.close();
        }

        Log.i(Constants.TAG_LOG, "Registros encontrados " + listFrustration.size());
        return listFrustration;
    }

    public static int deleteFrustration(Context context, Integer idFrustration) {
        Log.i(Constants.TAG_LOG, "delete frustration");

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;
        int registrosExcluidos = 0;
        try {
            db = dbHelper.getReadableDatabase();

            registrosExcluidos = db.delete(
                    TbFrustration.TABLE_NAME.getName(),
                    TbFrustration.COL_ID.getName() + " = ?",
                    new String[]{String.valueOf(idFrustration)});
        } catch (Exception e) {
            Log.e(Constants.TAG_LOG, "Erro deleteFrustration", e);
            e.printStackTrace();
        } finally {
            if (c != null) c.close();
            if(db != null) db.close();
        }
        return registrosExcluidos;
    }

    public static void updateFrustration(Context context, Frustration frustration) {
        Log.i(Constants.TAG_LOG, "updateFrustration");

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;

        try {
            db = dbHelper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(TbFrustration.COL_TITLE.getName(), frustration.getTitle());
            cv.put(TbFrustration.COL_DESCRIPTION.getName(), frustration.getDescription());
            cv.put(TbFrustration.COL_WHAT_TO_DO.getName(), frustration.getWhatToDo());

            Log.i(Constants.TAG_LOG, String.valueOf(frustration.getIdFrustration()));
            Log.i(Constants.TAG_LOG, frustration.getTitle());
            Log.i(Constants.TAG_LOG, frustration.getDescription());
            Log.i(Constants.TAG_LOG, frustration.getWhatToDo());

            int reg = db.update(
                    TbFrustration.TABLE_NAME.getName(),
                    cv,
                    TbFrustration.COL_ID.getName() + "=" + frustration.getIdFrustration(), null
//                    new String[]{String.valueOf(frustration.getIdFrustration())}
            );

            Log.i(Constants.TAG_LOG, "Registros alterados " + reg);
        } catch (Exception e) {
            Log.e(Constants.TAG_LOG, "Erro updateFrustration", e);
            e.printStackTrace();
        } finally {
            if (c != null) c.close();
            if (db != null) db.close();
        }
    }
}
