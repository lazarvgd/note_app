package com.example.xor.note.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xor.note.entities.Note;

import java.util.List;

/**
 * Created by xor on 12.11.16..
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Notes.db";
    public  static final String TABLE_NAME= "notes_table";
    public static  final  String COL1="ID";
    public static  final  String COL2="TITLE";
    public static  final  String COL3="CONTENT";
    private SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean inserData(String title, String content){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,title);
        contentValues.put(COL3, content);
        long result = db.insert(TABLE_NAME,null,contentValues);

        return result==-1?false:true;
    }

    public Cursor getCursor(){
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+";",null);

        return cursor;
    }

    public boolean deleteItem(int id) {

            String id2=""+id;
            //return db.delete(TABLE_NAME, "ID = ?",new String[] {id2});
       return db.delete(TABLE_NAME,"ID="+id,null)!=0;

    }


}
