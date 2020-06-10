package com.example.misreportapartment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private Button btnReg;
    private TextView myTextViewLogin;
    private EditText txtUsername, txtPassword, txtCnfPassword, txtPhone;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        txtUsername = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPass);
        txtCnfPassword = findViewById(R.id.txtCnfPass);
        txtPhone = findViewById(R.id.txtPhone);
        btnReg = findViewById(R.id.btnRegister);
        myTextViewLogin = findViewById(R.id.textview_login);

        myTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(Register.this, Login.class);
                startActivity(LoginIntent);
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsername.getText().toString().trim();
                String phone = txtUsername.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();
                String cnf_pwd = txtCnfPassword.getText().toString().trim();
                if (pwd.equals(cnf_pwd)){

                    if (db.addUser(user,phone, pwd)){
                        Toast.makeText(Register.this, " You've successfully registered ", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Register.this, Login.class);
                        startActivity(moveToLogin);
                    }else {
                        Toast.makeText(Register.this, "Registration error", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}