package com.example.ankita.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ankita on 24-12-2017.
 */

public class SignupActivity extends AppCompatActivity {
//    final

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Button btn = (Button) findViewById(R.id.signupbutton);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);

                String URL = "http://infinite-oasis-40195.herokuapp.com/signup";

                final EditText name = (EditText) findViewById(R.id.username);
                final EditText email = (EditText) findViewById(R.id.email);
                final EditText pass = (EditText) findViewById(R.id.password);


                StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                                Intent myIntent = new Intent(SignupActivity.this, ChatActivity.class);
                                startActivity(myIntent);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                                final Context mContext = getApplicationContext();
                                Toast.makeText(mContext, "Username or Email ID Incorrect", Toast.LENGTH_LONG).show();


                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        requestQueue.getCache().clear();
                        Log.d("INSIDE MAP GET PARAMS",name.getText().toString());
                        params.put("username", name.getText().toString());
                        params.put("password", pass.getText().toString());
                        params.put("email", email.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(postRequest);


            }

        });



    }
}