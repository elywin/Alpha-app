package com.example.alpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "alpha.db";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
        db.execSQL("CREATE TABLE student(ID INTEGER PRIMARY KEY AUTOINCREMENT,first_name TEXT,last_name TEXT,programOfStudy TEXT,yearOfStudy TEXT,courseunit TEXT,residence TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS student");
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

    public boolean addStudent(String first_name, String last_name,String programOfStudy,String yearOfStudy,String courseunit,String residence){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name ", first_name );
        contentValues.put("last_name", last_name);
        contentValues.put("programOfStudy", programOfStudy);
        contentValues.put("yearOfStudy", yearOfStudy);
        contentValues.put("courseunit", courseunit);
        contentValues.put("residence", residence);

        db.insert("student", null, contentValues);
        return true;
    }

    public boolean updateStudent (Integer id, String first_name, String last_name,String programOfStudy,String yearOfStudy,String courseunit,String residence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name ", first_name );
        contentValues.put("last_name", last_name);
        contentValues.put("programOfStudy", programOfStudy);
        contentValues.put("yearOfStudy", yearOfStudy);
        contentValues.put("courseunit", courseunit);
        contentValues.put("residence", residence);
        db.update("student", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteStudent (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("student",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllStudents() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from student", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("first_name")));
            res.moveToNext();
        }
        return array_list;
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
