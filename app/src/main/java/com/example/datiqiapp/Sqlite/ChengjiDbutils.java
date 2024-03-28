package com.example.datiqiapp.Sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.datiqiapp.bean.Chengji;

import java.util.ArrayList;
import java.util.List;

/**
 * 成绩数据库的增删改查
 */
public class ChengjiDbutils {
   public static final String DB_NAME="medic_dbname";//指定数据库名称为medic
   public static final int VERSION=1;
   private static ChengjiDbutils sqliteDB;//静态变量，用于存储ChengjiDbutils类的单例对象
   private SQLiteDatabase db;//私有变量，用于存储SQLite数据库的实例

    //单例模式
    //1.构造函数私有化，上述的private static ChengjiDbutils sqliteDB;
    //2.对外提供函数
   private ChengjiDbutils(Context context){
       ChengjiHelper openHelper=new ChengjiHelper(context,DB_NAME,null,VERSION);
       db=openHelper.getWritableDatabase();//用于创建databases文件夹
   }
   public synchronized static ChengjiDbutils getInstance(Context context){
       if(sqliteDB==null){
        sqliteDB=new ChengjiDbutils(context);
       }
       return sqliteDB;
   }

   //删除
   public void delete(Context context,String id){
       ChengjiHelper openHelper=new ChengjiHelper(context,DB_NAME,null,VERSION);
       db=openHelper.getReadableDatabase();//打开数据表
       db.delete("Medic","id=?",new String[]{id});
   }

   //修改
    public void change(Context context, Chengji medic){
       //创建一个ChengjiHelper对象openHelper，并调用它的getWritableDatabase()方法来获取一个可写的SQLite数据库对象
        ChengjiHelper openHelper=new ChengjiHelper(context,DB_NAME,null,VERSION);
        db=openHelper.getWritableDatabase();

        //创建一个ContentValues对象values
        ContentValues values=new ContentValues();
        //使用了put()方法来设置每一列的值。put()方法接受两个参数：一个String类型的参数key，表示要更新的列名；一个Object类型的参数value，表示要更新的值。
        values.put("id",medic.getId());
        values.put("username",medic.getUsername());
        values.put("userchengji", medic.getUserchengji());
        //使用db.update()方法来更新数据。
        // 这个方法接受四个参数：一个String类型的参数table，表示要更新的表名；
        // 一个ContentValues类型的参数values，表示要更新的数据；
        // 一个String类型的参数whereClause，表示更新的条件；
        // 一个String类型的数组参数whereArgs，表示更新条件的参数值
        db.update("Medic",values,"id=?",new String[]{medic.getId()+""});
    }

    //插入
   public int insert(Chengji medic){
       try {
           db.execSQL("insert into Medic(username,userchengji) values(?,?)",new String[]{
                   medic.getUsername(),
                   medic.getUserchengji()
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

    //从SQLite数据库中查询数据，并返回一个Chengji类型的列表
    public List<Chengji> load(){
          List<Chengji>list=new ArrayList<Chengji>();
          //使用db.query()方法来查询“Medic”表格中的所有数据，并将结果保存到一个Cursor对象中
          Cursor cursor=db.query("Medic",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                ////创建了一个Chengji类型的对象medic
                Chengji medic=new Chengji();

                //使用游标cursor对象获取每一列的值，并将这些值设置到medic对象的对应属性中
               medic.setId(cursor.getInt(cursor.getColumnIndex("id")));
               medic.setUsername(cursor.getString(cursor.getColumnIndex("username")));
               medic.setUserchengji(cursor.getString(cursor.getColumnIndex("userchengji")));

               //将medic对象添加到列表中
               list.add(medic);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public List<Chengji> loadByName(String name){
        List<Chengji>list=new ArrayList<Chengji>();
        Cursor cursor=db.query("Medic",null,null,null,null,null,null);
       if(cursor.moveToFirst()){
           do {
           if(cursor.getString(cursor.getColumnIndex("name")).indexOf(name)!=-1){
               Chengji medic=new Chengji();
              medic.setId(cursor.getInt(cursor.getColumnIndex("id")));
               medic.setUsername(cursor.getString(cursor.getColumnIndex("username")));
               medic.setUserchengji(cursor.getString(cursor.getColumnIndex("userchengji")));
              list.add(medic);
           }
          }while (cursor.moveToNext());
       }
       return list;
    }
}
