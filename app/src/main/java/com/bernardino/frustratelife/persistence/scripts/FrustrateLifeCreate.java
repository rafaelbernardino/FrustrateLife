package com.bernardino.frustratelife.persistence.scripts;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rafael on 09/03/2017.
 */

public class FrustrateLifeCreate {
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE FRUSTRATION (");
        sql.append(" ID_FRUSTRATION INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(" TITLE TEXT, ");
        sql.append(" DESCRIPTION TEXT, ");
        sql.append(" WHAT_TO_DO TEXT );");

        db.execSQL(sql.toString());

        sql = new StringBuilder();

        sql.append("CREATE TABLE APP_USER (");
        sql.append(" USER TEXT, ");
        sql.append(" PASSWORD TEXT );");

        db.execSQL(sql.toString());
    }
}
