package com.example.asus.likemap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ruttapongpaleegui on 11/29/2016 AD.
 */

public class RoomDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "building.db";
    public static final String TABLE_NAME = "Map_table";
    public static final int DATABASE_VERSION = 1;

    public static final String COL_1 = "ID";
    public static final String COL_2 = "Building";
    public static final String COL_3 = "Room";
    public static final String COL_4  = "Pic";
    public static final String COL_5 = "SetText";


    public RoomDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public RoomDatabase(){
        super(null,null,null,1);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Building TEXT,Room TEXT,Pic,SetText)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertBuilding(Info info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, info.getBuilding());
        values.put(COL_3, info.getRoom());
        values.put(COL_4, info.getPic());
        values.put(COL_5, info.getText());

//        db.close();
        long i =db.insert(TABLE_NAME, null, values);
        if(i==-1)
            return false ;
        else
        return true;
//        return null;
    }
    public  boolean insertRoom(String room) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public ArrayList<String> getAllRoom(String building){
        ArrayList<String> listString = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            do{
                listString.add(res.getString(2));
            } while (res.moveToNext()) ;
        }
        return  listString;
    }
    public  Info getPic(String building, String room){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, String.valueOf(new String[]{building}), new String[]{room},null,null,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        Info info = new Info(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return info;
    }
    public Info getInfo(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Boolean.parseBoolean(TABLE_NAME),COL_1,new String[]{COL_1,
        COL_2,COL_3,COL_4,COL_5},COL_1 + "=?" ,new String[]{String.valueOf(id)},null,null
        ,null,null);

        if(cursor !=null){
            cursor.moveToFirst();
        }
        Info info = new Info(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return info;
    }
//    public String getBuildRoom(String Building ,String Room){
////        SQLiteDatabase db = this.getReadableDatabase();
////        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_1,COL_2,COL_3},COL_1,C);
//    }
    public ArrayList<Info> getAllData() {
        ArrayList<Info> listString = new ArrayList<Info>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if (res.moveToFirst()) {
        do{
            Info info =new Info();
            info.setID(res.getString(0));
            info.setBuilding(res.getString(1));
            info.setRoom(res.getString(2));
            info.setPic(res.getString(3));
            info.setText(res.getString(4));
//            String word = res.getString(0) +" "+ res.getString(1) +" "+ res.getString(2) +" "+res.getString(3);
            listString.add(info);
        } while (res.moveToNext()) ;
        }
        return  listString;
    }
    public void deleteRoom(Info info){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COL_1+" =? ",
                new String[] {String.valueOf(info.getID())});
    }
//    public int updateText(Info info){
//        try
//        {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(COL_2, info.getBuilding());
//            values.put(COL_3, info.getRoom());
//            values.put(COL_4, info.getPic());
//            values.put(COL_5,info.getText());
//            db.update(TABLE_NAME, values, "ID =?",new String[]{info.getID()});
//            return 1;
//
//        }catch(Throwable e) {
//            return 0;
//
//        }
//
//    }

}