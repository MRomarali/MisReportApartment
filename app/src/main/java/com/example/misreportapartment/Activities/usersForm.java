package com.example.misreportapartment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misreportapartment.Adapters.MyAdapter;
import com.example.misreportapartment.Database.DatabaseHelper;
import com.example.misreportapartment.Model.User;
import com.example.misreportapartment.R;

import java.util.ArrayList;
import java.util.List;


public class usersForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public final static String SHARED_PREF_USERINFO = "SHAREDPREF";
    public final static String TEXTVIEW_USERNAME = "TEXTVIEW_TEXT_NAME";
    public final static String TEXTVIEW_PASSWORD = "TEXTVIEW_TEXT_PASS";
    private DatabaseHelper db;
    private TextView _txtEmail, _txtPhone;
    private Button logout;
    ArrayList<String> arrayList;
    Spinner spinner;
    ArrayAdapter<CharSequence> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_form);
        logout = findViewById(R.id.btnLogout);
        spinner = findViewById(R.id.spinner1);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.apartment, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        _txtEmail = findViewById(R.id.listView1);
        _txtPhone = findViewById(R.id.listView2);
        db = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        _txtEmail = findViewById(R.id.listView1);
        _txtPhone = findViewById(R.id.listView2);

        User users = db.getUserInfo(1);
        StringBuilder userName = new StringBuilder();
        StringBuilder password = new StringBuilder();
        userName.append("Username " + users.getUserName());
        password.append(" Phone " + users.getPhone());
        _txtEmail.setText(userName);
        _txtPhone.setText(password);

        spinner.setOnItemSelectedListener(this);
        storeDataInArrays();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogoutIntent = new Intent(usersForm.this, Login.class);
                startActivity(LogoutIntent);
            }
        });
    }

    public void logout(View view) {
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(0));
                Toast.makeText(getApplicationContext(), " Id: " + cursor.getString(0), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), " User: " + cursor.getString(1), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), " Phone: " + cursor.getString(2), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), " Password: " + cursor.getString(3), Toast.LENGTH_SHORT).show();
            }
        }
    }
}