package com.example.misreportapartment.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.misreportapartment.Database.DatabaseHelper;
import com.example.misreportapartment.Model.User;
import com.example.misreportapartment.R;

public class Register extends AppCompatActivity {

    private Button btnReg;
    private TextView myTextViewLogin, myTextView, myTextViewPhone;
    private EditText txtUsername, txtPassword, txtCnfPassword, txtPhone;
    private DatabaseHelper db;
    private ProgressBar progressBar;
    String user;
    String phone;
    String pwd;
    String cnf_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myTextView = findViewById(R.id.textv_username1);
        myTextViewPhone = findViewById(R.id.text_2);
        progressBar = findViewById(R.id.progressBar1);
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
                user = txtUsername.getText().toString().trim();
                Toast.makeText(Register.this, "Name: " + user, Toast.LENGTH_SHORT).show();
                myTextView.setText(user);
                phone = txtPhone.getText().toString().trim();
                myTextViewPhone.setText(phone);
                pwd = txtPassword.getText().toString().trim();
                cnf_pwd = txtCnfPassword.getText().toString().trim();
                User guestToAdd = new User(-1, user, phone,pwd);
                boolean status = db.addUser(guestToAdd);
                if (pwd.equals(cnf_pwd)){

                    if (db.addUser(guestToAdd)){

                        Toast.makeText(Register.this, " You've successfully registered " + status, Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Register.this, Login.class);
                        startActivity(moveToLogin);
                    }else {
                        Toast.makeText(Register.this, "Registration error", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }

                 if (TextUtils.isEmpty(user)){
                      txtUsername.setError("Email is required.");
                      return;
                 }

                if (TextUtils.isEmpty(phone)){
                    txtPhone.setError("Phone is required.");
                    return;
                }

                 if (TextUtils.isEmpty(pwd)){
                      txtPassword.setError("Password is required.");
                      return;
                 }

                if (TextUtils.isEmpty(cnf_pwd)){
                    txtCnfPassword.setError("Password confirmation is required.");
                    return;
                }

                 if (pwd.length() < 6){
                      txtPassword.setError("Password must be 6 or more characters");
                      return;
                 }
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }
}