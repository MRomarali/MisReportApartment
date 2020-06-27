package com.example.misreportapartment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.misreportapartment.Model.Guest;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1    ="ID";
    public static final String COL_2    ="username";
    public static final String COL_3    ="phone";
    public static final String COL_4 ="password";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, phone INT, password TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);// DROP OLDER TABLE IF EXISTS
        onCreate(db);
    }

    public boolean addUser(Guest guestToAdd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, guestToAdd.getUserName());
        contentValues.put(COL_3, guestToAdd.getPhone());
        contentValues.put(COL_4, guestToAdd.getPassword());
        long res = db.insert(TABLE_NAME,null,contentValues);
        if (res == -1){
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }
    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs =  {username,password};
        Cursor cursor = db.query(TABLE_NAME, columns,selection, selectionArgs, null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        else
            return false;
    }
    public ArrayList<Guest> getUserInfo(){

        ArrayList<Guest> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_info_query = "SELECT * FROM " + TABLE_NAME + " WHERE username = " + COL_2;

        Cursor cursor = db.rawQuery(get_all_info_query, null);

        while (cursor.moveToNext()){
            String userName = cursor.getString(1);
            String phone = cursor.getString(2);
            Guest tempGuest = new Guest(userName,phone);
            result.add(tempGuest);
        }
                cursor.close();
                return result;
    }
}