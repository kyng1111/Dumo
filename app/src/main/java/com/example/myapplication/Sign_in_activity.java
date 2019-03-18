package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sign_in_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        EditText idText = (EditText)findViewById(R.id.Sign_in_id);
        EditText passwordText = (EditText)findViewById(R.id.Sign_in_pw);

        Button loginbtn = (Button)findViewById(R.id.Sign_In_btn);
        Button registerbtn = (Button)findViewById(R.id.Sign_up_btn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Sign_in_activity.this, Sign_up_activity.class);
                Sign_in_activity.this.startActivity(registerIntent);
            }
        });
    }
}