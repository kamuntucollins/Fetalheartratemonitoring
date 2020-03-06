package com.example.fetalheartratemonitoring;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper2 extends SQLiteOpenHelper {
    private  static final  String DATABASE_NAME = "database_name";
    private static final String TABLE_NAME = "table_name";

    DataBaseHelper2(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable ="create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY,txt TEXT)";
        db.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public  boolean addText(String text){
        //get witable database
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //create contentvalues
        ContentValues contentValues=new ContentValues();
        contentValues.put("txt",text);
        //add data into the database
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return true;

    }


    public ArrayList getAllText(){
        //get readable database
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ArrayList<String> arrayList=new ArrayList<String>();
        //creating cursor to select all values
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("txt")));
            cursor.moveToNext();
        }
        return  arrayList;

    }
}
