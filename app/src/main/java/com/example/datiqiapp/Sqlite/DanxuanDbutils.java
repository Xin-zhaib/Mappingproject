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
 * 心理测试题目存入sqlite数据库的增删改查
 */
public class DanxuanDbutils {
    public static final String DB_NAME="peifang_dbname";
    public static final int VERSION=1;
    private static DanxuanDbutils sqliteDB;
    private SQLiteDatabase db;

    private DanxuanDbutils(Context context){
        DanxuanHelper peifangHelper=new DanxuanHelper(context,DB_NAME,null,VERSION);
        db=peifangHelper.getWritableDatabase();
    }
    public synchronized static DanxuanDbutils getInstance(Context context){
        if(sqliteDB==null){
            sqliteDB=new DanxuanDbutils(context);
        }
        return sqliteDB;
    }

    public void delete(Context context, String id){
        DanxuanHelper peifangHelper=new DanxuanHelper(context,DB_NAME,null,VERSION);
        db=peifangHelper.getReadableDatabase();
        db.delete("Peifang","id=?",new String[]{id});
    }

    public void change(Context context,Question peifang){
        DanxuanHelper peifangHelper=new DanxuanHelper(context,DB_NAME,null,VERSION);
        db=peifangHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",peifang.getId());
        values.put("leixing",peifang.getLeixing());
        values.put("wenti",peifang.getWenti());
        values.put("daan",peifang.getDaan());
        db.update("Peifang",values,"id=?",new String[]{peifang.getId()+""});
    }

    public int insert(Question peifang){
     try {
         db.execSQL("insert into Peifang(leixing,wenti,daan)values(?,?,?)",new String[]{
                 peifang.getLeixing(),
                 peifang.getWenti(),
                 peifang.getDaan(),
         });
         return 0;
     }catch (Exception e){
         Log.d("保存失败", e.getMessage().toString());
         return -1;
     }
    }
    /**
     * 查询所有数据
     * @return
     */

    public List<Question>load(){
        List<Question> list=new ArrayList<Question>();
        Cursor cursor=db.query("Peifang",null,null,null,null,null,null);
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
        Cursor cursor=db.query("Peifang",null,null,null,null,null,null);
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
