package com.example.misreportapartment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {


    private Button _btnLogin;
    private EditText _txtEmail, _txtPass;
    private TextView myTextViewRegister;
    private DatabaseHelper db;
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
                String user = _txtEmail.getText().toString().trim();
                String pwd = _txtPass.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (res == true){
                    Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(Login.this, usersForm.class);
                    startActivity(registerIntent);
                }else {
                    Toast.makeText(Login.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(View view) {
    }

    public void saveData(){

    }
    public void loadData(){

    }
}