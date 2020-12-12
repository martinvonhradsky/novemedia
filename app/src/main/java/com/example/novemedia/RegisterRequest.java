package com.example.novemedia;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;


public class RegisterRequest extends JsonObjectRequest {

    private static final String REGISTER_REQUEST_URL = "http://174.138.11.120:5000/register";


    public RegisterRequest(JSONObject request, Response.Listener<JSONObject> listener){
        super(POST, REGISTER_REQUEST_URL, request, listener, null);

    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

}
