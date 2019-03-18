package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class Sign_up_request extends StringRequest {

    final static private String URL = "http://13.125.216.189/signup.php";
    private Map<String, String> parameters;

    //생성자
    public Sign_up_request(String User_ID, String User_Password, String User_Name, String User_Nickname, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("User_ID", User_ID);
        parameters.put("User_Password", User_Password);
        parameters.put("User_Name", User_Name);
        parameters.put("User_Nickname", User_Nickname);

    }

    //추후 사용을 위한 부분
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
