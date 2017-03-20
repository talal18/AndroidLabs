package com.example.talal.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Talal on 2017-02-12.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "CHATS";
    public static String Database_Name = "Chat.db";
    public static int Version_NUM = 1;
    public static final String KEY_ID ="_id" ;
    public static final String KEY_MESSAGE =  "_msg";


    public ChatDatabaseHelper(Context ctx){
        super(ctx, Database_Name, null, Version_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(ChatDatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(ChatDatabaseHelper.class.getName(), "Downgrading database from version " + newVersion + " to "
                + oldVersion + ", which will destroy all old data.");
        db.execSQL("Dropr Table IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_MESSAGE + " VARCHAR(50));";
}
