package com.example.misreportapartment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private Button btnReg;
    private TextView myTextViewLogin;
    private EditText txtUsername, txtPassword, txtCnfPassword, txtPhone;
    private DatabaseHelper db;
    private ArrayAdapter adp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                String user = txtUsername.getText().toString().trim();
                String phone = txtPhone.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();
                String cnf_pwd = txtCnfPassword.getText().toString().trim();
                GuestModel guestToAdd = new GuestModel(-1, user, phone,pwd);
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

                 if (TextUtils.isEmpty(pwd)){
                      txtPassword.setError("Password is required.");
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
    private void updateViews() {
        adp = new ArrayAdapter<GuestModel>(this, android.R.layout.simple_list_item_1, db.getAllInfoFromUser());
    }
}