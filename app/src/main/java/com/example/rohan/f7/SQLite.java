package com.example.rohan.f7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class SQLite extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME="TIMETABLE.db";
    public static final String TABLE_NAME1="MON";
    public static final String TABLE_NAME2="TUE";
    public static final String TABLE_NAME3="WED";
    public static final String TABLE_NAME4="THU";
    public static final String TABLE_NAME5="FRI";
    public static final String TABLE_NAME6="SAT";
    public static final String CLASS_ID="ID";
    public static final String CLASS_TYPE="TYPE";
    public static final String CLASS_SUBJECT="SUBJECT";
    public static final String CLASS_TIMING="TIMING";
    public static final String CLASS_FACULTY="FACULTY";
    public static final String CLASS_VENUE="VENUE";


    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String MON="CREATE TABLE "+TABLE_NAME1 +"("
                +CLASS_ID+" INTEGER PRIMARY KEY , "
                +CLASS_TYPE+" TEXT, "
                +CLASS_SUBJECT+" TEXT, "
                +CLASS_TIMING+" TEXT, "
                +CLASS_FACULTY+" TEXT, "
                +CLASS_VENUE+" TEXT)";
        String TUE="CREATE TABLE "+TABLE_NAME2 +"("
                +CLASS_ID+" INTEGER PRIMARY KEY , "
                +CLASS_TYPE+" TEXT, "
                +CLASS_SUBJECT+" TEXT, "
                +CLASS_TIMING+" TEXT, "
                +CLASS_FACULTY+" TEXT, "
                +CLASS_VENUE+" TEXT)";
        String WED="CREATE TABLE "+TABLE_NAME3 +"("
                +CLASS_ID+" INTEGER PRIMARY KEY , "
                +CLASS_TYPE+" TEXT, "
                +CLASS_SUBJECT+" TEXT, "
                +CLASS_TIMING+" TEXT, "
                +CLASS_FACULTY+" TEXT, "
                +CLASS_VENUE+" TEXT)";
        String THU="CREATE TABLE "+TABLE_NAME4 +"("
                +CLASS_ID+" INTEGER PRIMARY KEY , "
                +CLASS_TYPE+" TEXT, "
                +CLASS_SUBJECT+" TEXT, "
                +CLASS_TIMING+" TEXT, "
                +CLASS_FACULTY+" TEXT, "
                +CLASS_VENUE+" TEXT)";
        String FRI="CREATE TABLE "+TABLE_NAME5 +"("
                +CLASS_ID+" INTEGER PRIMARY KEY , "
                +CLASS_TYPE+" TEXT, "
                +CLASS_SUBJECT+" TEXT, "
                +CLASS_TIMING+" TEXT, "
                +CLASS_FACULTY+" TEXT, "
                +CLASS_VENUE+" TEXT)";
        String SAT="CREATE TABLE "+TABLE_NAME6 +"("
                +CLASS_ID+" INTEGER PRIMARY KEY , "
                +CLASS_TYPE+" TEXT, "
                +CLASS_SUBJECT+" TEXT, "
                +CLASS_TIMING+" TEXT, "
                +CLASS_FACULTY+" TEXT, "
                +CLASS_VENUE+" TEXT)";

        db.execSQL(MON);
        db.execSQL(TUE);
        db.execSQL(WED);
        db.execSQL(THU);
        db.execSQL(FRI);
        db.execSQL(SAT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME6);
        onCreate(db);

    }
    public boolean insertClass(String tablename,int id, String type, String subject, String timing, String faculty, String venue)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(CLASS_ID, id);
        contentValues.put(CLASS_TYPE, type);
        contentValues.put(CLASS_SUBJECT, subject);
        contentValues.put(CLASS_TIMING, timing);
        contentValues.put(CLASS_FACULTY, faculty);
        contentValues.put(CLASS_VENUE, venue);
        db.insert(tablename, null, contentValues);
        return true;
    }
    public List<ClassDetail> getClassDetail(String tablename)
    {
        List<ClassDetail> classDetailList= new ArrayList<ClassDetail>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query = "SELECT * FROM "+ tablename;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ClassDetail cd = new ClassDetail();
                cd.setType(cursor.getString(1));
                cd.setSubject(cursor.getString(2));
                cd.setTiming(cursor.getString(3));
                cd.setFaculty(cursor.getString(4));
                cd.setRoom(cursor.getString(5));
                classDetailList.add(cd);
            } while (cursor.moveToNext());

        }
        return classDetailList;
    }

}
