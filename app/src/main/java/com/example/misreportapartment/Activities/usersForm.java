package com.example.misreportapartment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.misreportapartment.R;


public class usersForm extends AppCompatActivity {
    private Button send;
    private EditText to;
    private EditText subject;
    private EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_form);
        to = findViewById(R.id.et_to);
        subject = findViewById(R.id.et_subject);
        msg = findViewById(R.id.et_message);
        send = findViewById(R.id.bt_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toValue = to.getText().toString();
                String subjectValue = subject.getText().toString();
                String msgValue = msg.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ toValue });
                //email.putExtra(Intent.EXTRA_CC, new String[]{ toValue });
                //email.putExtra(Intent.EXTRA_BCC, new String[]{ toValue });
                email.putExtra(Intent.EXTRA_SUBJECT, new String[]{ subjectValue });
                email.putExtra(Intent.EXTRA_TEXT, new String[]{ msgValue });

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an email client"));
            }
        });
    }

}