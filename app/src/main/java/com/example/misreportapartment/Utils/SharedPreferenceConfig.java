package com.example.misreportapartment.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.misreportapartment.R;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context) {
        this.context = context;
        //sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_shared_preference, status));
    }
    public void login_status(boolean status){

    }
}
