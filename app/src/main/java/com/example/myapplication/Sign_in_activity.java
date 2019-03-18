package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class Sign_in_activity extends AppCompatActivity {

    public static Activity Sign_in_activity;

    private static String TAG = "phpquerytest";
    private static String IP_ADDRESS = "13.125.216.189";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_ID = "User_ID";
    private static final String TAG_PASSWORD = "User_Password";


    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    String mJsonString;

    EditText mEditTextID;
    EditText mEditTextPW;

    String success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        success = "success";

        Sign_in_activity = Sign_in_activity.this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        mEditTextID = (EditText) findViewById(R.id.Sign_in_id);
        mEditTextPW = (EditText) findViewById(R.id.Sign_in_pw);

        mTextViewResult = (TextView)findViewById(R.id.Sign_in_txt);

        Button in_btn = (Button) findViewById(R.id.Sign_in_btn);
        Button up_btn = (Button)findViewById(R.id.Sign_up_btn);
        in_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();
                task.execute( mEditTextID.getText().toString(),mEditTextPW.getText().toString());

            }
        });

        up_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent=new Intent(Sign_in_activity.this,Sign_up_activity.class);
                startActivity(intent);
            }
        });

        mArrayList = new ArrayList<>();

    }


    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Sign_in_activity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();

                if(mTextViewResult.getText().toString().equals(success)){

                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Sign_in_activity.this,MainActivity.class);
                    startActivity(intent);

                }

            }

        }


        @Override
        protected String doInBackground(String... params) {

            String User_ID = params[0];
            String User_Password = params[1];

            String serverURL = "http://" + IP_ADDRESS + "/signin.php";

            String postParameters = "User_ID=" + User_ID + "&User_Password=" + User_Password;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

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
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String User_ID = item.getString(TAG_ID);
                String User_Password = item.getString(TAG_PASSWORD);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_ID, User_ID);
                hashMap.put(TAG_PASSWORD, User_Password);

                mArrayList.add(hashMap);
            }


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

}