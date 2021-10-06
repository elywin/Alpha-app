package com.example.alpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String  DATABASE_NAME="student.db";
    private static final String TABLE_NAME="students";
    private static final String ID="id";
    private static final String fname="first_name";
    private static final String lname="last_name";
    private static final String programOfStudy="programOfStudy";
    private static final String yearOfStudy="yearOfStudy";
    private static final String courseunit="courseunit";
    private static final String residence="residence";
    private static final String created_at="created_at";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not EXISTS user (ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");

        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY,"+
                fname+" TEXT ,"+
                lname+" TEXT ,"+
                programOfStudy+" TEXT ,"+
                yearOfStudy+"TEXT,"+
                courseunit+"TEXT,"+
                residence+ " TEXT ,"+
                created_at+ " TEXT "+
                ")";
        db.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public void AddStudnet(StudentModel studentModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(fname,studentModel.getfName());
        contentValues.put(lname,studentModel.getlName());
        contentValues.put(programOfStudy,studentModel.getProgramofstudy());
        contentValues.put(yearOfStudy,studentModel.getYearofstudy());
        contentValues.put(courseunit,studentModel.getCourseunit());
        contentValues.put(residence,studentModel.getResidence());
        contentValues.put(created_at,studentModel.getCreated_at());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public StudentModel getStudent(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{ID,fname,lname,yearOfStudy,programOfStudy,courseunit,residence,created_at},ID+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        StudentModel studentModel=new StudentModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
        db.close();
        return studentModel;
    }

    public List<StudentModel> getAllStudents(){
        List<StudentModel> studentModelList=new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                StudentModel studentModel=new StudentModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3),cursor.getString(5),cursor.getString(6),cursor.getString(7));
                studentModelList.add(studentModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return studentModelList;
    }

    public int updateStudent(StudentModel studentModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(fname,studentModel.getfName());
        contentValues.put(lname,studentModel.getlName());
        contentValues.put(residence,studentModel.getResidence());
        contentValues.put(programOfStudy,studentModel.getProgramofstudy());
        contentValues.put(yearOfStudy,studentModel.getYearofstudy());
        contentValues.put(courseunit,studentModel.getCourseunit());
        return db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(studentModel.getId())});

    }

    public void deleteStudent(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+"=?",new String[]{id});
        db.close();
    }

    public int getTotalCount(){
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
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
        public boolean Insert(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("user", null, contentValues);
        return true;
    }
}

//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//
//public class DBhelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "alpha.db";
//
//    public DBhelper(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
//        db.execSQL("CREATE TABLE student(ID INTEGER PRIMARY KEY AUTOINCREMENT,first_name TEXT,last_name TEXT,programOfStudy TEXT,yearOfStudy TEXT,courseunit TEXT,residence TEXT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS user");
//        db.execSQL("DROP TABLE IF EXISTS student");
//        onCreate(db);
//    }
//
//    public boolean Insert(String email, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email", email);
//        contentValues.put("password", password);
//        db.insert("user", null, contentValues);
//        return true;
//    }
//
//    public boolean addStudent(String first_name, String last_name,String programOfStudy,String yearOfStudy,String courseunit,String residence){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("first_name ", first_name );
//        contentValues.put("last_name", last_name);
//        contentValues.put("programOfStudy", programOfStudy);
//        contentValues.put("yearOfStudy", yearOfStudy);
//        contentValues.put("courseunit", courseunit);
//        contentValues.put("residence", residence);
//
//        db.insert("student", null, contentValues);
//        return true;
//    }
//
//    public boolean updateStudent (Integer id, String first_name, String last_name,String programOfStudy,String yearOfStudy,String courseunit,String residence) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("first_name ", first_name );
//        contentValues.put("last_name", last_name);
//        contentValues.put("programOfStudy", programOfStudy);
//        contentValues.put("yearOfStudy", yearOfStudy);
//        contentValues.put("courseunit", courseunit);
//        contentValues.put("residence", residence);
//        db.update("student", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Integer deleteStudent (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("student",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList<String> getAllStudents() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from student", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex("first_name")));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public Boolean CheckUsername(String email){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
//        if(cursor.getCount() > 0){
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//    public Boolean CheckLogin(String email, String password){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=? AND password=?", new String[]{email, password});
//        if(cursor.getCount() > 0){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//
//}
