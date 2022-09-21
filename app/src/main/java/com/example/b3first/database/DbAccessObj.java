package com.example.b3first.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.b3first.database.FeedReaderContract.FeedEntry;


//https://developer.android.com/training/data-storage/sqlite#java
public class DbAccessObj {

    SQLiteDatabase database;
    DbHelper dbHelper;

    public DbAccessObj(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void openDb(){
        database = dbHelper.getWritableDatabase();
    }
    public void closeDb(){
        database.close();
    }

    public void  createRow(String title, String subtitle){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,title);
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);

        database.insert(FeedEntry.TABLE_NAME,null,values);
    }
    public String readRow(){
      Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
      cursor.moveToLast();
      int titleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE);
      int subtitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE);

      String result = cursor.getString(titleIndex)+"\n"+ cursor.getString(subtitleIndex);
      return result;
    }
    public void updateRow(){}
    public void deleteRow(){}
}
