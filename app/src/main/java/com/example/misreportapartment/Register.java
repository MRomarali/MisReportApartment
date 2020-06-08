package com.example.misreportapartment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btnReg, btnLog;
    EditText txtfname, txtlname, txtpass, txtemail, _txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new DatabaseHelper(this);
        txtfname = (EditText)findViewById(R.id.txtfname);
        txtlname = (EditText)findViewById(R.id.txtlname);
        txtpass = (EditText)findViewById(R.id.txtpass);
        txtemail = (EditText)findViewById(R.id.txtemail);
        _txtPhone = (EditText)findViewById(R.id.txtphone);
        btnLog = (Button)findViewById(R.id._btnLogin);
        btnReg = (Button)findViewById(R.id._btnreg);
        btnReg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            db = openHelper.getWritableDatabase();
            String fname = txtfname.getText().toString();
            String lname = txtlname.getText().toString();
            String password = txtpass.getText().toString();
            String email = txtemail.getText().toString();
            String phone = _txtPhone.getText().toString();
            insertData(fname,lname,password,email,phone);
            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_LONG).show();
        }
    });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
}
     public void insertData(String firstName, String lastName, String pass, String email, String phone){
          ContentValues contentValues  = new ContentValues();
          contentValues.put(DatabaseHelper.COL_2, firstName);
          contentValues.put(DatabaseHelper.COL_3, lastName);
          contentValues.put(DatabaseHelper.COL_4, pass);
          contentValues.put(DatabaseHelper.COL_5, email);
          contentValues.put(DatabaseHelper.COL_6, phone);
          long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}