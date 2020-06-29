package com.example.misreportapartment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.misreportapartment.Adapters.MyAdapter;
import com.example.misreportapartment.Database.DatabaseHelper;
import com.example.misreportapartment.Model.User;
import com.example.misreportapartment.R;

import java.util.ArrayList;
import java.util.List;


public class usersForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public final static String SHARED_PREF_USERIFNO = "SHAREDPREF";
    public final static String TEXTVIEW_USERNAME = "TEXTVIEW_TEXT_NAME";
    public final static String TEXTVIEW_PASSWORD = "TEXTVIEW_TEXT_PASS";
    private DatabaseHelper db;
    private ListView _txtEmail, _txtPhone;
    ArrayList<User> arrayList;
    MyAdapter myAdapter;
    Spinner spinner;
    ArrayAdapter<CharSequence> arrayAdapter;
    String user;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_form);
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
        List<User> guestModels = db.getUserInfo();
        StringBuilder userName = new StringBuilder();
        StringBuilder password = new StringBuilder();
        userName.append("Username " + guestModels.get(0).getUserName());
        password.append(" Phone " + guestModels.get(0).getPhone());
        _txtEmail.setSelection(1);
        _txtPhone.setSelection(2);
        spinner.setOnItemSelectedListener(this);

    }

    /*
    private void loadDataInListView() {
        arrayList = db.getUserInfo();
        myAdapter = new MyAdapter(this,arrayList);
        _txtEmail.setAdapter(myAdapter);
        _txtPhone.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

     */

    public void logout(View view) {
    }
    public void loadData(){
        SharedPreferences sharedPrefName = getSharedPreferences(SHARED_PREF_USERIFNO, MODE_PRIVATE);
        user = sharedPrefName.getString(TEXTVIEW_USERNAME, "");
        phone = sharedPrefName.getString(TEXTVIEW_PASSWORD, "");
        Toast.makeText(this, "user: " + user + " phone: "+ phone, Toast.LENGTH_SHORT).show();
    }
    public void saveUsername(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_USERIFNO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTVIEW_USERNAME, user);
        editor.apply();
    }
    public void savePassword(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_USERIFNO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTVIEW_PASSWORD, phone);
        editor.apply();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}