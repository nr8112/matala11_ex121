package com.example.matala11_ex121;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.matala11_ex121.Grades.GRADE;
import static com.example.matala11_ex121.Grades.SUBJECT;
import static com.example.matala11_ex121.Grades.TABLE_GRADES;
import static com.example.matala11_ex121.Users.ACTIVE_OR_NOT;
import static com.example.matala11_ex121.Users.ADDRESS;
import static com.example.matala11_ex121.Users.AGE;
import static com.example.matala11_ex121.Users.HOME_PHONE;
import static com.example.matala11_ex121.Users.KEY_ID;
import static com.example.matala11_ex121.Users.NAME;
import static com.example.matala11_ex121.Users.PARENT_NAME_1;
import static com.example.matala11_ex121.Users.PARENT_NAME_2;
import static com.example.matala11_ex121.Users.PARENT_PHONE;
import static com.example.matala11_ex121.Users.STUDENT_PHONE;
import static com.example.matala11_ex121.Users.TABLE_USERS;

public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_USERS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+AGE+" INTEGER";
        strCreate += " "+ ACTIVE_OR_NOT+" INTEGER,";
        strCreate += " "+ADDRESS+" TEXT,";
        strCreate += " "+ STUDENT_PHONE+" TEXT,";
        strCreate += " "+ HOME_PHONE+" TEXT,";
        strCreate += " "+ PARENT_NAME_1+" TEXT,";
        strCreate += " "+ PARENT_NAME_2+" TEXT,";
        strCreate += " "+ PARENT_PHONE+" TEXT,";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_GRADES;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+SUBJECT+" TEXT,";
        strCreate+=" "+GRADE+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        strDelete="DROP TABLE IF EXISTS "+TABLE_USERS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_GRADES;
        db.execSQL(strDelete);

        onCreate(db);
    }
}

