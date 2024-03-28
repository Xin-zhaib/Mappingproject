package com.example.datiqiapp.Sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.datiqiapp.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用户增删改查
 */
public class UserDBUtils {
   public static final String DB_NAME="sqlite_dbname";
   public static final int VERSION=1;
   private static UserDBUtils sqliteDB;
   private  SQLiteDatabase db;

   private UserDBUtils(Context context){
       UserHelper dbHelper=new UserHelper(context,DB_NAME,null,VERSION);
       db=dbHelper.getWritableDatabase();
   }

   public synchronized static UserDBUtils getInstance(Context context){
   if(sqliteDB==null){
       sqliteDB=new UserDBUtils(context);
   }
   return sqliteDB;
   }
   public void delete(Context context,String username){
       UserHelper dbHelper=new UserHelper(context,DB_NAME,null,VERSION);
       db=dbHelper.getReadableDatabase();
       db.delete("User","username=?",new String[]{username});
   }
   public void change(Context context,int id, String name,String pwd){
     UserHelper dbHelper=new UserHelper(context,DB_NAME,null,VERSION);
           db=dbHelper.getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put("id",id);
       values.put("username",name);
       values.put("userpwd",pwd);
       db.update("User",values,"id=?",new String[]{id+""});
   }

   //添加用户
    public int saveUser(User user){
       //首先判断user参数是否为空，如果为空则返回0
       if(user!=null){
           //使用db.rawQuery()方法来查询数据库中是否已经存在相同用户名的数据。
           //这个方法接受两个参数：一个String类型的参数sql，表示要执行的SQL语句；一个String类型的数组参数selectionArgs，表示查询条件的参数值
           Cursor cursor=db.rawQuery("select * from User where username=?",new String[]{user.getUsername().toString()});

           //查询结果中已经存在相同用户名的数据，则返回-1，表示插入失败
          if(cursor.getCount()>0){
              return -1;
          }else{
          try {
              db.execSQL("insert into User(username,userpwd)values(?,?)",new String[]{user.getUsername().toString(),user.getUserpwd().toString()});
          }catch (Exception e){
              Log.d("����", e.getMessage().toString());
          }
           return 1;
          }
       }else {
           return 0;
       }
    }

    public List<User> loadUser(){
        List<User> list=new ArrayList<User>();
        Cursor cursor=db.query("User",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
           do{
            User user=new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setUserpwd(cursor.getString(cursor.getColumnIndex("userpwd")));
            list.add(user);
            }while (cursor.moveToNext());
        }
            return list;
   }

   //根据用户名和密码查询用户信息，返回值为1表示查询到了该用户，返回值为-1表示密码不正确，返回值为0表示用户名不存在
    public int Quer(String name,String pwd){
       HashMap<String,String>hashMap=new HashMap<String,String>();
       Cursor cursor=db.rawQuery("select * from User where username=?",new String[]{name});
       if(cursor.getCount()>0){
           Cursor pwdcursor=db.rawQuery("select * from User where  username=? and userpwd=? ",new String[]{name,pwd});
          if(pwdcursor.getCount()>0){
              return 1;
          }else {
              return -1;
          }
       }else {
           return 0;

       }
    }

    //根据用户名和密码查询用户信息，并返回该用户的id，如果用户名和密码不匹配则返回0
       public int selectId(String name,String pwd){
        int id=0;
        Cursor cursor=db.query("User",null,null,null,null,null,null);
         if(cursor.moveToFirst()){
             do {
                 if(name.equals(cursor.getString(cursor.getColumnIndex("username")))&&
                 pwd.equals(cursor.getString(cursor.getColumnIndex("userpwd")))){
                   return cursor.getInt(cursor.getColumnIndex("id"));
                 }



             }while (cursor.moveToNext());
         }
         return id;
   }

}