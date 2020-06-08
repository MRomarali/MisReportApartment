package com.example.misreportapartment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnLogin;
    EditText _txtEmail, _txtPass;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        _btnLogin = (Button)findViewById(R.id.btnSend);
        _txtEmail = (EditText) findViewById(R.id.txtEmail);
        _txtPass = (EditText) findViewById(R.id.txtMessage);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtEmail.getText().toString();
                String pass = _txtPass.getText().toString();
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + " =? AND " + DatabaseHelper.COL_4 + " =? ", new String[]{email, pass});
                if (cursor!=null){
                    if (cursor.getCount() > 0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), " Login succesfully ", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), " error ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Form.class);
                startActivity(intent);
            }
        });
    }
}