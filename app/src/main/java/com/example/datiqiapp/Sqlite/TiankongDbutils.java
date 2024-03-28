package com.example.datiqiapp.Sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.datiqiapp.bean.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * 填空题数据库的增删改查
 */
public class TiankongDbutils {
    public static final String DB_NAME="tiankong_dbname";
    public static final int VERSION=1;
    private static TiankongDbutils sqliteDB;
    private SQLiteDatabase db;

    private TiankongDbutils(Context context){
        TiankongHelper peifangHelper=new TiankongHelper(context,DB_NAME,null,VERSION);
        db=peifangHelper.getWritableDatabase();
    }

    public synchronized static TiankongDbutils getInstance(Context context){
        if(sqliteDB==null){
            sqliteDB=new TiankongDbutils(context);
        }
        return sqliteDB;
    }
    public void delete(Context context, String id){
        TiankongHelper peifangHelper=new TiankongHelper(context,DB_NAME,null,VERSION);
        db=peifangHelper.getReadableDatabase();
        db.delete("Tiankong","id=?",new String[]{id});
    }

    public void change(Context context,Question peifang){
        TiankongHelper peifangHelper=new TiankongHelper(context,DB_NAME,null,VERSION);
        db=peifangHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",peifang.getId());
        values.put("leixing",peifang.getLeixing());
        values.put("wenti",peifang.getWenti());
        values.put("daan",peifang.getDaan());
        db.update("Tiankong",values,"id=?",new String[]{peifang.getId()+""});
    }

    public int insert(Question peifang){
        try {
            db.execSQL("insert into Tiankong(leixing,wenti,daan)values(?,?,?)",new String[]{
                    peifang.getLeixing(),
                    peifang.getWenti(),
                    peifang.getDaan(),
            });
            return 0;
        }catch (Exception e){
            Log.d("����", e.getMessage().toString());
            return -1;
        }
    }
    /**
     * 查询所有数据
     * @return
     */

    public List<Question>load(){
        List<Question> list=new ArrayList<Question>();
        Cursor cursor=db.query("Tiankong",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Question peifang=new Question();
                peifang.setId(cursor.getInt(cursor.getColumnIndex("id")));
                peifang.setLeixing(cursor.getString(cursor.getColumnIndex("leixing")));
                peifang.setWenti(cursor.getString(cursor.getColumnIndex("wenti")));
                peifang.setDaan(cursor.getString(cursor.getColumnIndex("daan")));
                list.add(peifang);
            }while (cursor.moveToNext());
            cursor.close();
        }   return list;

    }
    public List<Question> load(String name){
        List<Question>list=new ArrayList<Question>();
        Cursor cursor=db.query("Tiankong",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                if(cursor.getString(cursor.getColumnIndex("leixing")).indexOf(name)!=-1){
                    Question medic=new Question();
                    medic.setLeixing(cursor.getString(cursor.getColumnIndex("leixing")));
                    medic.setWenti(cursor.getString(cursor.getColumnIndex("wenti")));
                    medic.setDaan(cursor.getString(cursor.getColumnIndex("daan")));
                    list.add(medic);
                }
            }while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
}
