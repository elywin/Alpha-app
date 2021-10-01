package com.example.alpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "alpha.db";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean Insert(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("user", null, contentValues);
        return true;
    }

    public Boolean CheckUsername(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckLogin(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=? AND password=?", new String[]{email, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
