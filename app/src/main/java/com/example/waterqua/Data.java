package com.example.waterqua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Data extends SQLiteOpenHelper {
    public static final String databaseName = "water_quality.db";

    public Data(@Nullable Context context) {
        super(context,"water_quality.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alluser(username TEXT,email TEXT primary key , password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists alluser");

    }
    public boolean signup(String username, String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("usename",username);
        cv.put("email",email);
        cv.put("password",password);
        long result = db.insert("alluser",null, cv);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from alluser where email= ? ", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }

    }
    public boolean checkemailPassword(String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from alluser where email= ? and password=?", new String[]{email,password});

        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }

    }
}

