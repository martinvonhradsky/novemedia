package com.example.novemedia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.novemedia.RegisterRequest;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {


    private EditText etUserName;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = findViewById(R.id.edMail);
        etPassword = findViewById(R.id.edPassword);
        etPassword2 = findViewById(R.id.edPassword2);
        btRegister = findViewById(R.id.btReg);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String uName = etUserName.getText().toString();
                final String password = etPassword.getText().toString();
                final String password2 = etPassword2.getText().toString();

                boolean registerFlag = true;

                String mailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";
                if(uName.matches("") || password.matches("") || password2.matches("") ){
                    Toast toast = Toast.makeText(getApplicationContext(), "Nie sú vyplnené všetky polia", Toast.LENGTH_LONG);
                    registerFlag = false;
                    toast.show();
                }else
                if(password.length() < 6){
                    Toast toast = Toast.makeText(getApplicationContext(), "Zadané heslo musí obsahovať minimálne 6 znakov", Toast.LENGTH_LONG);
                    registerFlag = false;
                    toast.show();
                }else
                if(!password.matches(pattern)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Heslo musí obsahovať:\n->aspoň jedno veľké písmeno\n->aspoň jedno malé písmeno\n->aspoň jednu číslicu 0 - 9", Toast.LENGTH_LONG);
                    registerFlag = false;
                    toast.show();
                }else
                if(!password.equals(password2)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Zadané heslá sa nezhodujú", Toast.LENGTH_LONG);
                    registerFlag = false;
                    toast.show();
                }
                else
                if(!uName.matches(mailPattern)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Nesprávny formát Emailu", Toast.LENGTH_LONG);
                    registerFlag = false;
                    toast.show();
                }

                if(!registerFlag){
                    return;
                }


                    Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("tagconvertstr", "["+response+"]");
                            Intent backToLogin = new Intent(Register.this, Login.class);
                            Register.this.startActivity(backToLogin);
                        }


                    };
                    //String firstName, String lastName, String email, String password, String city, String zip, String country
                    JSONObject request = new JSONObject();
                    try {
                        request.put("email", uName);
                        request.put("password", password);
                        Log.i("JSON2Bsend", "["+request.toString()+"]");
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                RegisterRequest registerRequest = new RegisterRequest(request ,response);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                Log.i("Request2Bsend", "["+registerRequest.toString()+"]");
                queue.add(registerRequest);
                }
        });
    }

}
