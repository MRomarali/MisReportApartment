package com.example.misreportapartment.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.misreportapartment.Database.DatabaseHelper;
import com.example.misreportapartment.R;
import com.example.misreportapartment.Utils.Util;


public class Login extends AppCompatActivity {
    public final static String SHARED_PREF_USERINFO = "SHAREDPREF";
    public final static String TEXTVIEW_USERNAME = "TEXTVIEW_TEXT_NAME";
    public final static String TEXTVIEW_PASSWORD = "TEXTVIEW_TEXT_PASS";

    private Button _btnLogin;
    private EditText _txtEmail, _txtPass;
    private TextView myTextViewRegister;
    private DatabaseHelper db;

    String user;
    String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        _btnLogin = findViewById(R.id.btnRegister);
        _txtEmail = findViewById(R.id.txtEmail);
        _txtPass = findViewById(R.id.txtPass);
        myTextViewRegister = findViewById(R.id.textview_register);
        myTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
            }
        });
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = _txtEmail.getText().toString().trim();
                pwd = _txtPass.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (res == true){
                    Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(Login.this, usersForm.class);
                    startActivity(registerIntent);
                }else if (user.equals("omar@admin.com") && pwd.equals("localadmin")){
                    Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(Login.this, adminsForm.class);
                    startActivity(registerIntent);

                }else {
                    Toast.makeText(Login.this, "Login error", Toast.LENGTH_SHORT).show();
                }



               /*Util.saveSharedSetting(Login.this, "omar@admin.com", "true");
                //and when you click on logout button, you will set the value to true again
                Intent LogOut = new Intent(getApplicationContext(), adminsForm.class);
                startActivity(LogOut);
                finish();

                */



            }
        });
    }
    /*
    private void CheckAdminSession() {
        Boolean Check = Boolean.valueOf(Util.readSharedSetting(Login.this, "omar@admin.com", "true"));

        Intent introIntent = new Intent(Login.this, adminsForm.class);
        introIntent.putExtra("omar@admin.com", Check);
        //The value  if you click on login activity and set the value os false and we false the activity  will be visible
        if (Check){
            startActivity(introIntent);
            finish();
        }//if not, nothing happens with the main activity
    }
    private void CheckUserSession() {
        Boolean Check = Boolean.valueOf(Util.readSharedSetting(Login.this, "", "true"));

        Intent introIntent = new Intent(Login.this, adminsForm.class);
        introIntent.putExtra("", Check);
        //The value  if you click on login activity and set the value os false and we false the activity  will be visible
        if (Check){
            startActivity(introIntent);
            finish();
        }//if not, nothing happens with the main activity
    }
    public void saveUsername(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_USERINFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTVIEW_USERNAME, user);
        editor.apply();
    }
    public void savePassword(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_USERINFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTVIEW_PASSWORD, pwd);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPrefName = getSharedPreferences(SHARED_PREF_USERINFO, MODE_PRIVATE);
        user = sharedPrefName.getString(TEXTVIEW_USERNAME, "");
        pwd = sharedPrefName.getString(TEXTVIEW_PASSWORD, "");
        Toast.makeText(this, "user: " + user + " password: "+ pwd, Toast.LENGTH_SHORT).show();
    }

     */

    public void login(View view) {
    }

}