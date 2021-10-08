package com.example.anotherdbapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "dbshechka";
    public static final int Version = 1;
    public static final String TableName = "users";
    public static String ColumnId = "_id";
    public static String ColumnName = "name";
    public static String ColumnAge = "age";

    public DBHelper(Context context) {
        super(context, DBName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TableName +
                "(" + ColumnId + " INTEGER PRIMARY KEY, " + ColumnName + " TEXT, " + ColumnAge + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }
}
