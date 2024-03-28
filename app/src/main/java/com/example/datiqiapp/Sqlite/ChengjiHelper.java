package com.example.datiqiapp.Sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 成绩数据库Helper类
 */
public class ChengjiHelper extends SQLiteOpenHelper {

    //创建SQLite表的查询语句，用于创建一个名为“Medic”的表，id为主键，autoincrement为设置id可以自增
    public static final String CREATE_USER ="create table Medic("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "userchengji text)";;

    //构造函数，传输数据库的名字，版本号，使得可以创建数据库
    public ChengjiHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库初始化，当数据库第一次被创建时，onCreate()方法会被调用
    //调用它的execSQL()方法来执行CREATE_USER语句，从而创建一个名为“Medic”的表格
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER);
    }

    //数据库升级
    //如果数据库已经存在，onCreate()方法不会被调用。相反，SQLiteOpenHelper会检查数据库的版本号，如果版本号发生变化，则会自动调用onUpgrade()方法来升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
