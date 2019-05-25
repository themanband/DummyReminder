package com.example.dummyreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DatabaseSQLite extends SQLiteOpenHelper {
    private static final String dbName = "DummyDB";
    private static final String dbCreate = "CREATE TABLE Reminders (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME TEXT, DATE TEXT, DAILY INTEGER)";

    public DatabaseSQLite(Context context) {
        super(context, dbName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Reminders");
    }

    public boolean insertData(String name, String time, String date, int daily) {
        SQLiteDatabase db =  this.getWritableDatabase();

        ContentValues contentValues =  new ContentValues();
        //inserts rows
        contentValues.put("NAME", name);
        contentValues.put("TIME", time);
        contentValues.put("DATE", date);
        contentValues.put("DAILY", daily);
        ///

        //inserts table "reminders"
        long result = db.insert("Reminders", null, contentValues);
        ///

        if (result == -1 ){
            return false;
        }else{
            return true;
        }
    }

    public Cursor returnAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //query to get data
        Cursor res = db.rawQuery("SELECT * FROM Reminders ORDER BY ID ASC", null);
        while(res.moveToNext()) {
            Log.d("~~DEBUGGING DB~~", "Name: '" + res.getString(1) + "'");
        }
        return res;
    }

}
