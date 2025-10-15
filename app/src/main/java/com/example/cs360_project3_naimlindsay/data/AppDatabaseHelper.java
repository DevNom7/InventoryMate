package com.example.cs360_project3_naimlindsay.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "appdata.db";
    public static final int DB_VERSION = 1;

    public AppDatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // simple users table
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        // simple inventory table
        db.execSQL("CREATE TABLE inventory(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, qty INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS inventory");
        onCreate(db);
    }
}
