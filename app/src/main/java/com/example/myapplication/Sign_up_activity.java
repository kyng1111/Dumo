package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;


public class Sign_up_activity extends AppCompatActivity {

    public static Activity Sign_up_activity;

    private static String IP_ADDRESS = "13.125.216.189";
    private static String TAG = "phptest";

    private EditText mEditTextId;
    private EditText mEditTextName;
    private EditText mEditTextPassword;
    private EditText mEditTextNickname;
    //private Button mConfirmBtn;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        Sign_up_activity = Sign_up_activity.this;

        mEditTextId = (EditText)findViewById(R.id.Sign_up_id);
        mEditTextPassword = (EditText)findViewById(R.id.Sign_up_pw);
        mEditTextName = (EditText)findViewById(R.id.Sign_up_name);
        mEditTextNickname = (EditText)findViewById(R.id.Sign_up_nickname);
        mTextViewResult = (TextView)findViewById(R.id.sign_up_text);
        //mConfirmBtn = (Button)findViewById(R.id.Sign_up_btn);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());


        Button buttonInsert = (Button)findViewById(R.id.sign_up_ok_btn);
        buttonInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String User_ID = mEditTextId.getText().toString();
                String User_Password = mEditTextPassword.getText().toString();
                String User_Name = mEditTextName.getText().toString();
                String User_Nickname = mEditTextNickname.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/signup.php", User_ID, User_Password, User_Name, User_Nickname);

                Intent intent=new Intent(Sign_up_activity.this,Sign_in_activity.class);
                startActivity(intent);

            }
        });

    }



    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Sign_up_activity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mTextViewResult.setText(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = (String)params[0];

            String User_ID = (String)params[1];
            String User_Password = (String)params[2];
            String User_Name = (String)params[3];
            String User_Nickname = (String)params[4];


            String postParameters = "User_ID=" + User_ID + "&User_Password=" + User_Password + "&User_Name=" + User_Name + "&User_Nickname=" + User_Nickname;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


}