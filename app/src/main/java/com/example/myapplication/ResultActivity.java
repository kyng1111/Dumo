package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView TextView_id;
    TextView TextView_password;
    TextView cummit22;

    // ??

    // 아 왜 안돼

    //ㄱㄲ?

    // 되냐? 되냐?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView_id = findViewById(R.id.TextView_id);
        TextView_password = findViewById(R.id.TextView_password);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        String password = bundle.getString("password");

        TextView_id.setText(id);
        TextView_password.setText(password);

    }
}
