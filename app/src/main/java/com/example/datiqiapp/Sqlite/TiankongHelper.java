package com.example.datiqiapp.Sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 填空题数据库Helper类
 */
public class TiankongHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table Tiankong ("
            + "id integer primary key autoincrement, "
            + "leixing text, "
            + "wenti text, "
            + "daan text)";

    public TiankongHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
