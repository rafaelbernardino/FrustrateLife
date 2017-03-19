package com.bernardino.frustratelife.persistence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bernardino.frustratelife.Constants;
import com.bernardino.frustratelife.persistence.DatabaseHelper;
import com.bernardino.frustratelife.persistence.bean.User;
import com.bernardino.frustratelife.persistence.table.TbUser;

/**
 * Created by Rafael on 12/03/2017.
 */

public class DaoUser {
    public static void insertDataUser(Context context, String user, String pass) throws Exception {
        Log.i(Constants.TAG_LOG, "insertDataUser");

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = dbHelper.getReadableDatabase();

            ContentValues userInsert = new ContentValues();
            userInsert.put("USER", user);
            userInsert.put("PASSWORD", pass);
            db.insert(TbUser.TABLE_NAME.getName(), null, userInsert);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) c.close();
            if (db != null) db.close();
        }
    }

    public static User getUser(Context context) throws Exception {
        Log.i(Constants.TAG_LOG, "getUser");

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = dbHelper.getReadableDatabase();
            String[] columns = new String[]{TbUser.COL_USER.getName(), TbUser.COL_PASS.getName()};

            c = db.query(TbUser.TABLE_NAME.getName(), columns, null, null, null, null, null, null);

            while(c.moveToNext()) {
                User user = new User();

                user.setUser(c.getString(c.getColumnIndex(TbUser.COL_USER.getName())));
                user.setPass(c.getString(c.getColumnIndex(TbUser.COL_PASS.getName())));
                return user;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) c.close();
            if(db != null) db.close();
        }

        return null;
    }

    public static void deleteUser(Context context) throws Exception {
        Log.i("Delete", "deleteUser");

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = dbHelper.getReadableDatabase();
            int reg = db.delete(TbUser.TABLE_NAME.getName(), null, null);
            Log.i(Constants.TAG_LOG, "Registro de usuario deletado " + reg);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) c.close();
            if (db != null) db.close();
        }
    }
}
