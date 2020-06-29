package com.example.misreportapartment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.misreportapartment.Model.User;
import com.example.misreportapartment.Utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME,null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, phone INT, password TEXT) ";
        db.execSQL(CREATE_REGISTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_IF_EXISTS =" DROP TABLE IF EXISTS " + Util.TABLE_NAME;// DROP OLDER TABLE IF EXISTS
        db.execSQL(DROP_TABLE_IF_EXISTS);
        onCreate(db);
    }

    public boolean addUser(User guestToAdd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.COL_2, guestToAdd.getUserName());
        contentValues.put(Util.COL_3, guestToAdd.getPhone());
        contentValues.put(Util.COL_4, guestToAdd.getPassword());
        long res = db.insert(Util.TABLE_NAME,null,contentValues);
        if (res == -1){
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }
    public boolean checkUser(String username, String password){
        String[] columns = { Util.COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = Util.COL_2 + "=?" + " and " + Util.COL_4 + "=?";
        String[] selectionArgs =  {username,password};
        Cursor cursor = db.query(Util.TABLE_NAME, columns,selection, selectionArgs, null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        else
            return false;
    }
    public List<User> getUserInfo(){

        List<User> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_info_query = "SELECT * FROM " + Util.TABLE_NAME;// + " WHERE username = " + Util.COL_2

        Cursor cursor = db.rawQuery(get_all_info_query, null);

        if (cursor.moveToFirst()){
            do {
                User guest = new User();
                guest.setId(cursor.getInt(0));
                guest.setUserName(cursor.getString(1));
                guest.setPhone(cursor.getString(2));
                guest.setPassword(cursor.getString(4));
                result.add(guest);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public User getUserInfo(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.COL_1, Util.COL_2, Util.COL_3, Util.COL_4},
                Util.COL_1 + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
         if (cursor != null)
            cursor.moveToFirst();
            User guest = new User(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2));
        return guest;
    }

    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.COL_2, user.getUserName());
        contentValues.put(Util.COL_3, user.getPhone());
        contentValues.put(Util.COL_4, user.getPassword());

        //Update row
        return db.update(Util.TABLE_NAME, contentValues, Util.COL_1 + "=?",
                new String[]{String.valueOf(Util.COL_1)});

    }
}