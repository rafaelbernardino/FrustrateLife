package com.bernardino.frustratelife.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bernardino.frustratelife.persistence.scripts.FrustrateLifeCreate;

/**
 * Created by Rafael on 12/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "frustrationlifebernardino.db";
    private static final int VERSAO_SCHEMA = 1;

    public DatabaseHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DataBase", "Create");
        //script usado na instalação do app
        FrustrateLifeCreate.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onOpenReadableDatabase() {
        onOpen(this.getReadableDatabase());
    }

    public void onOpenWritableDatabase() {
        onOpen(this.getWritableDatabase());
    }
}
