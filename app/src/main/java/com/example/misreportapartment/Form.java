package com.example.misreportapartment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Form extends AppCompatActivity {


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText _txtEmail, _txtPhone, _txtMessage;
    Button _btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        openHelper = new DatabaseHelper(this);
        _txtEmail = (EditText)findViewById(R.id.txtEmail);
        _txtPhone = (EditText)findViewById(R.id.txtphone);
        _txtMessage = (EditText)findViewById(R.id.txtMessage);
        _btnSend = (Button)findViewById(R.id.btnSend);
        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String Email = _txtEmail.getText().toString();
                String Phone = _txtPhone.getText().toString();
                String Message = _txtMessage.getText().toString();
                insertData(Email,Phone, Message);
                Toast.makeText(getApplicationContext(), "Created notification successfully", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void insertData(String email, String phone, String message){
        ContentValues contentValues  = new ContentValues();
        contentValues.put(DatabaseHelper.COL_5, email);
        contentValues.put(DatabaseHelper.COL_6, phone);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}