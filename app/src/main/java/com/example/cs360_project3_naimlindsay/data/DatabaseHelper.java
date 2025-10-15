package com.example.cs360_project3_naimlindsay.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cs360_app.db";
    private static final int DB_VERSION = 2; // <- bump this whenever you change schema

    public static final String T_USERS = "users";
    public static final String T_INV   = "inventory";

    public DatabaseHelper(Context ctx) { super(ctx, DB_NAME, null, DB_VERSION); }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + T_USERS + " (" +
                "username TEXT PRIMARY KEY," +
                "password TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + T_INV + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "qty INTEGER NOT NULL)");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // simplest for class project — drop & recreate
        db.execSQL("DROP TABLE IF EXISTS " + T_INV);
        db.execSQL("DROP TABLE IF EXISTS " + T_USERS);
        onCreate(db);
    }

    // ---------- Users ----------
    public boolean createUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username.trim());
        cv.put("password", password.trim());
        long id = db.insertWithOnConflict(T_USERS, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        return id != -1; // false if user already existed
    }

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT 1 FROM " + T_USERS + " WHERE username=? AND password=? LIMIT 1",
                new String[]{ username.trim(), password.trim() }
        );
        boolean ok = c.moveToFirst();
        c.close();
        return ok;
    }

    // ---------- Inventory ----------
    public long insertItem(String name, int qty) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name.trim());
        cv.put("qty", qty);
        return db.insert(T_INV, null, cv); // returns new rowId or -1 on failure
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT id, name, qty FROM " + T_INV + " ORDER BY id DESC", null);
    }
}
