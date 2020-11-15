package com.example.misreportapartment.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Util {
    public static final int DATABASE_VERSION= 1;
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1    ="ID";
    public static final String COL_2    ="username";
    public static final String COL_3    ="phone";
    public static final String COL_4 ="password";

    public final static String FileName = "omar@admin.com";
    public static String readSharedSetting(Context ctx, String settingName, String defaultValue){
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }
    public static void saveSharedSetting(Context ctx, String settingName, String settingValue){
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

}
