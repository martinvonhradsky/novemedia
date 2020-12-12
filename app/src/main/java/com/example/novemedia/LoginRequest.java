package com.example.novemedia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://ehealthtest.000webhostapp.com/Login.php";
    private Map<String,String> params;

    public LoginRequest(String userName, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("userName",userName);
        params.put("password",password);
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }
}
